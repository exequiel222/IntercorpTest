package com.ezeballos.intercorptest.features.auth.login.services.facebook;

import com.ezeballos.intercorptest.core.firebase.FirebaseSigInErrors;

@FunctionalInterface
public interface IFacebookServiceFail {
    void failureListener(FirebaseSigInErrors firebaseSigInError);
}
