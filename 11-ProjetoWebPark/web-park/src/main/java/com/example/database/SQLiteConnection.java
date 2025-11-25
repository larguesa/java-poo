package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteConnection {
    // String de conexão (cria o arquivo parking.db na raiz do projeto)
    private static final String URL = "jdbc:sqlite:parking.db";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL);
    }

    // Método para criar as tabelas se elas não existirem
    public static void createTables() {
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT UNIQUE NOT NULL, " +
                        "password TEXT NOT NULL, " +
                        "role TEXT NOT NULL DEFAULT 'USER', " +
                        "token TEXT)";
        String sqlVehicleStays = "CREATE TABLE IF NOT EXISTS vehicle_stays (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "vehicle_plate TEXT NOT NULL, " +
                        "vehicle_model TEXT NOT NULL, " +
                        "vehicle_color TEXT NOT NULL, " +
                        "begin_stay DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                        "end_stay DATETIME, " +
                        "price REAL, " +
                        "user_id INTEGER NOT NULL, " +
                        "FOREIGN KEY (user_id) REFERENCES users(id))";
        String sqlHourPrices = "CREATE TABLE IF NOT EXISTS hour_prices (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "price REAL NOT NULL)";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsers);
            stmt.execute(sqlVehicleStays);
            stmt.execute(sqlHourPrices);
            System.out.println("Tabelas criadas/verif.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}