/*
package com.revature.feed.services;

import com.revature.feed.models.Post;
import com.revature.feed.repository.PostDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {
    PostService postService;

    @Mock
    PostDao postDao;

    @BeforeEach
    void setUp() { this.postService = new PostService(postDao);
    }

    @Test
    void selectPostForFav() {
        Integer page = 1;
        Integer userId = 1;

        List<Integer> fave = new ArrayList<>();
        fave.add(1);
        fave.add(2);
        fave.add(3);


        List<Post> examplePost = new ArrayList<>();
        examplePost.add(new Post(1,null,"www.www", "Text", null,"Url", 1));
        examplePost.add(new Post(2,1,"www.www", "Text", null,"Url", 2));
        examplePost.add(new Post(3,null,"www.www", "Text", null,"Url", 3));


        List<Post> expectedResult =new ArrayList<>();
        expectedResult.add(new Post(1,null,"www.www", "Text", null,"Url", 1));
       // expectedResult.add(new Post(3,null,"www.www", "Text", null,"Url", 3));

        Mockito.when(this.postDao.getPostByUserId(userId)).thenReturn(null);
        List<Post> acutalResult = this.postService.selectPostForFav(page, fave);

        assertEquals(expectedResult, acutalResult);
    }
}*/
