package com.ezeballos.intercorptest.features.verify.usecase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ezeballos.intercorptest.features.verify.services.IVerifyService;

public class VerifyUseCase implements IVerifyUseCase {
    @NonNull
    private IVerifyService service;

    public VerifyUseCase(@NonNull IVerifyService service) {
        this.service = service;
    }

    @Override
    public void verifyFirebaseCode(@NonNull String code, @NonNull String codeSentKey) {
        service.verifyCode(code, codeSentKey);
    }

    @Override
    public boolean isTheCodeValid(@Nullable String code) {
        return code != null && !code.isEmpty();
    }
}
