package com.ezeballos.intercorptest.features.verify.services;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.features.register.services.CustomerDto;

public interface IVerifyService {
    void verifyCode(@NonNull final String code,
                    @NonNull final String codeSentKey);
}
