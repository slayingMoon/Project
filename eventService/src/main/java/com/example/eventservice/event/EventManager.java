package com.example.eventservice.event;

import com.example.eventservice.InvokeService;
import com.example.eventservice.event.annotation.EventClass;
import com.example.eventservice.event.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventManager {
    private static final String BASE_PACKAGE = "com.example.eventservice";
    @Autowired
    ApplicationContext applicationContext;

    private final Map<Class<?>, List<Method>> eventHandlerMap = new HashMap<>();

    @PostConstruct
    void init() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(EventClass.class));
        ClassLoader classLoader = EventManager.class.getClassLoader();
        for (BeanDefinition candidateComponent : scanner.findCandidateComponents(BASE_PACKAGE)) {
            try {
                eventHandlerMap.put(ClassUtils.forName(candidateComponent.getBeanClassName(), classLoader), new ArrayList<>());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        String[] allBeans = applicationContext.getBeanNamesForAnnotation(Component.class);
        for (String beanName : allBeans) {
            if (beanName.equals("invokeService") || beanName.equals("eventManager")) {
                continue;
            }
            Object obj = applicationContext.getBean(beanName);

            Class<?> objClz = obj.getClass();

            for (Method m : objClz.getDeclaredMethods()) {
                if (m.isAnnotationPresent(EventHandler.class)) {
                    Class<?> type = m.getParameterTypes()[0];
                    eventHandlerMap.get(type).add(m);
                }
            }
        }
    }

    public <E> void sendEvent (E event) {
        if (eventHandlerMap.containsKey(event.getClass())) {
            eventHandlerMap.get(event.getClass()).forEach(eventHandler -> {
                try {
                    Class<?> declaringClass = eventHandler.getDeclaringClass();
                    eventHandler.setAccessible(true);
                    eventHandler.invoke(declaringClass.newInstance(), event);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
