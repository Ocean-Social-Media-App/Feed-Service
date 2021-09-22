package com.revature.feed.controller;

import com.revature.feed.models.Comment;
import com.revature.feed.models.Response;
import com.revature.feed.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("commentController")
@RequestMapping(value= "api")
//@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){ this.commentService = commentService;}

    @Autowired
    private Environment environment;

    //Create a Comment
    @PostMapping("comment")
    public Response createComment(@RequestBody Comment comment){
        Response response;
        Comment tempComment = this.commentService.createComment(comment);
        if(tempComment != null){
            response = new Response(true, "Comment has been added to post", comment);
        }else{
            response = new Response(false, "Your comment was not created", null);
        }
        return response;
    }

    //Read a Comment
    @GetMapping("comment/{commentId}")
    public Response lookForCommentById(@PathVariable Integer commentId){
        Response response;
        Comment tempComment = this.commentService.findCommentById(commentId);
        if(tempComment != null){
        response = new Response(true, "Here is the comment", tempComment);
        }else{
        response = new Response(false, "Could not find your comment", null);
        }
        return response;
    }

    @GetMapping("comment/post/{postId}")
    public Response lookForCommentByPostId(@PathVariable Integer postId){
        Response response;
        List<Comment> tempComment = this.commentService.findCommentByPostId(postId);
        if(tempComment != null){
            response = new Response(true, "Here are the comments", tempComment);
        }else{
            response = new Response(false, "Could not find your comments", null);
        }
        return response;
    }

    //Get Comment by UserId
    @GetMapping("comment/user/{userId}")
    public Response lookForCommentByUserId(@PathVariable Integer userId){
        Response response;
        List<Comment> tempComment = this.commentService.findCommentByUserId(userId);
        if(tempComment != null){
            response = new Response(true, "Here are their comments", tempComment);
        }else{
            response = new Response(false, "Could not find your comments", null);
        }
        return response;
    }

    //Update a Comment
    @PutMapping("comment")
    public Response updateComment(@RequestBody Comment comment){
        Response response;
        Comment tempComment = this.commentService.updateComment(comment);
        if(tempComment != null){
            response = new Response(true, "Here is the  updated comment", tempComment);
        }else{
            response = new Response(false, "Update to your comment failed", null);
        }
        return response;
    }

    //Delete a Comment
    @DeleteMapping("comment/{commentId}")
    public Response deleteComment(@PathVariable Integer commentId){
        Response response;
        Boolean tempComment = this.commentService.deleteComment(commentId);
        if(tempComment == true){
            response = new Response(true, "Your comment has been deleted", tempComment);
        }else{
            response = new Response(false, "There was an error deleting this comment", null);
        }
        return response;
    }

}
