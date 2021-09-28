package com.revature.feed.services;

import com.revature.feed.config.MQConfig;
import com.revature.feed.models.Like;
import com.revature.feed.models.Post;
import com.revature.feed.models.RabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rabbitService")
public class RabbitService {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private PostService postService;

    public String likeNotification(Like like){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.LIKE,
                //Send RabbitMessage object to user-server with postId,
                new RabbitMessage(like.getUserId(), like.getPost().getPostId(), like.getPost().getUserId())

        );
        return "Like Notification success!";
    }

    public String postNotification(Post post){
        if(post.getPostParentId() != null ) {
            //is a post or comment
            template.convertAndSend(
                    MQConfig.EXCHANGE,
                    MQConfig.POST,
                    new RabbitMessage(postService.getPostById(post.getPostParentId()).getUserId(), post.getPostId(), post.getUserId())
            );
            return "Post Notification success!";
        }
        return "No notification needed its a post";
    }

    public List<Integer> requestListOfFollowers(Integer userId){
        return template.convertSendAndReceiveAsType(
                MQConfig.EXCHANGE,
                MQConfig.USER,
                 userId,
                new ParameterizedTypeReference<List<Integer>>(){

                }
        );
    }



}

