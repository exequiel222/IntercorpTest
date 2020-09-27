package com.ezeballos.intercorptest.features.auth.login.usecase;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ezeballos.intercorptest.features.auth.login.services.facebook.IFacebookServiceFail;
import com.facebook.login.widget.LoginButton;

public interface ILoginUseCase {

    void loginWithFacebook(@NonNull final Activity activity,
                           @NonNull final LoginButton loginButton,
                           @NonNull IFacebookServiceFail failureListener);

    void loginWithOtp(@NonNull final Activity activity, @NonNull final String phoneNumber);
    void loginWithGmail(@NonNull final Activity activity);
    void handleActivityResults(int requestCode, int resultCode, @NonNull Intent data);

    boolean isValidCodeArea(@Nullable final String codeArea);
    boolean isValidPhoneNumber(@Nullable final String phoneNumber);
}
