package com.revature.feed.controller;

import com.revature.feed.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("portController")
@RequestMapping(value= "port")
@CrossOrigin(value = "http://3.12.71.16:9999/", allowCredentials = "true")
public class PortController {

    @Autowired
    private Environment environment;

    @GetMapping
    public Response retrievePort(){
        return new Response(true, "hello, this came from port", environment.getProperty("local.server.port"));
    }
}
