package com.cqrs.query;

import org.springframework.transaction.annotation.Transactional;
import java.lang.annotation.*;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional(readOnly = true)
public @interface Reader {
}
