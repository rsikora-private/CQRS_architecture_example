package com.cqrs.command;

import java.lang.annotation.*;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CQRSCommand {

    boolean async() default false;
}
