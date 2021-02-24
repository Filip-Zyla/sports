package com.fzyla.sportstandings;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class MySqlConnection {

    final static String DB_URL = "jdbc:MySQL://localhost/sport_table";
    final static String DB_USER = "root";
    final static String DB_PASSWORD = "kanar12sqlkot";

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        return connection;
    }
}
