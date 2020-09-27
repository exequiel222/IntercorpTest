package com.ezeballos.intercorptest.features.auth.login.usecase;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.login.widget.LoginButton;

public interface ILoginUseCase {
    void loginWithFacebook(@NonNull final Activity activity, @NonNull final LoginButton loginButton);
    void loginWithOtp();
}
