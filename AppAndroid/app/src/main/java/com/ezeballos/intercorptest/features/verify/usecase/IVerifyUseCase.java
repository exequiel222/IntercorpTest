package com.ezeballos.intercorptest.features.verify.usecase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public interface IVerifyUseCase {

    void verifyFirebaseCode(@NonNull final String code,
                            @NonNull final String codeSentKey);

    boolean isTheCodeValid(@Nullable final String code);
}
