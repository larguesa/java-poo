package com.example;

import com.example.database.SQLiteConnection;
import com.example.model.Role;
import com.example.model.User;
import com.example.model.VehicleStay;
import com.example.model.HourPrice;
import com.example.repository.VehicleStayDAO;
import com.example.repository.UserDAO;
import com.example.repository.HourPriceDAO;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        SQLiteConnection.createTables();

        // Cria admin padrão se não houver usuários
        try {
            // Verifica se há usuários (simples, sem lista completa)
            User adminCheck = UserDAO.getUserByUsername("admin");
            if (adminCheck == null) {
                User admin = new User("admin", "admin123", Role.ADMIN);
                UserDAO.createUser(admin);
                System.out.println("Admin padrão criado: username=admin, password=admin123");
            }
        } catch (Exception e) {
            System.err.println("Erro ao criar admin padrão: " + e.getMessage());
        }

        // Cria preço por hora padrão se não houver
        try {
            HourPrice hp = HourPriceDAO.getHourPrice();
            if (hp == null) {
                hp = new HourPrice();
                hp.setPrice(5.0); // R$ 5 por hora
                HourPriceDAO.insertHourPrice(hp);
                System.out.println("Preço por hora padrão criado: R$ 5.00");
            }
        } catch (Exception e) {
            System.err.println("Erro ao criar preço padrão: " + e.getMessage());
        }

        Javalin app = Javalin.create(config -> {
            config.http.defaultContentType = "text/plain; charset=UTF-8";
            config.staticFiles.add("/static", Location.CLASSPATH);
        }).start(7078);

        app.before("/api/*", ctx -> {
            try {
                String token = ctx.header("Authorization");
                if (token == null || token.isEmpty()) {
                    ctx.status(401).json(Map.of("error", "Token necessário"));
                    ctx.skipRemainingHandlers();
                    return;
                }
                User user = UserDAO.getUserByToken(token);
                if (user == null) {
                    ctx.status(401).json(Map.of("error", "Token inválido"));
                    ctx.skipRemainingHandlers();
                    return;
                }
                ctx.attribute("user", user);
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro interno: " + e.getMessage()));
                ctx.skipRemainingHandlers();
            }
        });
        app.before("/api/admin/*", ctx -> {
            User user = ctx.attribute("user");
            if (user == null) {
                ctx.status(401).json(Map.of("error", "Usuário não autenticado"));
                ctx.skipRemainingHandlers();
                return;
            }
            if (user.getRole() != Role.ADMIN) {
                ctx.status(403).json(Map.of("error", "Acesso negado"));
                ctx.skipRemainingHandlers();
                return;
            }
        });

        app.get("/api/session", ctx -> {
            try {
                User user = ctx.attribute("user");
                if (user != null) {
                    ctx.json(Map.of("username", user.getUsername(), "role", user.getRole().name()));
                } else {
                    ctx.status(401).json(Map.of("error", "No session"));
                }
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        app.post("/login", ctx -> {
            try {
                User loginData = ctx.bodyAsClass(User.class);
                boolean valid = UserDAO.validateLogin(loginData.getUsername(), loginData.getPassword());
                if (!valid) {
                    ctx.status(401).json(Map.of("error", "Credenciais inválidas"));
                    return;
                }
                String token = UUID.randomUUID().toString();
                UserDAO.updateToken(loginData.getUsername(), token);
                ctx.json(Map.of("token", token));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro interno: " + e.getMessage()));
            }
        });

        app.put("/logout", ctx -> {
            try {
                String token = ctx.header("Authorization");
                if (token != null) {
                    UserDAO.clearToken(token);
                }
                ctx.json(Map.of("message", "Logout OK"));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        app.get("/api/parking", ctx -> {
            try {
                User user = ctx.attribute("user");
                if (user == null) {
                    ctx.status(401).json(Map.of("error", "Usuário não autenticado"));
                    return;
                }
                List<VehicleStay> stays = VehicleStayDAO.getAll().stream().filter(s -> s.getExitTime() == null).collect(Collectors.toList());
                HourPrice hp = HourPriceDAO.getHourPrice();
                ctx.json(Map.of("list", stays, "hourPrice", hp != null ? hp.getPrice() : 0.0));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        app.post("/api/parking", ctx -> {
            try {
                User user = ctx.attribute("user");
                if (user == null) {
                    ctx.status(401).json(Map.of("error", "Usuário não autenticado"));
                    return;
                }
                JSONObject json = new JSONObject(ctx.body());
                String plate = json.getString("plate");
                String model = json.getString("model");
                String color = json.getString("color");
                VehicleStay stay = new VehicleStay(plate, model, color, LocalDateTime.now(), user.getId());
                VehicleStayDAO.add(stay);
                ctx.status(201).json(Map.of("message", "Estadia iniciada"));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        app.put("/api/parking/{id}", ctx -> {
            try {
                User user = ctx.attribute("user");
                if (user == null) {
                    ctx.status(401).json(Map.of("error", "Usuário não autenticado"));
                    return;
                }
                int id = Integer.parseInt(ctx.pathParam("id"));
                // Verifica se pertence ao usuário
                List<VehicleStay> userStays = VehicleStayDAO.getByUserId(user.getId());
                boolean owns = userStays.stream().anyMatch(s -> s.getId().equals(id));
                if (!owns) {
                    ctx.status(403).json(Map.of("error", "Estadia não pertence ao usuário"));
                    return;
                }
                VehicleStay stay = userStays.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
                if (stay == null) return;
                HourPrice hp = HourPriceDAO.getHourPrice();
                double price = hp != null ? hp.getPrice() : 0.0;
                long hours = java.time.Duration.between(stay.getEntryTime(), LocalDateTime.now()).toHours();
                stay.setExitTime(LocalDateTime.now());
                stay.setTotalCost(price * hours);
                VehicleStayDAO.update(stay);
                ctx.json(Map.of("message", "Estadia finalizada"));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        app.get("/api/parking/history", ctx -> {
            try {
                User user = ctx.attribute("user");
                if (user == null) {
                    ctx.status(401).json(Map.of("error", "Usuário não autenticado"));
                    return;
                }
                // Mostra todas as estadias concluídas
                List<VehicleStay> stays = VehicleStayDAO.getAll().stream().filter(s -> s.getExitTime() != null).toList();
                ctx.json(Map.of("list", stays));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        app.get("/api/admin/parking", ctx -> {
            try {
                List<VehicleStay> stays = VehicleStayDAO.getAll();
                ctx.json(stays);
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", "Erro: " + e.getMessage()));
            }
        });

        // Admin: criar user
        app.post("/api/admin/users", ctx -> {
            try {
                JSONObject json = new JSONObject(ctx.body());
                String username = json.getString("username");
                String password = json.getString("password");
                String roleStr = json.getString("role");
                Role role = Role.valueOf(roleStr.toUpperCase());
                User newUser = new User(username, password, role);
                UserDAO.createUser(newUser);
                ctx.status(201).json(Map.of("message", "User criado"));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", e.getMessage()));
            }
        });

        // Admin: listar users
        app.get("/api/admin/users", ctx -> {
            try {
                List<User> users = UserDAO.getAllUsers();
                ctx.json(Map.of("users", users));
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", e.getMessage()));
            }
        });

        // Admin: deletar user
        app.delete("/api/admin/users", ctx -> {
            try {
                int id = Integer.parseInt(ctx.queryParam("id"));
                boolean deleted = UserDAO.deleteUser(id);
                if (deleted) {
                    ctx.status(200).json(Map.of("message", "User deleted"));
                } else {
                    ctx.status(404).json(Map.of("error", "User not found"));
                }
            } catch (Exception e) {
                ctx.status(500).json(Map.of("error", e.getMessage()));
            }
        });

        System.out.println("Servidor rodando em http://localhost:7078");
    }
}