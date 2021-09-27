package com.revature.feed.services;

import com.revature.feed.config.MQConfig;
import com.revature.feed.models.Like;
import com.revature.feed.models.Post;
import com.revature.feed.models.RabbitMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rabbitService")
public class RabbitService {

    @Autowired
    private RabbitTemplate template;

    public String likeNotification(Like like){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.LIKE,
                //Send RabbitMessage object to user-server with postId,
                new RabbitMessage(like.getUserId() + " just liked a post", like.getPost(), like)

        );
        return "Like Notification success!";
    }

    public String postNotification(Post post){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.POST,
                new RabbitMessage(post.getUserId() + " just comment a post", post, null)
        );
        return "Post Notification success!";
    }

    public String requestListOfFollowers(Integer userId){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.USER,
                 userId
        );
        return "request to get follower list sent.";
    }



}

