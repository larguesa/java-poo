package com.example.repository;

import com.example.database.SQLiteConnection;
import com.example.model.HourPrice;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HourPriceDAO {

    public static HourPrice getHourPrice() throws Exception {
        String sql = "SELECT * FROM hour_prices LIMIT 1";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                HourPrice hp = new HourPrice();
                hp.setId(rs.getInt("id"));
                hp.setPrice(rs.getDouble("price"));
                return hp;
            }
        }
        return null;
    }

    public static void updateHourPrice(HourPrice hourPrice) throws Exception {
        String sql = "UPDATE hour_prices SET price = ? WHERE id = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, hourPrice.getPrice());
            pstmt.setInt(2, hourPrice.getId());
            pstmt.executeUpdate();
        }
    }

    public static void insertHourPrice(HourPrice hourPrice) throws Exception {
        String sql = "INSERT INTO hour_prices(price) VALUES(?)";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, hourPrice.getPrice());
            pstmt.executeUpdate();
        }
    }
}