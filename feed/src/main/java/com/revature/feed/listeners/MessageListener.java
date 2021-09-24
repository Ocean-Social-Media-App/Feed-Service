package com.revature.feed.listeners;

import com.revature.feed.config.MQConfig;

import com.revature.feed.models.RabbitMessage;
import com.revature.feed.services.LikeService;
import com.revature.feed.services.PostService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

public class MessageListener {

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @RabbitListener(queues = MQConfig.POST)
    public void postListener(RabbitMessage message){



    }
/*        if(message.getOperation().equals("createPost")){
            this.postService.createPost(message.getPost());
        }
*//*        if(message.getOperation().equals("getAllPost")){
            ///////////////////need to look at this one
            this.postService.getAllPosts(1);
        }*//*
        if(message.getOperation().equals("lookForAPost")){
            this.postService.getPostById(message.getPost().getPostId());
        }
        if(message.getOperation().equals("lookForPostByUser")){
            this.postService.getPostByUserId(message.getPost().getUserId());
        }
        if(message.getOperation().equals("updatePost")){
            this.postService.updatePost(message.getPost());
        }
        if(message.getOperation().equals("deletePost")){
            this.postService.deletePost(message.getPost().getPostId());
        }
    }

    @RabbitListener(queues = MQConfig.LIKE)
    public void likeListener(RabbitMessage message){
        if(message.getOperation().equals("createLike")){
            this.likeService.createLike(message.getLike());
        }
        if(message.getOperation().equals("getLikeByPostId")){
            this.likeService.getLikeByPostId(message.getPost().getPostId());
        }
        if(message.getOperation().equals("getLikeByPostIdAndUserID")){

            /////need to check this one
            this.likeService.getLikeByPostId(message.getPost().getPostId());
        }
        if(message.getOperation().equals("deleteLike")){
            this.likeService.deleteLike(message.getLike().getLikeId());
        }

    }*/

}
