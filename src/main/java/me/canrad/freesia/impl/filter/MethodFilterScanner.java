package me.canrad.freesia.impl.filter;

import me.canrad.freesia.filter.EventFilter;
import me.canrad.freesia.filter.EventFilterScanner;
import me.canrad.freesia.impl.handler.Listener;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class MethodFilterScanner implements EventFilterScanner<Method> {
    public Set<EventFilter> scan(Method listener) {
        if (!listener.isAnnotationPresent(Listener.class))
            return Collections.emptySet();
        Set<EventFilter> filters = new HashSet<>();
        for (Class<? extends EventFilter> filter : listener.getDeclaredAnnotation(Listener.class).filters()) {
            try {
                filters.add(filter.newInstance());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return filters;
    }
}