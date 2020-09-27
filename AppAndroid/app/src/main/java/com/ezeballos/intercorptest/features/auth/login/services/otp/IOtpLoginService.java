package com.ezeballos.intercorptest.features.auth.login.services.otp;

import android.app.Activity;

import androidx.annotation.NonNull;

public interface IOtpLoginService {

    void loginWithOtp(@NonNull final Activity activity,
                      @NonNull final String phoneNumber);
}
