package com.revature.feed.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.feed.config.JwtUtility;
import com.revature.feed.models.Like;
import com.revature.feed.models.Response;
import com.revature.feed.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("likeController")
@RequestMapping(value= "like")
@CrossOrigin(value = "http://3.12.71.16:9999/", allowCredentials = "true")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){ this.likeService = likeService;}

    @Autowired
    private Environment environment;

    @Autowired
    JwtUtility jwtUtility;

    //Create a Like
    //Angular send message we receive
    @PostMapping
    public Response createLike(@RequestBody Like like, @RequestHeader Map<String, String> headers){
        //Verify the JWT - Andrew
        DecodedJWT decoded = jwtUtility.verify(headers.get("jwt"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
        Like tempLike = this.likeService.createLike(like);
        if(tempLike != null){
            response = new Response(true, "Like has been added to post", like);
        }else{
            response = new Response(false, "Your like was not created", null);
        }
        return response;
    }
    //Get all Likes by PostID
    //Angular send message we receive
    @GetMapping("{postId}")
    public Response getLikeByPostId(@PathVariable Integer postId, @RequestHeader Map<String, String> headers){
        //Verify the JWT - Andrew
        DecodedJWT decoded = jwtUtility.verify(headers.get("jwt"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
    List<Like> like = this.likeService.getLikeByPostId(postId);
        if(like != null){
        response = new Response(true, "Here is the likes of this post", like);
    }else{
        response = new Response(false, "Post was not found",null);
    }
        return response;
}

    //Get like PostId and by UserId
    //Angular send message we receive
    @GetMapping("{postId}/{userId}")
    public Response getLikeByPostIdAndUserID(@PathVariable Integer postId, @PathVariable Integer userId,
                                             @RequestHeader Map<String, String> headers){
        //Verify the JWT - Andrew
        DecodedJWT decoded = jwtUtility.verify(headers.get("jwt"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
        boolean theyLikedIt = false;
       List<Like> like = this.likeService.getLikeByPostId(postId);
       Integer likeId = 0;
       for(Like a : like){
           if(a.getUserId().equals(userId)){
               theyLikedIt = true;
               likeId = a.getLikeId();
           }
       }
       if(!theyLikedIt){
           response = new Response(false, "They have not liked this post yet", false);
       }else{
           response = new Response(true, "They have already liked this post", likeId);
       }
       return response;
    }

    //Delete a Like
    //Angular send message we receive
    @DeleteMapping("{likeId}")
    public Response deleteLike(@PathVariable Integer likeId, @RequestHeader Map<String, String> headers){
        //Verify the JWT - Andrew
        DecodedJWT decoded = jwtUtility.verify(headers.get("jwt"));
        if(decoded == null){
            return new Response(false, "Invalid token", null);
        }

        Response response;
        Boolean deleteLike = this.likeService.deleteLike(likeId);
        if(deleteLike){
            response = new Response(true,"Your Like was removed", likeId);
        }else{
            response = new Response(false,"There was an error removing this like", likeId);
        }
        return response;
    }

}
