package com.ezeballos.intercorptest.features.auth.login.viewmodel;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.login.widget.LoginButton;

public interface ILoginViewModel {

    void doOtpLogin();
    void doFacebookLogin(@NonNull final Activity activity, @NonNull final LoginButton loginButton);
    void doGmailLogin();
}
