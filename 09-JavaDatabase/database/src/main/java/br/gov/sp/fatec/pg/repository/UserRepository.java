package br.gov.sp.fatec.pg.repository;

import br.gov.sp.fatec.pg.database.SQLiteConnection;
import br.gov.sp.fatec.pg.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    /**
     * Insere um novo usuário no banco de dados.
     * @param user Objeto usuário preenchido
     * @throws Exception Se houver erro de SQL ou duplicação de username
     */
    public static void add(User user) throws Exception {
        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            
            pstmt.executeUpdate();
        }
        // A conexão fecha sozinha aqui graças ao try-with-resources
    }

    /**
     * Remove um usuário pelo nome de usuário.
     * @param username Nome do usuário a ser removido
     * @return true se apagou, false se não encontrou
     * @throws Exception Erros de conexão
     */
    public static boolean delete(String username) throws Exception {
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Valida se o par username/senha existe no banco.
     * @param username Nome do usuário
     * @param password Senha (em texto plano para este exemplo didático)
     * @return true se as credenciais forem válidas
     * @throws Exception Erros de conexão
     */
    public static boolean validate(String username, String password) throws Exception {
        String sql = "SELECT count(*) FROM users WHERE username = ? AND password = ?";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Se o count for maior que 0, o usuário existe
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    
    /**
     * (Opcional) Busca um usuário pelo nome. Útil para Sessão.
     */
    public static User getByUsername(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        }
        return user;
    }

    // ATUALIZAR TOKEN (Login)
    public static void updateToken(String username, String token) throws Exception {
        String sql = "UPDATE users SET token = ? WHERE username = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        }
    }

    // BUSCAR POR TOKEN (Validação de Acesso)
    public static User getUserByToken(String token) throws Exception {
        String sql = "SELECT * FROM users WHERE token = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUsername(rs.getString("username"));
                // Não precisamos retornar a senha aqui por segurança
                return u;
            }
        }
        return null; // Token inválido ou não encontrado
    }
}