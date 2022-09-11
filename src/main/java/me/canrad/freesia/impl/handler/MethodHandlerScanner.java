package me.canrad.freesia.impl.handler;

import me.canrad.freesia.filter.EventFilterScanner;
import me.canrad.freesia.handler.EventHandler;
import me.canrad.freesia.handler.EventHandlerScanner;
import me.canrad.freesia.impl.filter.MethodFilterScanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public final class MethodHandlerScanner implements EventHandlerScanner {
    private final AnnotatedListenerPredicate annotatedListenerPredicate = new AnnotatedListenerPredicate();

    private final EventFilterScanner<Method> filterScanner = new MethodFilterScanner();

    public Map<Class<?>, Set<EventHandler>> locate(Object listenerContainer) {
        HashMap<Class<?>, Set<EventHandler>> eventHandlers = new HashMap<>();
        Stream.of(listenerContainer.getClass().getDeclaredMethods())
                .filter(this.annotatedListenerPredicate)
                .forEach(method -> eventHandlers.computeIfAbsent(method.getParameterTypes()[0],
                                obj -> new TreeSet<>())
                        .add(new MethodEventHandler(listenerContainer, method, this.filterScanner.scan(method))));
        return eventHandlers;
    }
}