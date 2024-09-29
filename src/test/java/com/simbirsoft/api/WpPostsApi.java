package com.simbirsoft.api;

import com.simbirsoft.dto.PostRequest;
import com.simbirsoft.dto.PostResponse;
import com.simbirsoft.helpers.ConfHelpers;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

public class WpPostsApi {

    private static final String URL_WP_V2_POSTS = "http://localhost:8000/wp-json/wp/v2/posts";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    private static final RequestSpecification specification = new RequestSpecBuilder()
            .addHeader(AUTHORIZATION, "Basic " + ConfHelpers.getProperty("token"))
            .setContentType(CONTENT_TYPE_APPLICATION_JSON)
            .setBaseUri(URL_WP_V2_POSTS)
            .build();

    public PostResponse postWpPost(PostRequest body) {
        return RestAssured.given(specification)
                .body(body)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PostResponse.class);
    }

    public PostResponse[] getAllWpPosts() {
        return RestAssured.given(specification)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostResponse[].class);
    }

    public PostResponse getWpPostById(Integer id) {
        return RestAssured.given(specification)
                .when()
                .get("/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostResponse.class);
    }

    public PostResponse deleteWpPost(Integer id) {
        return RestAssured.given(specification)
                .when()
                .delete("/" + id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostResponse.class);
    }

}
