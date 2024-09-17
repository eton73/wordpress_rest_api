package com.simbirsoft.tests;

import com.simbirsoft.dto.GetResponse;
import com.simbirsoft.dto.PostRequest;
import com.simbirsoft.dto.PostResponse;
import com.simbirsoft.helpers.ConfHelpers;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.sql.*;

public class WpPostsTest extends BaseWpTest {

    private static final Logger logger = LoggerFactory.getLogger(WpPostsTest.class);
    private static final String URL_WP_V2_POSTS = "http://localhost:8000/wp-json/wp/v2/posts";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    @Test
    public void postTest() throws SQLException {
        PostRequest body = createPostRequest();

        PostResponse result = RestAssured.given()
                .body(body)
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                .when()
                .post(URL_WP_V2_POSTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PostResponse.class);

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            String sql = String.format("SELECT * FROM wp_posts WHERE id = %s", result.getId());

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String postDate = resultSet.getString("post_date");
                String postDateGmt = resultSet.getString("post_date_gmt");

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(id).isEqualTo(result.getId());
                softAssertions.assertThat(postDate).isEqualTo(result.getDate());
                softAssertions.assertThat(postDateGmt).isEqualTo(result.getDateGmt());
            }

            String deleteData = String.format("DELETE FROM wp_posts WHERE id = %s", result.getId());

            statement.executeQuery(deleteData);

        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

    }
    
    @Test
    public void getTest() throws SQLException {
        GetResponse[] result = RestAssured.given()
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .when()
                .get(URL_WP_V2_POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(GetResponse[].class);
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            String sql = "SELECT * FROM wp_posts";

            resultSet = statement.executeQuery(sql);

            for (GetResponse getResponse : result) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String postDate = resultSet.getString("post_date");
                    String postDateGmt = resultSet.getString("post_date_gmt");

                    SoftAssertions softAssertions = new SoftAssertions();
                    softAssertions.assertThat(id).isEqualTo(getResponse.getId());
                    softAssertions.assertThat(postDate).isEqualTo(getResponse.getDate());
                    softAssertions.assertThat(postDateGmt).isEqualTo(getResponse.getDateGmt());
                }
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Test
    public void getByIDTest() throws SQLException {
        GetResponse result = RestAssured.given()
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .when()
                .get(URL_WP_V2_POSTS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(GetResponse.class);
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            String sql = String.format("SELECT * FROM wp_posts WHERE id = %s", result.getId());

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String postDate = resultSet.getString("post_date");
                String postDateGmt = resultSet.getString("post_date_gmt");

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(id).isEqualTo(result.getId());
                softAssertions.assertThat(postDate).isEqualTo(result.getDate());
                softAssertions.assertThat(postDateGmt).isEqualTo(result.getDateGmt());
            }

        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Test
    public void deletedTest() throws SQLException {
        PostRequest body = createPostRequest();

        PostResponse result = RestAssured.given()
                .body(body)
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                .when()
                .post(URL_WP_V2_POSTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PostResponse.class);

        RestAssured.given()
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .when()
                .get(URL_WP_V2_POSTS + "/" + result.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(GetResponse.class);

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            String sql = String.format("SELECT * FROM wp_posts WHERE id = %s", result.getId());

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                SoftAssertions softAssertions = new SoftAssertions();
                softAssertions.assertThat(id).isNull();
            }

        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    private PostRequest createPostRequest() {
        return new PostRequest("2024-09-02T13:38:26", "2024-09-02T10:38:26",
                "%d0%bf%d1%80%d0%b8%d0%b2%d0%b5%d1%82-%d0%bc%d0%b8%d1%80","pending",
                "Вторая запись! Животные",
                "Добро пожаловать в WordPress. Это ваша вторая запись. " +
                        "Отредактируйте или удалите ее, затем начинайте создавать!",
                "Добро пожаловать в WordPress. Это ваша вторая запись. " +
                        "Отредактируйте или удалите ее, затем начинайте создавать!",1,0,
                "open", "open",false,"","standard",
                new Integer[]{2});
    }
}
