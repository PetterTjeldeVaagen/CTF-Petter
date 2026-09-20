package com.petter.ctf.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final String[][] SEED_USERS = {
        {"per", "password123"},
        {"pål", "password456"},
        {"espen", "password789"},
        {"flag", "FLAG"}
    };

    private final DatabaseConfig.ConnectionFactory connectionFactory;

    public DatabaseInitializer(DatabaseConfig.ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = connectionFactory.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                // drop and recreate on every startup so the db resets each time the app restarts
                stmt.execute("DROP TABLE IF EXISTS users");
                stmt.execute("""
                    CREATE TABLE users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT NOT NULL UNIQUE,
                        password TEXT NOT NULL
                    )
                    """);
            }

            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)")) {
                for (String[] user : SEED_USERS) {
                    ps.setString(1, user[0]);
                    ps.setString(2, user[1]);
                    ps.executeUpdate();
                }
            }
        }
    }
}
