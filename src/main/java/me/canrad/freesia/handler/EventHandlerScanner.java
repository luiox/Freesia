package me.canrad.freesia.handler;

import java.util.Map;
import java.util.Set;

public interface EventHandlerScanner {
    Map<Class<?>, Set<EventHandler>> locate(Object paramObject);
}
