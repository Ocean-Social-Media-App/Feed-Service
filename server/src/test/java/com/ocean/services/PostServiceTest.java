package com.ocean.services;

import com.ocean.models.Post;
import com.ocean.models.Response;
import com.ocean.models.User;
import com.ocean.repository.PostDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    PostService postService;

    @Mock
    PostDao postDao;

    @BeforeEach
    void setUp() { this.postService = new PostService(postDao);
    }

    @Test
    void createPostReturnNull() {
    //assign
    User tempUser = new User("Shane", "Password");
    tempUser.setUserId(1);
    Post post = new Post("postPic","postText","null","postUrl",tempUser);
    Post expectedResult = new Post();
    //Mock
    Mockito.when(postDao.save(post)).thenReturn(expectedResult);
    //act
    Post actualResult = this.postService.createPost(post);
    //assert
    assertEquals(expectedResult, actualResult);
    }

    @Test
    void createPostReturnNotNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        Post expectedResult = post;
        //Mock
        Mockito.when(postDao.save(post)).thenReturn(expectedResult);
        //act
        Post actualResult = this.postService.createPost(post);
        //assert
        assertEquals(expectedResult, actualResult);

    }

    @Test
    void getPostByUserIdReturnNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        List<Post> expectedResult = new ArrayList<>();
        //Mock
        Mockito.when(postDao.getPostByUserId(tempUser.getUserId())).thenReturn(expectedResult);
        //act
        List<Post> actualResult = this.postService.getPostByUserId(tempUser.getUserId());
        //assert
        assertEquals(expectedResult, actualResult);
    }
    @Test
    void getPostByUserIdReturnNotNull() {
        //assign
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        List<Post> expectedResult = new ArrayList<>();
        expectedResult.add(post);
        //Mock
        Mockito.when(postDao.getPostByUserId(tempUser.getUserId())).thenReturn(expectedResult);
        //act
        List<Post> actualResult = this.postService.getPostByUserId(tempUser.getUserId());
        //assert
        assertEquals(expectedResult, actualResult);
    }

}