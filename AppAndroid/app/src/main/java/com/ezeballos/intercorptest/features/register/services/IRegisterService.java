package com.ezeballos.intercorptest.features.register.services;

import androidx.annotation.NonNull;

public interface IRegisterService {
    void register(@NonNull final CustomerDto customerDto);
}
