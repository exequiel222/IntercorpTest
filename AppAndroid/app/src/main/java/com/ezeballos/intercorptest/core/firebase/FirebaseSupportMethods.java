package com.ezeballos.intercorptest.core.firebase;

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class FirebaseSupportMethods {

    public FirebaseSigInErrors getFirebaseErrorMessage(Exception exception){
        FirebaseSigInErrors result;

        if (exception instanceof FirebaseAuthInvalidUserException) {
            switch (((FirebaseAuthInvalidUserException) exception).getErrorCode()){
                case FirebaseConstants.ERROR_USER_DISABLE_STRING : {
                    result = FirebaseSigInErrors.ERROR_USER_DISABLE;
                    break;
                }
                case FirebaseConstants.ERROR_USER_NOT_FOUND_STRING : {
                    result = FirebaseSigInErrors.ERROR_USER_NOT_FOUND;
                    break;
                }
                default : {
                    result = FirebaseSigInErrors.ERROR_INVALID_USER;
                }
            }
            return result;
        }

        if (exception instanceof FirebaseAuthInvalidCredentialsException){
            result = FirebaseSigInErrors.ERROR_INVALID_PASSWORD;
            return result;
        }

        if (exception instanceof FirebaseAuthUserCollisionException){
            switch (((FirebaseAuthUserCollisionException) exception).getErrorCode()){
                case FirebaseConstants.ERROR_ACCOUNT_EXIST_STRING : {
                    result = FirebaseSigInErrors.ERROR_ACCOUNT_EXIST;
                }
                default : {
                    result = FirebaseSigInErrors.ERROR_ACCOUNT_USED;
                }
            }
            return result;
        }

        return FirebaseSigInErrors.ERROR_UNKNOWN;
    }
}
