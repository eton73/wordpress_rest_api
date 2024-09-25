package com.simbirsoft.tests;

import com.simbirsoft.dto.PostRequest;
import com.simbirsoft.dto.PostResponse;
import com.simbirsoft.repository.model.WpPostModel;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

public class WpPostsTest extends BaseWpTest {

    @Test
    public void postTest() {
        PostResponse result = api.postWpPost(createPostRequest());

        WpPostModel modelDb = repository.getPostById(result.getId());

        assertEquals(new SoftAssertions(), result, modelDb);

        repository.deletePostById(result.getId());
    }
    
    @Test
    public void getTest() {
        PostResponse[] result = api.getAllWpPosts();

        List<WpPostModel> modelsDb = repository.getAllPosts();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(result.length).isEqualTo(modelsDb.size());
        for (int i = 0; i < result.length; i++) {
            assertEquals(softAssertions, result[i], modelsDb.get(i));
        }
    }

    @Test
    public void getByIDTest() {
        PostResponse result = api.getWpPostById(18);

        WpPostModel modelDb = repository.getPostById(18);

        assertEquals(new SoftAssertions(), result, modelDb);
    }

    @Test
    public void deletedTest() {
        PostResponse result = api.postWpPost(createPostRequest());

        api.deleteWpPost(result.getId());

        WpPostModel modelDb = repository.getPostById(result.getId());

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(modelDb.getId()).isNull();
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

    private void assertEquals(SoftAssertions softAssertions, PostResponse result, WpPostModel modelDb) {
        softAssertions.assertThat(result.getId()).isEqualTo(modelDb.getId());
        softAssertions.assertThat(result.getDate()).isEqualTo(modelDb.getPost_date());
        softAssertions.assertThat(result.getDateGmt()).isEqualTo(modelDb.getPost_date_gmt());
    }

}
