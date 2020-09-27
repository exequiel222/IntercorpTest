package com.ezeballos.intercorptest.features.auth.login.services.gmail;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ezeballos.intercorptest.core.firebase.FirebaseSigInErrors;
import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods;
import com.ezeballos.intercorptest.core.firebase.IFirebaseResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GmailLoginService implements IGmailLoginService {

    public static final String API_ID_CLIENT_WEB = "473990735697-fhpb3s2ef39dqut9f9kf025coomri0oo.apps.googleusercontent.com";
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
        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(API_ID_CLIENT_WEB)
                .requestId()
                .requestProfile()
                .requestEmail()
                .build();
        if (googleApiClient == null){
            googleApiClient = GoogleSignIn.getClient(activity, options);
        }
        Intent gmailSignInIntent = googleApiClient.getSignInIntent();
        activity.startActivityForResult(gmailSignInIntent, REQ_LOG_GOOGLE);
    }

    @Override
    public void handleGmailResult(@NonNull Intent data, @NonNull IFirebaseResponse onResult) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            Log.d("Gmail login", "Login con Google signIn exitoso, user: " + result.getSignInAccount().getDisplayName());
            String authToken = result.getSignInAccount().getIdToken();
            AuthCredential credential = GoogleAuthProvider.getCredential(authToken, null);
            loginSocialAccountWithFirebase(credential, onResult);
        }else{
            String errorMessage = "Error Google signIn code: " + result.getStatus().getStatusCode();
            Log.d("Gmail login", errorMessage);
            onResult.onError(FirebaseSigInErrors.ERROR_UNKNOWN);
        }
    }

    private void loginSocialAccountWithFirebase(
            AuthCredential credential,
            IFirebaseResponse onResult
    ) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task ->
                {
                    Log.d("Gmail login", "Se ha completado el login con firebase");
                    if (task.isSuccessful()){
                        onResult.onSuccess(task.isSuccessful());
                    }
                })
                .addOnFailureListener(exception -> {
                    Log.d("Gmail login", "Se ha completado el login con firebase");
                    onResult.onError(firebaseSupportMethods.getFirebaseErrorMessage(exception));
                });
    }
}
