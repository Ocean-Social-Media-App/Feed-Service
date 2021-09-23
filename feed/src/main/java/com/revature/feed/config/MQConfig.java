package com.revature.feed.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class MQConfig {

    public static final String POST ="post_queue";
    public static final String LIKE ="like_queue";
    public static final String EXCHANGE = "exchange";

    @Bean
    public Queue post() {return new Queue(POST);}

    @Bean
    public Queue like() {return new Queue(LIKE);}

    @Bean
    public TopicExchange exchange(){return new TopicExchange(EXCHANGE);}

    @Bean
    public Binding postBinding(){
        return BindingBuilder
                .bind(post())
                .to(exchange())
                .with(POST);
    }

    @Bean
    public Binding likeBinding(){
        return BindingBuilder
                .bind(like())
                .to(exchange())
                .with(LIKE);
    }


    @Bean
    public MessageConverter messageConverter(){return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
