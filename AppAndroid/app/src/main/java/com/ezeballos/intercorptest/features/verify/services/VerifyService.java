package com.ezeballos.intercorptest.features.verify.services;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.features.verify.services.events.VerifyCodeFailEvent;
import com.ezeballos.intercorptest.features.verify.services.events.VerifyCodeSuccessEvent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.greenrobot.eventbus.EventBus;

public class VerifyService implements IVerifyService {
    @NonNull
    private FirebaseAuth mAuth;

    public VerifyService(@NonNull FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    @Override
    public void verifyCode(@NonNull String code, @NonNull String codeSentKey) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSentKey, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && task.getResult().getUser() != null) {
                        String uuid = task.getResult().getUser().getUid();
                        EventBus.getDefault().post(new VerifyCodeSuccessEvent(uuid));
                    } else {
                        String errorMessage;
                        if (task.getException() != null){
                            errorMessage = task.getException().getMessage();
                        }else{
                            errorMessage = "Verificación cancelada";
                        }
                        EventBus.getDefault().post(new VerifyCodeFailEvent(errorMessage));
                    }
                });
    }
}
