package com.petter.ctf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Value("${app.datasource.url}")
    private String url;

    @Bean
    public ConnectionFactory connectionFactory() {
        String path = url.replaceFirst("^jdbc:sqlite:", "");
        File parentDir = new File(path).getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }

        return () -> DriverManager.getConnection(url);
    }

    @FunctionalInterface
    public interface ConnectionFactory {
        Connection getConnection() throws SQLException;
    }
}
