package com.ezeballos.intercorptest.features.auth.login.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.core.firebase.FirebaseSigInErrors;
import com.ezeballos.intercorptest.core.firebase.IFirebaseResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

public class FirebaseAuthListener {

    public FirebaseAuth.AuthStateListener authLister = firebaseAuth -> {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Log.d("FirebaseAuthListener", "onAuthStateChanged: ");
            EventBus.getDefault().post(new FirebaseAuthSignedInEvent(firebaseUser, firebaseUser.getProviderId()));
        } else {
            EventBus.getDefault().post(new FirebaseAuthSignedOutEvent());
        }
    };

    public IFirebaseResponse generalFirebaseResponse = new IFirebaseResponse(){

        @Override
        public void onSuccess(boolean isSuccess) {
            Log.d("FirebaseAuthListener", "loginSuccess: " + isSuccess);
            EventBus.getDefault().post(new FirebaseLoginSuccessEvent());
        }

        @Override
        public void onError(FirebaseSigInErrors errorModel) {
            Log.d("FirebaseAuthListener", "onError: " + errorModel.toString());
            EventBus.getDefault().post(new FirebaseLoginFailEvent());
        }
    };

    public static class FirebaseAuthSignedInEvent {
        @NonNull
        private FirebaseUser firebaseUser;

        @NonNull
        private String providerId;

        public FirebaseAuthSignedInEvent(@NonNull FirebaseUser firebaseUser,
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

    public static class FirebaseAuthSignedOutEvent {}

    public static class FirebaseLoginSuccessEvent {}
    public static class FirebaseLoginFailEvent {}

    public static class OtpCodeSentEvent{
        @NonNull
        private String codeSent;

        public OtpCodeSentEvent(@NonNull String codeSent) {
            this.codeSent = codeSent;
        }

        @NonNull
        public String getCodeSent() {
            return codeSent;
        }
    }
    public static class OtpVerificationCompletedEvent{
        @NotNull
        private PhoneAuthCredential credential;

        public OtpVerificationCompletedEvent(@NotNull PhoneAuthCredential credential) {
            this.credential = credential;
        }

        @NotNull
        public PhoneAuthCredential getCredential() {
            return credential;
        }
    }
    public static class OtpVerificationFaliedEvent{
        @NonNull
        private FirebaseSigInErrors errors;

        public OtpVerificationFaliedEvent(@NonNull FirebaseSigInErrors errors) {
            this.errors = errors;
        }

        @NonNull
        public FirebaseSigInErrors getErrors() {
            return errors;
        }
    }
}
