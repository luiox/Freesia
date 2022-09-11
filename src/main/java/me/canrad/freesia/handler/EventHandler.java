package me.canrad.freesia.handler;

import me.canrad.freesia.filter.EventFilter;

public interface EventHandler extends Comparable<EventHandler> {
    <E> void handle(E paramE);

    Object getListener();

    ListenerPriority getPriority();

    Iterable<EventFilter> getFilters();
}