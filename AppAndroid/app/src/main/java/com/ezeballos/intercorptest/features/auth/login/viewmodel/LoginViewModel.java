package com.ezeballos.intercorptest.features.auth.login.viewmodel;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.ezeballos.intercorptest.core.firebase.FirebaseAuthListener;
import com.ezeballos.intercorptest.features.auth.login.usecase.ILoginUseCase;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginViewModel extends ViewModel implements ILoginViewModel {
    @NonNull
    private LoginViewModelDelegate delegate;

    @NonNull
    private FirebaseAuthListener firebaseAuthListener;

    @NonNull
    private FirebaseAuth firebaseAuth;

    @NonNull
    private ILoginUseCase loginUseCase;

    public LoginViewModel(@NonNull LoginViewModelDelegate delegate,
                          @NonNull ILoginUseCase loginUseCase,
                          @NonNull FirebaseAuthListener firebaseAuthListener,
                          @NonNull FirebaseAuth firebaseAuth) {
        this.delegate = delegate;
        this.loginUseCase = loginUseCase;
        this.firebaseAuthListener = firebaseAuthListener;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public void doOtpLogin() {

    }

    @Override
    public void doFacebookLogin(@NonNull final Activity activity, @NonNull final LoginButton loginButton) {
        loginUseCase.loginWithFacebook(activity, loginButton);
    }

    public void addFirebaseListener(){
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        firebaseAuth.addAuthStateListener(firebaseAuthListener.authLister);
    }

    public void removeFirebaseLister(){
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        firebaseAuth.removeAuthStateListener(firebaseAuthListener.authLister);
    }

    public void handleResultFromActivity(Intent data){
        delegate.showProgressPostValue();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void firebaseLoginFail(@NonNull final FirebaseAuthListener.FirebaseLoginFail firebaseLoginFail){
        delegate.hideProgressPostValue();
        delegate.showMessagePostValue(firebaseLoginFail.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void signedCompleted(@NonNull final FirebaseAuthListener.FirebaseAuthSignedIn signedIn){
        delegate.hideProgressPostValue();
        delegate.showMessagePostValue("Log in completed :)");
    }
}
