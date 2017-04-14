package com.app.examplecommand;

import com.cqrs.command.engine.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rsikora on 3/6/2017.
 */

@RestController("/users")
public class UserCommandController {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping
    public String addUser() {
        commandDispatcher.dispatch(new UserAddCommand());
        return "Greetings from Spring Boot!";
    }
}
