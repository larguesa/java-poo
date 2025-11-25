package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.database.SQLiteConnection;
import com.example.model.VehicleStay;

public class VehicleStayDAO {

    public static void add(VehicleStay stay) throws Exception {
        String sql = "INSERT INTO vehicle_stays(vehicle_plate, vehicle_model, vehicle_color, begin_stay, user_id) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stay.getLicensePlate());
            pstmt.setString(2, stay.getVehicleModel());
            pstmt.setString(3, stay.getVehicleColor());
            pstmt.setString(4, stay.getEntryTime().toString());
            pstmt.setInt(5, stay.getUserId());
            pstmt.executeUpdate();
        }
    }

    public static List<VehicleStay> getByUserId(int userId) throws Exception {
        String sql = "SELECT * FROM vehicle_stays WHERE user_id = ? AND end_stay IS NULL";
        List<VehicleStay> stays = new ArrayList<>();
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                VehicleStay stay = new VehicleStay();
                stay.setId(rs.getInt("id"));
                stay.setLicensePlate(rs.getString("vehicle_plate"));
                stay.setVehicleModel(rs.getString("vehicle_model"));
                stay.setVehicleColor(rs.getString("vehicle_color"));
                stay.setEntryTime(LocalDateTime.parse(rs.getString("begin_stay")));
                stay.setExitTime(rs.getString("end_stay") != null ? LocalDateTime.parse(rs.getString("end_stay")) : null);
                stay.setTotalCost(rs.getDouble("price"));
                stay.setUserId(rs.getInt("user_id"));
                stays.add(stay);
            }
        }
        return stays;
    }

    public static List<VehicleStay> getAll() throws Exception {
        String sql = "SELECT * FROM vehicle_stays";
        List<VehicleStay> stays = new ArrayList<>();
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                VehicleStay stay = new VehicleStay();
                stay.setId(rs.getInt("id"));
                stay.setLicensePlate(rs.getString("vehicle_plate"));
                stay.setVehicleModel(rs.getString("vehicle_model"));
                stay.setVehicleColor(rs.getString("vehicle_color"));
                stay.setEntryTime(LocalDateTime.parse(rs.getString("begin_stay")));
                stay.setExitTime(rs.getString("end_stay") != null ? LocalDateTime.parse(rs.getString("end_stay")) : null);
                stay.setTotalCost(rs.getDouble("price"));
                stay.setUserId(rs.getInt("user_id"));
                stays.add(stay);
            }
        }
        return stays;
    }

    public static void update(VehicleStay stay) throws Exception {
        String sql = "UPDATE vehicle_stays SET end_stay = ?, price = ? WHERE id = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, stay.getExitTime().toString());
            pstmt.setDouble(2, stay.getTotalCost());
            pstmt.setInt(3, stay.getId());
            pstmt.executeUpdate();
        }
    }

    public static boolean delete(int id) throws Exception {
        String sql = "DELETE FROM vehicle_stays WHERE id = ?";
        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}