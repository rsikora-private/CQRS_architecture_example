package com.app.query;

import com.cqrs.query.Reader;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rsikora on 3/16/2017.
 */

@Reader
public class UserReader {

    public List<String> getAll() {
        return Arrays.asList("a", "b");
    }
}
