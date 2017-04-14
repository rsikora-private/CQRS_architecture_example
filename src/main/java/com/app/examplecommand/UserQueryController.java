package com.app.examplecommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rsikora on 3/6/2017.
 */

@RestController("/users")
public class UserQueryController {

    @Autowired
    private UserReader userReader;

    @GetMapping
    public List<String> getUsers() {
        return userReader.getAll();
    }
}
