package br.gov.sp.fatec.pg.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteConnection {
    // String de conexão (cria o arquivo users.db na raiz do projeto)
    private static final String URL = "jdbc:sqlite:users.db";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL);
    }

    // Método para criar a tabela se ela não existir
    public static void createTable() {
        // 1. Cria a tabela base (se não existir)
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "username TEXT UNIQUE NOT NULL, " +
                     "password TEXT NOT NULL)";
        // 2. Tenta adicionar a coluna token (Migração)
        String sqlAlter = "ALTER TABLE users ADD COLUMN token TEXT";
        
        // Try-with-resources garante o fechamento da conexão
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreate);
            System.out.println("Tabela 'users' verificada/criada com sucesso.");
            try {
                stmt.execute(sqlAlter); // Tenta criar a coluna
                System.out.println("Coluna 'token' adicionada com sucesso.");
            } catch (Exception e) {
                // Se der erro, provavelmente a coluna já existe. Ignoramos.
                // Em produção, usaríamos ferramentas como Flyway ou Liquibase.
                System.out.println("Nota: A coluna 'token' provavelmente já existe.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}