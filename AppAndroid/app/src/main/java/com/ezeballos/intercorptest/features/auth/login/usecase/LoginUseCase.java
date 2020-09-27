package com.ezeballos.intercorptest.features.auth.login.usecase;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.features.auth.login.services.IFacebookLoginService;
import com.facebook.login.widget.LoginButton;

public class LoginUseCase implements ILoginUseCase {

    @NonNull
    private IFacebookLoginService facebookLoginService;

    public LoginUseCase(@NonNull IFacebookLoginService facebookLoginService) {
        this.facebookLoginService = facebookLoginService;
    }

    @Override
    public void loginWithFacebook(@NonNull final Activity activity, @NonNull final LoginButton loginButton) {
        facebookLoginService.loginWithFacebook(activity, loginButton, firebaseSigInError -> {
            Log.d("LoginUseCase", "fallo el login con facebook en el caso de uso ");
        });
    }

    @Override
    public void loginWithOtp() {

    }
}
