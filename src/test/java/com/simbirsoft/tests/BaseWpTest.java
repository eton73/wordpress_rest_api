package com.simbirsoft.tests;

import com.simbirsoft.helpers.ConfHelpers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseWpTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseWpTest.class);
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/wordpress";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    protected Connection connection;

    @BeforeClass
    public void setup(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, ConfHelpers.getProperty("user"),
                    ConfHelpers.getProperty("password"));
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @AfterClass
    public void exit() throws SQLException {
        connection.close();
    }
}
