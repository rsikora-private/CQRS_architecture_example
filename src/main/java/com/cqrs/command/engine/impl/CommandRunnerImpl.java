package com.cqrs.command.engine.impl;

import com.cqrs.command.CommandHandler;
import com.cqrs.command.engine.CommandRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Component
public class CommandRunnerImpl<C, R> implements CommandRunner<C, R>, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Map<Class<?>, CommandHandler<C, R>> handlers = new HashMap<>();

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public R run(C command) {
        final CommandHandler<C, R> commandHandler = handlers.get(command.getClass());
        Assert.state(commandHandler != null, String.format("CQRS engine doesn't have any knowledge about command " +
                "handler for %s command", command.getClass()));
        return commandHandler.handle(command);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();
        String[] commandHandlersNames = beanFactory.getBeanNamesForType(CommandHandler.class);
        for (String beanName : commandHandlersNames) {
            BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);
            final CommandHandler commandHandler1 = (CommandHandler) beanFactory.getBean(beanName);
            try {
                Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass), commandHandler1);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getHandledCommandType(Class<?> clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, CommandHandler.class);
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parametrized = (ParameterizedType) type;
                if (expectedRawType.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}
