package com.ocean.controller;

import com.ocean.models.Comment;
import com.ocean.models.Post;
import com.ocean.models.Response;
import com.ocean.models.User;
import com.ocean.services.CommentService;
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
class CommentControllerTest {
    CommentController commentController;

    @Mock
    CommentService commentService;

    @BeforeEach
    void setUp() {
        this.commentController = new CommentController(commentService);
    }

    @Test
    void createCommentReturnNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment comment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(false, "Your comment was not created", null);

        //Mock
        Mockito.when(commentService.createComment(comment)).thenReturn(null);
        //act

        Response actualResult = this.commentController.createComment(comment);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void createCommentReturnNotNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment comment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(true, "Comment has been added to post", comment);

        //Mock
        Mockito.when(commentService.createComment(comment)).thenReturn(comment);
        //act

        Response actualResult = this.commentController.createComment(comment);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void lookForCommentByIdReturnNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment comment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(false, "Could not find your comment", null);

        //Mock
        Mockito.when(commentService.findCommentById(comment.getCommentId())).thenReturn(null);
        //act

        Response actualResult = this.commentController.lookForCommentById(comment.getCommentId());
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void lookForCommentByIdReturnNotNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment tempComment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(true, "Here is the comment", tempComment);

        //Mock
        Mockito.when(commentService.findCommentById(tempComment.getCommentId())).thenReturn(tempComment);
        //act

        Response actualResult = this.commentController.lookForCommentById(tempComment.getCommentId());
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void lookForCommentByPostIdReturnNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment tempComment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(false, "Could not find your comments", null);

        //Mock
        Mockito.when(commentService.findCommentByPostId(post.getPostId())).thenReturn(null);
        //act

        Response actualResult = this.commentController.lookForCommentByPostId(post.getPostId());
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void lookForCommentByPostIdReturnNotNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        List<Comment> tempComment = new ArrayList<>();
        tempComment.add(new Comment(1,"hello test",post,user));
        Response expectedResult = new Response(true, "Here are the comments", tempComment);

        //Mock
        Mockito.when(commentService.findCommentByPostId(post.getPostId())).thenReturn(tempComment);
        //act

        Response actualResult = this.commentController.lookForCommentByPostId(post.getPostId());
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void lookForCommentByUserIdReturnNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        List<Comment> tempComment = new ArrayList<>();
        tempComment.add(new Comment(1,"hello test",post,user));
        Response expectedResult = new Response(false, "Could not find your comments", null);

        //Mock
        Mockito.when(commentService.findCommentByUserId(user.getUserId())).thenReturn(null);
        //act

        Response actualResult = this.commentController.lookForCommentByUserId(user.getUserId());
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void lookForCommentByUserIdReturnNotNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        List<Comment> tempComment = new ArrayList<>();
        tempComment.add(new Comment(1,"hello test",post,user));
        Response expectedResult = new Response(true, "Here are their comments", tempComment);

        //Mock
        Mockito.when(commentService.findCommentByUserId(user.getUserId())).thenReturn(tempComment);
        //act

        Response actualResult = this.commentController.lookForCommentByUserId(user.getUserId());
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void updateCommentReturnNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment tempComment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(false, "Update to your comment failed", null);

        //Mock
        Mockito.when(commentService.updateComment(tempComment)).thenReturn(null);
        //act

        Response actualResult = this.commentController.updateComment(tempComment);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void updateCommentReturnNotNull() {
        //assign
        User user = new User();
        user.setUserId(1);
        Post post = new Post();
        post.setPostId(2);
        Comment tempComment = new Comment(1,"hello test",post,user);
        Response expectedResult = new Response(true, "Here is the  updated comment", tempComment);

        //Mock
        Mockito.when(commentService.updateComment(tempComment)).thenReturn(tempComment);
        //act

        Response actualResult = this.commentController.updateComment(tempComment);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }

    @Test
    void deleteCommentReturnNull() {
        //assign
        Integer commentId = 1;
        Response expectedResult = new Response(false, "There was an error deleting this comment", null);

        //Mock
        Mockito.when(commentService.deleteComment(commentId)).thenReturn(false);
        //act

        Response actualResult = this.commentController.deleteComment(commentId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
    @Test
    void deleteCommentReturnNotNull() {
        //assign
        Integer commentId = 1;
        Boolean tempComment = true;
        Response expectedResult = new Response(true, "Your comment has been deleted", tempComment);

        //Mock
        Mockito.when(commentService.deleteComment(commentId)).thenReturn(true);
        //act

        Response actualResult = this.commentController.deleteComment(commentId);
        //assert
        assertEquals(expectedResult.toString(), actualResult.toString());
    }
}
