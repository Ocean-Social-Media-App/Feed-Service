package com.revature.feed.controller;

import com.revature.feed.models.Post;
import com.revature.feed.models.Response;
import com.revature.feed.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("postController")
@RequestMapping(value= "post")
@CrossOrigin(value = "http://3.12.71.16:9999/", allowCredentials = "true")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){ this.postService = postService;}


    //Create a Post
    //Angular send message we receive
    @PostMapping
    public Response createPost(@RequestBody Post post){
        Response response;
        Post tempPost = this.postService.createPost(post);
        if(tempPost != null){
            response = new Response(true, "Post has been created", post);
        }else{
            response = new Response(false, "Post was not created", null);
        }
        return response;
    }


    //Read a post
    //Angular send message we receive
    @GetMapping("{postId}")
    public Response lookForAPost(@PathVariable Integer postId){
        Response response;
        Post post = this.postService.getPostById(postId);
        if(post != null){
            response = new Response(true, "Here is the post", post);
        }else{
            response = new Response(false, "Post was not found",null);
        }
        return response;
    }

    //Get Post by UserId
    //Angular send message we receive
    @GetMapping("userId/{userId}")
    public Response lookForPostByUser(@PathVariable Integer userId){
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
    //Angular send message we receive
    @PutMapping
    public Response updatePost(@RequestBody Post post){
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
    //Angular send message we receive
    @DeleteMapping("{postId}")
    public Response deletePost(@PathVariable Integer postId){
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
