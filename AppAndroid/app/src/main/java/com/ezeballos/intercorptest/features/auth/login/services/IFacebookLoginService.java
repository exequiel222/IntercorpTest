package com.ezeballos.intercorptest.features.auth.login.services;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.facebook.login.widget.LoginButton;

public interface IFacebookLoginService {

    public void loginWithFacebook(@NonNull final Activity activity,
                                  @NonNull final LoginButton facebookLoginBtn,
                                  @NonNull IFacebookServiceFail failureListener);

    public void handleFacebookResult(int requestCode, int resultCode, @NonNull Intent data);
}
