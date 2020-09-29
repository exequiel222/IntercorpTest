package com.ezeballos.intercorptest.features.verify.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IVerifyViewModel {

    void doVerifyCode(@Nullable final String code,
                      @NonNull final String codeSentKey);
}
