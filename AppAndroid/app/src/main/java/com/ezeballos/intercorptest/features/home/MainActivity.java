package com.ezeballos.intercorptest.features.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ezeballos.intercorptest.R;
import com.ezeballos.intercorptest.features.auth.login.services.gmail.GmailLoginService;
import com.ezeballos.intercorptest.features.auth.login.services.gmail.IGmailLoginService;
import com.ezeballos.intercorptest.features.auth.login.view.LoginActivity;
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModel;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.firebase.auth.FirebaseAuth;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private Lazy<IGmailLoginService> service = inject(IGmailLoginService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSignOut(View view) {
        service.getValue().singOutGmail(this);
        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
