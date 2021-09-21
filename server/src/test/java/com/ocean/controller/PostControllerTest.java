package com.ocean.controller;


import com.ocean.models.Post;
import com.ocean.models.Response;
import com.ocean.models.User;
import com.ocean.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    PostController postController;

    @Mock
    PostService postService;

    @BeforeEach
    void setUp() {
        this.postController = new PostController(postService);
    }

    @Test
    void createPostReturnNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Response expectedResult = new Response(false, "Post was not created", null);

        //Mock
        Mockito.when(postService.createPost(post)).thenReturn(null);
        //act

        Response actualResult = this.postController.createPost(post);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void createPostReturnNotNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Response expectedResult = new Response(true, "Post has been created", post);

        //Mock
        Mockito.when(postService.createPost(post)).thenReturn(post);
        //act

        Response actualResult = this.postController.createPost(post);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void lookForAPostReturnNull() {
        //assign
        Integer postId = 1;
        Response expectedResult = new Response(false, "Post was not found",null);

        //Mock
        Mockito.when(postService.getPostById(postId)).thenReturn(null);
        //act

        Response actualResult = this.postController.lookForAPost(postId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void lookForAPostReturnNotNull() {
        //assign
        Integer postId = 1;
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Response expectedResult = new Response(true, "Here is the post", post);

        //Mock
        Mockito.when(postService.getPostById(postId)).thenReturn(post);
        //act

        Response actualResult = this.postController.lookForAPost(postId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

   @Test
    void lookForPostByUserReturnNull() {
        //assign
       Integer userId = 1;
       List<Post> post = new ArrayList<>();
       Response expectedResult = new Response(false, "Post was not found",null);

       //Mock
       Mockito.when(postService.getPostByUserId(userId)).thenReturn(post);
       //act

       Response actualResult = this.postController.lookForPostByUser(userId);
       //assert
       assertEquals(expectedResult.toString(), actualResult.toString());
   }
    @Test
    void lookForPostByUserReturnNotNull() {
        //assign
        Integer userId = 1;
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        List<Post> post = new ArrayList<>();
        post.add(new Post("postPic","postText","null","postUrl",tempUser));
        Response expectedResult = new Response(true, "Here is the post", post);

        //Mock
        Mockito.when(postService.getPostByUserId(userId)).thenReturn(post);
        //act

        Response actualResult = this.postController.lookForPostByUser(userId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void updatePostReturnNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Response expectedResult = new Response(false,"Post has not been updated", post);

        //Mock
        Mockito.when(postService.updatePost(post)).thenReturn(null);
        //act

        Response actualResult = this.postController.updatePost(post);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void updatePostReturnNotNull() {
        //assign
        Integer userId = 1;
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Response expectedResult = new Response(true,"Post has been updated",post);

        //Mock
        Mockito.when(postService.updatePost(post)).thenReturn(post);
        //act

        Response actualResult = this.postController.updatePost(post);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void deletePostReturnNull() {
        //assign
        Integer deletePost = 1;
        Response expectedResult = new Response(false,"There was an error deleting this post", null);

        //Mock
        Mockito.when(postService.deletePost(deletePost)).thenReturn(null);
        //act

        Response actualResult = this.postController.deletePost(deletePost);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void deletePostReturnNotNull() {
        //assign
        Integer deletePost = 1;
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Response expectedResult = new Response(true,"Post was deleted", post);

        //Mock
        Mockito.when(postService.deletePost(deletePost)).thenReturn(post);
        //act

        Response actualResult = this.postController.deletePost(deletePost);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
}