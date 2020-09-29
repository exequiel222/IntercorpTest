package com.ezeballos.intercorptest.features.auth.login.services.gmail;

import com.ezeballos.intercorptest.core.firebase.FirebaseSigInErrors;

@FunctionalInterface
public interface IGmailServiceFail {
    void failureListener(FirebaseSigInErrors firebaseSigInError);
}
