package com.ocean.controller;

import com.ocean.models.Like;
import com.ocean.models.Post;
import com.ocean.models.Response;
import com.ocean.models.User;
import com.ocean.services.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LikeControllerTest {
    LikeController likeController;

    @Mock
    LikeService likeService;

    @BeforeEach
    void setUp() {
        this.likeController = new LikeController(likeService);
    }

    @Test
    void createLikeReturnNull() {
        //assign
        Like like = new Like();
        Response expectedResult = new Response(false, "Your like was not created", null);

        //Mock
        Mockito.when(likeService.createLike(like)).thenReturn(null);
        //act

        Response actualResult = this.likeController.createLike(like);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void createLikeReturnNotNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Like like = new Like(4,user,post);
        Response expectedResult = new Response(true, "Like has been added to post", like);

        //Mock
        Mockito.when(likeService.createLike(like)).thenReturn(like);
        //act
        Response actualResult = this.likeController.createLike(like);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void getLikeByPostIdReturnNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Like like = new Like(4,user,post);
        Response expectedResult = new Response(false, "Post was not found",null);
        //Mock
        Mockito.when(likeService.getLikeByPostId(1)).thenReturn(null);
        //act
        Response actualResult = this.likeController.getLikeByPostId(1);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void getLikeByPostIdReturnNotNull() {
        //assign
        User user = new User("Shane", "Password");
        Post post = new Post("postPic","postText","null","postUrl",user);
        List<Like> like = new ArrayList<>();
        like.add(new Like(4,user,post));
        Response expectedResult = new Response(true, "Here is the likes of this post", like);
        //Mock
        Mockito.when(likeService.getLikeByPostId(1)).thenReturn(like);
        //act
        Response actualResult = this.likeController.getLikeByPostId(1);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void deleteLikeReturnNull() {
        //assign
        Integer likeId = 1;
        Response expectedResult = new Response(false,"There was an error removing this like", likeId);

        //Mock
        Mockito.when(likeService.deleteLike(likeId)).thenReturn(false);
        //act
        Response actualResult = this.likeController.deleteLike(likeId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void deleteLikeReturnNotNull() {
        //assign
        User user = new User("Shane", "Password");
        Post post = new Post("postPic","postText","null","postUrl",user);
        Like like = new Like(4,user,post);
        Integer likeId = 4;
        Response expectedResult = new Response(true,"Your Like was removed", likeId);

        //Mock
        Mockito.when(likeService.deleteLike(likeId)).thenReturn(true);
        //act
        Response actualResult = this.likeController.deleteLike(likeId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
}
