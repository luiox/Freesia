package me.canrad.freesia.impl;

import me.canrad.freesia.EventBus;
import me.canrad.freesia.dispatch.EventDispatcher;
import me.canrad.freesia.handler.EventHandler;
import me.canrad.freesia.handler.EventHandlerScanner;
import me.canrad.freesia.impl.dispatch.MethodEventDispatcher;
import me.canrad.freesia.impl.handler.MethodHandlerScanner;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager implements EventBus {
    final Map<Object, EventDispatcher> listenerDispatchers = new ConcurrentHashMap<>();
    private final EventHandlerScanner eventHandlerScanner = new MethodHandlerScanner();

    public <E> E post(E event) {
        for (EventDispatcher dispatcher : this.listenerDispatchers.values())
            dispatcher.dispatch(event);
        return event;
    }

    public boolean isRegistered(Object listener) {
        return this.listenerDispatchers.containsKey(listener);
    }

    public boolean addListener(Object listenerContainer) {
        if (this.listenerDispatchers.containsKey(listenerContainer))
            return false;
        Map<Class<?>, Set<EventHandler>> eventHandlers = this.eventHandlerScanner.locate(listenerContainer);
        if (eventHandlers.isEmpty())
            return false;
        return (this.listenerDispatchers.put(listenerContainer, new MethodEventDispatcher(eventHandlers)) == null);
    }

    public boolean removeListener(Object listenerContainer) {
        return (this.listenerDispatchers.remove(listenerContainer) != null);
    }
}