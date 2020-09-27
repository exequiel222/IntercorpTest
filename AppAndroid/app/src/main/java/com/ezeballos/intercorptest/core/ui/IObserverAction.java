package com.ezeballos.intercorptest.core.ui;

@FunctionalInterface
public interface IObserverAction<T> {
    void run(T data);
}
