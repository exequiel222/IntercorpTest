package com.ezeballos.intercorptest;

import androidx.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.koin.android.java.KoinAndroidApplication;
import org.koin.core.KoinApplication;
import org.koin.core.context.GlobalContext;

import static com.ezeballos.intercorptest.features.auth.login.di.LoginFirebaseModuleKt.firebaseModule;
import static com.ezeballos.intercorptest.features.auth.login.di.LoginGmailModuleKt.gmailLoginModule;
import static com.ezeballos.intercorptest.features.auth.login.di.LoginModuleKt.loginModule;
import static com.ezeballos.intercorptest.features.auth.login.di.LoginFacebookModuleKt.facebookLoginModule;
import static com.ezeballos.intercorptest.features.auth.login.di.LoginOtpModuleKt.otpLoginModule;
import static com.ezeballos.intercorptest.features.verify.di.VerifiyModuleKt.verifyModule;
import static org.koin.core.context.ContextFunctionsKt.startKoin;

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeKoin();
        initializeFacebookSdk();
    }

    private void initializeFacebookSdk() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    private void initializeKoin(){
        // Start Koin
        KoinApplication koin = KoinAndroidApplication.create(this)
                .modules(loginModule,
                        facebookLoginModule,
                        gmailLoginModule,
                        otpLoginModule,
                        firebaseModule,
                        verifyModule);
        startKoin(new GlobalContext(), koin);
    }
}
