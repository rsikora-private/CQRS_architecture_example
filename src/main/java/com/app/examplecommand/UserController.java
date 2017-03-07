package com.app.examplecommand;

import com.cqrs.command.engine.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rsikora on 3/6/2017.
 */

@RestController
public class UserController {

    @Autowired
    private CommandDispatcher commandDispatcher;


    @PostMapping("/users")
    public String index() {
        commandDispatcher.dispatch(new UserAddCommand());
        return "Greetings from Spring Boot!";
    }
}
