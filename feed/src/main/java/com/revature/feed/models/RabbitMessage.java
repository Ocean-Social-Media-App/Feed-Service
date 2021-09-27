package com.revature.feed.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RabbitMessage {
    Integer userId;
    Integer postId;
}
