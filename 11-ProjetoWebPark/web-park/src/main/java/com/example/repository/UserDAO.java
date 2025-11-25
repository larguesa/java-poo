package com.example.repository;

import com.example.database.SQLiteConnection;
import com.example.model.Role;
import com.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public static void createUser(User user) throws Exception {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String sql = "INSERT INTO users(username, password, role) VALUES(?, ?, ?)";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, user.getRole().name());
            pstmt.executeUpdate();
        }
    }

    public static User getUserByUsername(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setToken(rs.getString("token"));
                return user;
            }
        }
        return null;
    }

    public static boolean validateLogin(String username, String password) throws Exception {
        User user = getUserByUsername(username);
        return user != null && BCrypt.checkpw(password, user.getPassword());
    }

    public static void updateToken(String username, String token) throws Exception {
        String sql = "UPDATE users SET token = ? WHERE username = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        }
    }

    public static User getUserByToken(String token) throws Exception {
        String sql = "SELECT * FROM users WHERE token = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(Role.valueOf(rs.getString("role")));
                return user;
            }
        }
        return null;
    }

    public static void clearToken(String token) throws Exception {
        String sql = "UPDATE users SET token = NULL WHERE token = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, token);
            pstmt.executeUpdate();
        }
    }

    public static List<User> getAllUsers() throws Exception {
        String sql = "SELECT id, username, role FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setRole(Role.valueOf(rs.getString("role")));
                users.add(user);
            }
        }
        return users;
    }

    public static boolean deleteUser(int id) throws Exception {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}