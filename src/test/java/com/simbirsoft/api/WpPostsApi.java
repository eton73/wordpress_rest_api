package com.simbirsoft.api;

import com.simbirsoft.dto.GetResponse;
import com.simbirsoft.dto.PostRequest;
import com.simbirsoft.dto.PostResponse;
import com.simbirsoft.helpers.ConfHelpers;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

public class WpPostsApi {

    private static final String URL_WP_V2_POSTS = "http://localhost:8000/wp-json/wp/v2/posts";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    public PostResponse postWpPost(PostRequest body) {
        return RestAssured.given()
                .body(body)
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON)
                .when()
                .post(URL_WP_V2_POSTS)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PostResponse.class);
    }

    public GetResponse[] getAllWpPosts() {
        return RestAssured.given()
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .when()
                .get(URL_WP_V2_POSTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(GetResponse[].class);
    }

    public GetResponse getWpPostById(Integer id) {
        return RestAssured.given()
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .when()
                .get(URL_WP_V2_POSTS + "/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(GetResponse.class);
    }

    public GetResponse deleteWpPost(Integer id) {
        return RestAssured.given()
                .header(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
                .when()
                .delete(URL_WP_V2_POSTS + "/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(GetResponse.class);
    }

}
