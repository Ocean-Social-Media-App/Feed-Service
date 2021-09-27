package com.revature.feed.controller;

import com.revature.feed.config.JwtUtility;
import com.revature.feed.models.Post;
import com.revature.feed.models.Response;
import com.revature.feed.services.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    PostController postController;

    @Mock
    PostService postService;

    @Mock
    JwtUtility jwtUtility;

    @BeforeEach
    void setUp() {
        this.postController = new PostController(postService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPost() {

        //ASSIGN
        Post post = new Post();
        post.setPostId(1);
        post.setPostText("This is a post!");
        post.setUserId(1);
        
        Response expected = new Response(true, "Post has been created", post);

        //ACT

        //ASSERT
    }

    @Test
    void lookForAPost() {
    }

    @Test
    void lookForPostByUser() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }
}