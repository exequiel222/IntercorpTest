package com.ezeballos.intercorptest.features.verify.services.events;


import androidx.annotation.NonNull;

public class VerifyCodeSuccessEvent {
    @NonNull
    private String uuid;

    public VerifyCodeSuccessEvent(@NonNull String uuid) {
        this.uuid = uuid;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }
}
