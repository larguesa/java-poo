package br.gov.sp.fatec.pg;

import java.util.UUID;

import br.gov.sp.fatec.pg.database.SQLiteConnection;
import io.javalin.Javalin;

import br.gov.sp.fatec.pg.repository.UserRepository;
import br.gov.sp.fatec.pg.model.User;

public class Main {
    public static void main(String[] args) {
        // 1. Garante que a tabela existe ao iniciar
        SQLiteConnection.createTable();

        Javalin app = Javalin.create(config -> {
            // Define o Content-Type padrão para todas as respostas
            config.http.defaultContentType = "text/plain; charset=UTF-8";
            
            // Se estiver usando arquivos estáticos ou templates, verifique também:
            config.pvt.javaLangErrorHandler((res, error) -> {
                res.setCharacterEncoding("UTF-8");
            });
        }).start(7070);

        // Antes das rotas ou logo após a criação do app
        app.before("/admin/*", ctx -> {
            // Verifica se existe um usuário guardado na sessão
            User usuario = ctx.sessionAttribute("usuario_logado");

            if (usuario == null) {
                // Se não houver usuário, interrompe a requisição aqui mesmo!
                ctx.status(401).result("Acesso negado: Faça login primeiro.");
                ctx.skipRemainingHandlers(); // Não deixa passar para a rota real
            }
            // Se não entrar no if, o Javalin deixa a requisição seguir normalmente
        });

        // Middleware para proteger a API via TOKEN
        app.before("/api/*", ctx -> {
            // 1. Pega o token do cabeçalho (Padrão: "Authorization")
            String token = ctx.header("Authorization");
            
            if (token == null) {
                ctx.status(401).result("Cabeçalho Authorization ausente.");
                ctx.skipRemainingHandlers(); // Para tudo!
                return;
            }

            // 2. Verifica se o Token existe no Banco
            User usuario = UserRepository.getUserByToken(token);
            
            if (usuario == null) {
                ctx.status(403).result("Token inválido ou expirado. Faça login novamente.");
                ctx.skipRemainingHandlers();
            } else {
                // Token válido! Deixa passar.
                System.out.println("Acesso API autorizado para: " + usuario.getUsername());
            }
        });

        // Exemplo de rota protegida por Token
        app.get("/api/secreto", ctx -> {
            ctx.result("Se você está vendo isso, seu Token é válido!");
        });

        // 2. Rota de Teste (Logger)
        app.get("/logger", ctx -> {
            // Se chegou aqui sem erros no console, o banco está ok
            ctx.result("Banco de dados conectado e tabela pronta!");
        });

        // 3. Cadastrar Usuário (POST)
        app.post("/users", ctx -> {
            User user = ctx.bodyAsClass(User.class); // Transforma JSON em Objeto Java
            try {
                UserRepository.add(user);
                ctx.status(201).result("Usuário criado!");
            } catch (Exception e) {
                ctx.status(400).result("Erro ao criar: " + e.getMessage());
            }
        });

        // 4. Deletar Usuário (DELETE)
        app.delete("/users/{username}", ctx -> {
            String username = ctx.pathParam("username");
            if (UserRepository.delete(username)) {
                ctx.status(204); // Sucesso, sem conteúdo
            } else {
                ctx.status(404).result("Usuário não encontrado.");
            }
        });

        // 5. Login (POST) - Simples validação
        app.post("/login", ctx -> {
            User dto = ctx.bodyAsClass(User.class); // Dados do formulário
            // 1. Valida credenciais
            if (UserRepository.validate(dto.getUsername(), dto.getPassword())) {
                // 2. Busca o usuário completo (Aqui usamos o getByUsername!)
                User userDoBanco = UserRepository.getByUsername(dto.getUsername());
                // 3. Grava na Sessão (Javalin cria o Cookie JSESSIONID automaticamente)
                ctx.sessionAttribute("usuario_logado", userDoBanco);
                ctx.status(200).result("Login efetuado! Sessão criada.");
            } else {
                ctx.status(401).result("Dados incorretos.");
            }
        });

        // 5.1. (Login com Token) - Stateless
        app.post("/token-login", ctx -> {
            User dto = ctx.bodyAsClass(User.class);

            if (UserRepository.validate(dto.getUsername(), dto.getPassword())) {
                // 1. Gerar um Token aleatório único (UUID)
                String token = UUID.randomUUID().toString();
                
                // 2. Salvar o token no banco (Persistência)
                UserRepository.updateToken(dto.getUsername(), token);
                
                // 3. Retornar o token para o cliente (JSON)
                ctx.json(java.util.Map.of("token", token)); 
            } else {
                ctx.status(401).result("Credenciais inválidas");
            }
        });

        // Rota de Logout (Destrói a sessão)
        app.post("/logout", ctx -> {
            ctx.req().getSession().invalidate(); // Limpa a memória do servidor
            ctx.status(200).result("Sessão encerrada.");
        });

        // Uma rota protegida para testar
        app.get("/admin/dados", ctx -> {
            User usuario = ctx.sessionAttribute("usuario_logado");
            ctx.result("Dados secretos acessados por: " + usuario.getUsername());
        });
    }
}