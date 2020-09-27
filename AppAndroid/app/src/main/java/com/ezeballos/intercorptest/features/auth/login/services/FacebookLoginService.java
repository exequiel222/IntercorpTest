package com.ezeballos.intercorptest.features.auth.login.services;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ezeballos.intercorptest.core.firebase.FirebaseSigInErrors;
import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class FacebookLoginService implements IFacebookLoginService{
    @NonNull
    private FirebaseAuth firebaseAuth;
    @NonNull
    private CallbackManager callbackManager;
    @NonNull
    private FirebaseSupportMethods firebaseSupportMethods;

    public FacebookLoginService(@NonNull FirebaseAuth firebaseAuth,
                                @NonNull CallbackManager callbackManager,
                                @NonNull FirebaseSupportMethods firebaseSupportMethods) {
        this.firebaseAuth = firebaseAuth;
        this.callbackManager = callbackManager;
        this.firebaseSupportMethods = firebaseSupportMethods;
    }

    @Override
    public void loginWithFacebook(@NonNull Activity activity,
                                  @NonNull LoginButton facebookLoginBtn,
                                  @NonNull IFacebookServiceFail failureListener) {
        facebookLoginBtn.setPermissions("email", "public_profile");
        facebookLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FacebookLoginService", "onSuccess: " + loginResult.getAccessToken());
                handleFacebookAccessToken(loginResult.getAccessToken(), failureListener);
            }

            @Override
            public void onCancel() {
                Log.d("FacebookLoginService", "onCancel");
                failureListener.failureListener(FirebaseSigInErrors.ERROR_LOGIN_CANCEL);
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FacebookLoginService", "onError: " + error.toString());
                failureListener.failureListener(FirebaseSigInErrors.ERROR_UNKNOWN);
            }
        });
    }

    @Override
    public void handleFacebookResult(int requestCode, int resultCode, @NonNull Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void handleFacebookAccessToken(@NonNull AccessToken accessToken,
                                          @NonNull IFacebookServiceFail failureListener){
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnFailureListener(
                        (Exception ex) -> failureListener.failureListener(firebaseSupportMethods.getFirebaseErrorMessage(ex))
                );
    }
}
