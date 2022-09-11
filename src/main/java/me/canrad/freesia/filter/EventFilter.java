package me.canrad.freesia.filter;

import me.canrad.freesia.handler.EventHandler;

public interface EventFilter<E> {
    boolean test(EventHandler paramEventHandler, E paramE);
}