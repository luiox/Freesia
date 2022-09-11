package me.canrad.freesia.impl.handler;

import me.canrad.freesia.filter.EventFilter;
import me.canrad.freesia.handler.ListenerPriority;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Listener {
    Class<? extends EventFilter>[] filters() default {};

    ListenerPriority priority() default ListenerPriority.NORMAL;
}