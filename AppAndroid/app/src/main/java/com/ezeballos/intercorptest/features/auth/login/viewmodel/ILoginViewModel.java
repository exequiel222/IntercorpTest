package com.ezeballos.intercorptest.features.auth.login.viewmodel;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.login.widget.LoginButton;

public interface ILoginViewModel {

    void doOtpLogin(@Nullable final String areaCode,
                    @Nullable final String phoneNumber,
                    @NonNull final Activity activity);
    void doFacebookLogin(@NonNull final Activity activity, @NonNull final LoginButton loginButton);
    void doGmailLogin(@NonNull final Activity activity);
}
