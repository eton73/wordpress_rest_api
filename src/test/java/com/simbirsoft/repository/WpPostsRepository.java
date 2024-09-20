package com.simbirsoft.repository;

import com.simbirsoft.config.DataBaseConfig;
import com.simbirsoft.repository.model.WpPostModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WpPostsRepository {

    private static final Logger logger = LoggerFactory.getLogger(WpPostsRepository.class);

    public WpPostModel getPostById(Integer id) {
        WpPostModel model = new WpPostModel();
        try(Statement statement = DataBaseConfig.connection.createStatement()) {
            String sql = String.format("SELECT * FROM wp_posts WHERE id = %s", id);
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                model.setId(resultSet.getInt(WpPostModel.Fields.id));
                model.setPost_date(resultSet.getString(WpPostModel.Fields.post_date));
                model.setPost_date_gmt(resultSet.getString(WpPostModel.Fields.post_date_gmt));
            }

            resultSet.close();
            return model;
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
            return null;
        }
    }

    public List<WpPostModel> getAllPosts() {
        List<WpPostModel> models = new ArrayList<>();
        try(Statement statement = DataBaseConfig.connection.createStatement()) {
            String sql = "SELECT * FROM wp_posts";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                WpPostModel model = new WpPostModel();
                model.setId(resultSet.getInt(WpPostModel.Fields.id));
                model.setPost_date(resultSet.getString(WpPostModel.Fields.post_date));
                model.setPost_date_gmt(resultSet.getString(WpPostModel.Fields.post_date_gmt));
                models.add(model);
            }

            resultSet.close();
            return models;
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
            return null;
        }
    }

    public void deletePostById(Integer id) {
        try(Statement statement = DataBaseConfig.connection.createStatement()) {
            String sql = String.format("DELETE FROM wp_posts WHERE id = %s", id);
            statement.executeQuery(sql);
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }

}
