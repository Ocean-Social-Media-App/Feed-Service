package com.ocean.services;

import com.ocean.models.Comment;
import com.ocean.models.Post;
import com.ocean.models.User;
import com.ocean.repository.CommentDao;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    CommentService commentService;

    @Mock
    CommentDao commentDao;
    @BeforeEach
    void setUp() {this.commentService = new CommentService(commentDao);
    }

    @Test
    void createCommentReturnNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Comment tempComm = new Comment(1,"Testing",post,tempUser);

        Mockito.when(commentDao.save(tempComm)).thenReturn(null);

        Comment acctualResult = this.commentService.createComment(tempComm);

        assertNotEquals(tempComm, acctualResult);
    }
    @Test
    void createCommentReturnNotNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Comment tempComm = new Comment(1,"Testing",post,tempUser);

        Mockito.when(commentDao.save(tempComm)).thenReturn(tempComm);

        Comment acctualResult = this.commentService.createComment(tempComm);

        assertEquals(tempComm, acctualResult);
    }

    @Test
    void findCommentByIdReturnNotNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Comment tempComm = new Comment(1,"Testing",post,tempUser);

        Mockito.when(commentDao.findById(tempComm.getCommentId())).thenReturn(java.util.Optional.of(tempComm));

        Comment acctualResult = this.commentService.findCommentById(1);
        assertEquals(tempComm, acctualResult);
    }


    @Test
    void findCommentByUserIdReturnNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        List<Comment> tempComm = new ArrayList<>();
        tempComm.add(new Comment(1,"Testing",post,tempUser));

        Mockito.when(commentDao.getCommentByUserId(tempUser.getUserId())).thenReturn(null);

        List<Comment> acctualResult = this.commentService.findCommentByUserId(tempUser.getUserId());
        assertNotEquals(tempComm, acctualResult);
    }
    @Test
    void findCommentByUserIdReturnNotNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        List<Comment> tempComm = new ArrayList<>();
        tempComm.add(new Comment(1,"Testing",post,tempUser));

        Mockito.when(commentDao.getCommentByUserId(tempUser.getUserId())).thenReturn(tempComm);

        List<Comment> acctualResult = this.commentService.findCommentByUserId(tempUser.getUserId());
        assertEquals(tempComm, acctualResult);
    }

    @Test
    void deleteCommentReturnNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Comment tempComm = new Comment(1,"Testing",post,tempUser);
        boolean inDb = false;

        Mockito.when(commentDao.existsById(tempComm.getCommentId())).thenReturn(false);

        boolean actualResult = this.commentService.deleteComment(tempComm.getCommentId());
        assertEquals(inDb, actualResult);
    }

    @Test
    void deleteCommentReturnNotNull() {
        User tempUser = new User("Shane", "Password");
        tempUser.setUserId(1);
        Post post = new Post("postPic","postText","null","postUrl",tempUser);
        post.setPostId(1);
        Comment tempComm = new Comment(1,"Testing",post,tempUser);
        boolean inDb = true;

        Mockito.when(commentDao.existsById(tempComm.getCommentId())).thenReturn(inDb);

        boolean actualResult = this.commentService.deleteComment(tempComm.getCommentId());
        Mockito.verify(commentDao, Mockito.atLeast(1)).deleteById(tempComm.getCommentId());
        assertEquals(inDb, actualResult);
    }

}