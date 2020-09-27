package com.ezeballos.intercorptest.features.auth.login.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel implements ILoginViewModel {
    @NonNull
    private LoginViewModelDelegate delegate;

    public LoginViewModel(@NonNull LoginViewModelDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void doOtpLogin() {

    }

    @Override
    public void doFacebookLogin() {

    }
}
