package com.revature.feed.controller;

import com.revature.feed.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("portController")
@RequestMapping(value= "api")
//@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class PortController {

    @Autowired
    private Environment environment;

    @GetMapping("port")
    public Response retrievePort(){
        return new Response(true, "hello, this came from port", environment.getProperty("local.server.port"));
    }
}
