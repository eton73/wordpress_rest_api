package com.simbirsoft.config;

import com.simbirsoft.helpers.ConfHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/wordpress";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static Connection connection;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, ConfHelpers.getProperty("user"),
                    ConfHelpers.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static void disconnect() throws SQLException {
        connection.close();
    }

}
