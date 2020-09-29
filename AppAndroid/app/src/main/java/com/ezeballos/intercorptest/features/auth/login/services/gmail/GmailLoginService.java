package com.ezeballos.intercorptest.features.auth.login.services.gmail;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ezeballos.intercorptest.core.firebase.FirebaseSigInErrors;
import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GmailLoginService implements IGmailLoginService {

    public static final String API_ID_CLIENT_WEB = "620633412875-i72dfinksfrtbt36f3g204tgh46omdk0.apps.googleusercontent.com";
    public static final int REQ_LOG_GOOGLE = 12;

    @NonNull
    private FirebaseAuth firebaseAuth;
    @NonNull
    private FirebaseSupportMethods firebaseSupportMethods;
    @Nullable
    private GoogleSignInClient googleApiClient = null;

    public GmailLoginService(@NonNull FirebaseAuth firebaseAuth,
                             @NonNull FirebaseSupportMethods firebaseSupportMethods) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseSupportMethods = firebaseSupportMethods;
    }

    @Override
    public void loginWithGmail(@NonNull Activity activity) {
        if (googleApiClient == null){
            googleApiClient = GoogleSignIn.getClient(activity, getGoogleOptions());
        }
        Intent gmailSignInIntent = googleApiClient.getSignInIntent();
        activity.startActivityForResult(gmailSignInIntent, REQ_LOG_GOOGLE);
    }

    @Override
    public void handleGmailResult(@NonNull Intent data,
                                  @NonNull final IGmailServiceFail failureListener) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result != null && result.isSuccess()) {
            Log.d("Gmail login", "Login con Google signIn exitoso, user: " + result.getSignInAccount().getDisplayName());
            String authToken = result.getSignInAccount().getIdToken();
            AuthCredential credential = GoogleAuthProvider.getCredential(authToken, null);
            loginSocialAccountWithFirebase(credential, failureListener);
        }else{
            FirebaseSigInErrors error = FirebaseSigInErrors.ERROR_UNKNOWN;
            if (result != null && GoogleSignInStatusCodes.SIGN_IN_CANCELLED == result.getStatus().getStatusCode()){
                error = FirebaseSigInErrors.ERROR_LOGIN_CANCEL;
            }
            //TODO quedan pendientes otros codigos de errores y el mensaje user-friendly
            failureListener.failureListener(error);
        }
    }

    private void loginSocialAccountWithFirebase(
            @NonNull final AuthCredential credential,
            @NonNull final IGmailServiceFail failureListener
    ) {
        firebaseAuth.signInWithCredential(credential)
                .addOnFailureListener(exception -> {
                    Log.d("Gmail login", "Se ha completado el login con firebase");
                    failureListener.failureListener(firebaseSupportMethods.getFirebaseErrorMessage(exception));
                });
    }

    @Override
    public void singOutGmail(@NonNull final Activity activity) {
        if (googleApiClient == null){
            googleApiClient = GoogleSignIn.getClient(activity, getGoogleOptions());
        }
        googleApiClient.signOut();
    }

    private GoogleSignInOptions getGoogleOptions(){
        return new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(API_ID_CLIENT_WEB)
                .requestId()
                .requestProfile()
                .requestEmail()
                .build();
    }
}
