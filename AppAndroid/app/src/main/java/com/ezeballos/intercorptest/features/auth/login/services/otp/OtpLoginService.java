package com.ezeballos.intercorptest.features.auth.login.services.otp;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods;
import com.ezeballos.intercorptest.features.auth.login.services.FirebaseAuthListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class OtpLoginService implements IOtpLoginService {

    @NonNull
    private FirebaseSupportMethods firebaseSupportMethods;

    @NonNull
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    public OtpLoginService(@NonNull FirebaseSupportMethods firebaseSupportMethods){
        this.firebaseSupportMethods = firebaseSupportMethods;
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NotNull PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                EventBus.getDefault().post(new FirebaseAuthListener.OtpVerificationCompletedEvent(credential));
            }

            @Override
            public void onVerificationFailed(@NotNull FirebaseException ex) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                EventBus.getDefault().post(new FirebaseAuthListener.OtpVerificationFaliedEvent(firebaseSupportMethods.getFirebaseErrorMessage(ex)));
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("OTP", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                EventBus.getDefault().post(new FirebaseAuthListener.OtpCodeSentEvent(verificationId));
            }
        };
    }

    @Override
    public void loginWithOtp(@NonNull final Activity activity,
                             @NonNull final String phoneNumber) {
        PhoneAuthProvider.getInstance()
                .verifyPhoneNumber(
                    phoneNumber,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    activity,               // Activity (for callback binding)
                    mCallbacks              // OnVerificationStateChangedCallbacks
                );
    }
}
