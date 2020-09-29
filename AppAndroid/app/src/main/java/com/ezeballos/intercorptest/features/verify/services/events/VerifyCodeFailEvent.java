package com.ezeballos.intercorptest.features.verify.services.events;

import androidx.annotation.NonNull;

public class VerifyCodeFailEvent {
    @NonNull
    private String message;

    public VerifyCodeFailEvent(@NonNull String message) {
        this.message = message;
    }
    @NonNull
    public String getMessage() {
        return message;
    }
}
