package com.simbirsoft.tests;

import com.simbirsoft.api.WpPostsApi;
import com.simbirsoft.config.DataBaseConfig;
import com.simbirsoft.repository.WpPostsRepository;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseWpTest {

    protected Connection connection;
    protected WpPostsApi api;
    protected WpPostsRepository repository;

    @BeforeClass
    public void setup(){
        connection = DataBaseConfig.connection;
        api = new WpPostsApi();
        repository = new WpPostsRepository();
    }

    @AfterClass
    public void exit() throws SQLException {
        DataBaseConfig.disconnect();
    }
}
