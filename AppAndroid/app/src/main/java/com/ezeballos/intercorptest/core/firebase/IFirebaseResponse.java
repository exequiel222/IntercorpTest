package com.ezeballos.intercorptest.core.firebase;

public interface IFirebaseResponse {
    void onSuccess(boolean isSuccess);
    void onError(FirebaseSigInErrors errorModel);
}
