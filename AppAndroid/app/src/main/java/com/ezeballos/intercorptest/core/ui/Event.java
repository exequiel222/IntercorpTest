package com.ezeballos.intercorptest.core.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
public class Event<T> {

    private T content;
    private boolean hasBeenHandled;

    public Event(@NonNull T content) {
        this.content = content;
    }

    @Nullable
    public T getContentIfNotHandled(){
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }

    public boolean isHasBeenHandled() {
        return hasBeenHandled;
    }
}
