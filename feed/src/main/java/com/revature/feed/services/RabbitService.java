package com.revature.feed.services;

import com.revature.feed.config.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rabbitService")
public class RabbitService {

    @Autowired
    private RabbitTemplate template;

    public String likeNotification(Integer userId){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.LIKE,
                userId + "just liked your post."

        );
        return "Like Notification success!";
    }

    public String postNotification(Integer userId){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.POST,
                userId + "just commented on your post."

        );
        return "Post Notification success!";
    }

    public String requestListOfFollowers(Integer userId){
        template.convertAndSend(
                MQConfig.EXCHANGE,
                MQConfig.USER,
                "Please send us the follower list of this userId: " + userId
        );
        return "request to get follower list sent.";
    }



}

