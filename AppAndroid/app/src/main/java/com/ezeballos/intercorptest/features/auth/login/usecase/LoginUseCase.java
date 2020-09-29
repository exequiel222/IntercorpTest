package com.ezeballos.intercorptest.features.auth.login.usecase;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ezeballos.intercorptest.features.auth.login.services.facebook.IFacebookLoginService;
import com.ezeballos.intercorptest.features.auth.login.services.facebook.IFacebookServiceFail;
import com.ezeballos.intercorptest.features.auth.login.services.gmail.IGmailLoginService;
import com.ezeballos.intercorptest.features.auth.login.services.gmail.IGmailServiceFail;
import com.ezeballos.intercorptest.features.auth.login.services.otp.IOtpLoginService;
import com.facebook.login.widget.LoginButton;

public class LoginUseCase implements ILoginUseCase {

    @NonNull
    private IFacebookLoginService facebookLoginService;
    @NonNull
    private IGmailLoginService gmailLoginService;
    @NonNull
    private IOtpLoginService otpLoginService;

    public LoginUseCase(@NonNull IFacebookLoginService facebookLoginService,
                        @NonNull IGmailLoginService gmailLoginService,
                        IOtpLoginService otpLoginService) {
        this.facebookLoginService = facebookLoginService;
        this.gmailLoginService = gmailLoginService;
        this.otpLoginService = otpLoginService;
    }

    @Override
    public void loginWithFacebook(@NonNull final Activity activity,
                                  @NonNull final LoginButton loginButton,
                                  @NonNull IFacebookServiceFail failureListener) {
        facebookLoginService.loginWithFacebook(activity, loginButton,failureListener);
    }

    @Override
    public void loginWithOtp(@NonNull final Activity activity, @NonNull final String phoneNumber){
        otpLoginService.loginWithOtp(activity, phoneNumber);
    }

    @Override
    public void loginWithGmail(@NonNull final Activity activity) {
        gmailLoginService.loginWithGmail(activity);
    }

    @Override
    public void handleActivityResultsForFacebook(int requestCode, int resultCode, @NonNull Intent data) {
        facebookLoginService.handleFacebookResult(requestCode, resultCode, data);
    }

    @Override
    public void handleActivityResultsForGmail(@NonNull Intent data, @NonNull IGmailServiceFail failureListener) {
        gmailLoginService.handleGmailResult(data, failureListener);
    }

    @Override
    public boolean isValidCodeArea(@Nullable String codeArea) {
        return codeArea != null && !codeArea.isEmpty();
    }

    @Override
    public boolean isValidPhoneNumber(@Nullable String phoneNumber) {
        return phoneNumber != null && !phoneNumber.isEmpty();
    }
}
