package com.ezeballos.intercorptest.core.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;

public class FirebaseAuthListener {

    public FirebaseAuth.AuthStateListener authLister = firebaseAuth -> {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Log.d("FirebaseAuthListener", "onAuthStateChanged: ");
            EventBus.getDefault().post(new FirebaseAuthSignedIn(firebaseUser, firebaseUser.getProviderId()));
        } else {
            EventBus.getDefault().post(new FirebaseAuthSignedOut());
        }
    };

    public IFirebaseResponse generalFirebaseResponse = new IFirebaseResponse(){

        @Override
        public void onSuccess(boolean isSuccess) {
            Log.d("FirebaseAuthListener", "loginSuccess: " + isSuccess);
            EventBus.getDefault().post(new FirebaseLoginSuccess());
        }

        @Override
        public void onError(FirebaseSigInErrors errorModel) {
            Log.d("FirebaseAuthListener", "onError: " + errorModel.toString());
            EventBus.getDefault().post(new FirebaseLoginFail());
        }
    };

    public static class FirebaseAuthSignedIn {
        @NonNull
        private FirebaseUser firebaseUser;

        @NonNull
        private String providerId;

        public FirebaseAuthSignedIn(@NonNull FirebaseUser firebaseUser,
                                    @NonNull String providerId) {
            this.firebaseUser = firebaseUser;
            this.providerId = providerId;
        }

        @NonNull
        public FirebaseUser getFirebaseUser() {
            return firebaseUser;
        }

        public void setFirebaseUser(@NonNull FirebaseUser firebaseUser) {
            this.firebaseUser = firebaseUser;
        }

        @NonNull
        public String getProviderId() {
            return providerId;
        }

        public void setProviderId(@NonNull String providerId) {
            this.providerId = providerId;
        }
    }

    public static class FirebaseAuthSignedOut{}

    public static class FirebaseLoginSuccess{}
    public static class FirebaseLoginFail{}
}
