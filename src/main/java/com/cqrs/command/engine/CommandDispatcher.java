package com.cqrs.command.engine;

import java.io.Serializable;

/**
 * Created by robertsikora on 25.02.2017.
 */

public interface CommandDispatcher<C, R> {

     R dispatch(C command);
}
