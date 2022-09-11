package me.canrad.freesia.dispatch;

public interface EventDispatcher {
    <E> void dispatch(E paramE);
}