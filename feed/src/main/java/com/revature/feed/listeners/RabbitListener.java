/*
package com.revature.feed.listeners;

import com.revature.feed.config.MQConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RabbitListener {

    public static List<Integer> listFave;

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = MQConfig.FOLLOWINGS)
    public List<Integer> getListOfFollowersFromUserService(Set<Integer> followersId) {
        listFave = new ArrayList<>(followersId);
        return listFave;
    }
}
*/
