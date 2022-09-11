package me.canrad.freesia.impl.dispatch;

import me.canrad.freesia.dispatch.EventDispatcher;
import me.canrad.freesia.handler.EventHandler;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public final class MethodEventDispatcher implements EventDispatcher {
    private final Map<Class<?>, Set<EventHandler>> eventHandlers;

    public MethodEventDispatcher(Map<Class<?>, Set<EventHandler>> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public <E> void dispatch(E event) {
        for (EventHandler eventHandler : this.eventHandlers.getOrDefault(event
                .getClass(), Collections.emptySet()))
            eventHandler.handle(event);
    }
}