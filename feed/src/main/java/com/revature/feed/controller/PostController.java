package com.revature.feed.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.feed.config.JwtUtility;
import com.revature.feed.models.Post;
import com.revature.feed.models.Response;
import com.revature.feed.services.PostService;
import com.revature.feed.services.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("postController")
@RequestMapping(value= "post")
public class PostController {

    private PostService postService;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    public PostController(PostService postService, JwtUtility jwtUtility, RabbitService rabbitService){
        this.postService = postService;
        this.jwtUtility = jwtUtility;
        this.rabbitService = rabbitService;
    }

    //Create a Post
    @PostMapping
    public Response createPost(@RequestBody Post post, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }
        Response response;
        Post tempPost = this.postService.createPost(post);
        if(tempPost != null){
            response = new Response(true, "Post has been created", post);

            //Send message to user service let them know this userId just comment/create a post.
            rabbitService.postNotification(post);

        }else{
            response = new Response(false, "Post was not created", null);
        }
        return response;
    }

    @GetMapping("fave/{pageNumber}")
    public Response getPostFromFave(@PathVariable Integer pageNumber, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }
        Response response;

        //Gets userId from Token and passes it to RabbitMQ to get favorite list from user service.
        List<Integer> fave = rabbitService.requestListOfFollowers(decoded.getClaims().get("userId").asInt());
        List<Post> favePost = this.postService.selectPostForFav(pageNumber, fave);

        if(favePost != null){
            response = new Response(true,"Favorite list", favePost);
        }else{
            response = new Response(false,"You have reached the end", null);
        }
        return response;
    }

    //Read a post
    @GetMapping("{postId}")
    public Response lookForAPost(@PathVariable Integer postId, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
        Post post = this.postService.getPostById(postId);
        if(post != null){
            response = new Response(true, "Here is the post", post);
        }else{
            response = new Response(false, "Post was not found",null);
        }
        return response;
    }

    @GetMapping("comment/{postId}")
    public Response getComment(@PathVariable Integer postId, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }
        Response response;
        List<Post> comment = this.postService.getAllParentId(postId);
        //Need to validate info being returned
        return response = new Response(true, "Here are the comments", comment);
    }


    //Get Post by UserId
    @GetMapping("userId/{userId}")
    public Response lookForPostByUser(@PathVariable Integer userId, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }
        Response response;
        List<Post> post = this.postService.getPostByUserId(userId);
        //If statement checks size as array is always returned
        if (post.size() <= 0) {
            response = new Response(false, "Post was not found",null);
        } else {
            response = new Response(true, "Here is the post", post);
        }
        return response;
    }

    //Update a post
    @PutMapping
    public Response updatePost(@RequestBody Post post, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
        Post updatePost = this.postService.updatePost(post);
        if(updatePost != post){
            response = new Response(false,"Post has not been updated", post);
        }else{
            response = new Response(true,"Post has been updated",post);
        }
        return response;
    }

    //Delete a post
    @DeleteMapping("{postId}")
    public Response deletePost(@PathVariable Integer postId, @RequestHeader Map<String, String> headers){
        //Verify the JWT
        DecodedJWT decoded = jwtUtility.verify(headers.get("authorization"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
        Post deletePost = this.postService.deletePost(postId);
        if(deletePost != null){
            response = new Response(true,"Post was deleted", deletePost);
        }else{
            response = new Response(false,"There was an error deleting this post", null);
        }
        return response;
    }
}
