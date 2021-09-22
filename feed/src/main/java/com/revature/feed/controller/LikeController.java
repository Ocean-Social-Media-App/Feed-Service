package com.revature.feed.controller;

import com.revature.feed.models.Like;
import com.revature.feed.models.Response;
import com.revature.feed.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("likeController")
@RequestMapping(value= "like")
@CrossOrigin(value = "http://3.12.71.16:9999/", allowCredentials = "true")
public class LikeController {
    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService){ this.likeService = likeService;}

    @Autowired
    private Environment environment;

    //Create a Like
    @PostMapping
    public Response createLike(@RequestBody Like like){
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
    @GetMapping("{postId}")
    public Response getLikeByPostId(@PathVariable Integer postId){
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
    @GetMapping("{postId}/{userId}")
    public Response getLikeByPostIdAndUserID(@PathVariable Integer postId, @PathVariable Integer userId){
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
    @DeleteMapping("{likeId}")
    public Response deleteLike(@PathVariable Integer likeId){
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
