package com.ocean.services;

import com.ocean.models.Like;
import com.ocean.models.Post;
import com.ocean.models.User;
import com.ocean.repository.LikeDao;
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
class LikeServiceTest {
    LikeService likeService;

    @Mock
    LikeDao likeDao;

    @BeforeEach
    void setUp() { this.likeService =new LikeService(likeDao);
    }

    @Test
    void createLikeReturnNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Like tempLike = new Like(1,tempUser,post);

        Mockito.when(likeDao.save(tempLike)).thenReturn(null);

        Like actualResult = this.likeService.createLike(tempLike);
        assertNotEquals(tempLike, actualResult);
    }
    @Test
    void createLikeReturnNoNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Like tempLike = new Like(1,tempUser,post);

        Mockito.when(likeDao.save(tempLike)).thenReturn(tempLike);

        Like actualResult = this.likeService.createLike(tempLike);
        assertEquals(tempLike, actualResult);
    }

    @Test
    void getLikeByPostIdReturnNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        List<Like> tempLike = new ArrayList<>();
        tempLike.add(new Like(1,tempUser,post));

        Mockito.when(likeDao.getLikesByPostId(post.getPostId())).thenReturn(null);
        List<Like> actualResult = this.likeService.getLikeByPostId(post.getPostId());
        assertNotEquals(tempLike, actualResult);
    }
    @Test
    void getLikeByPostIdReturnNotNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        List<Like> tempLike = new ArrayList<>();
        tempLike.add(new Like(1,tempUser,post));

        Mockito.when(likeDao.getLikesByPostId(post.getPostId())).thenReturn(tempLike);
        List<Like> actualResult = this.likeService.getLikeByPostId(post.getPostId());
        assertEquals(tempLike, actualResult);
    }

    @Test
    void deleteLikeReturnNull() {
        Integer likeId = 1;
        boolean expectedResult = false;

        Mockito.when(likeDao.existsById(likeId)).thenReturn(true);
        boolean actualResult = this.likeService.deleteLike(likeId);

        assertNotEquals(expectedResult, actualResult);
    }

    @Test
    void deleteLikeReturnNotNull() {
        Integer likeId = 1;
        boolean expectedResult = true;

        Mockito.when(likeDao.existsById(likeId)).thenReturn(expectedResult);
        boolean actualResult = this.likeService.deleteLike(likeId);

        assertEquals(expectedResult, actualResult);
        Mockito.verify(likeDao, Mockito.times(1)).deleteById(likeId);
    }
}