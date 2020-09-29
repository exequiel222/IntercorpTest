package com.ezeballos.intercorptest.features.auth.login.services.gmail;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

public interface IGmailLoginService {
    void loginWithGmail(@NonNull final Activity activity);
    void handleGmailResult(@NonNull final Intent data,
                           @NonNull final IGmailServiceFail failureListener);
    void singOutGmail(@NonNull final Activity activity);
}
