package com.ezeballos.intercorptest;

import androidx.multidex.MultiDexApplication;

import com.ezeballos.intercorptest.features.auth.login.di.LoginModuleKt;

import org.koin.android.java.KoinAndroidApplication;
import org.koin.core.KoinApplication;
import org.koin.core.context.GlobalContext;

import static com.ezeballos.intercorptest.features.auth.login.di.LoginModuleKt.loginModule;
import static org.koin.core.context.ContextFunctionsKt.startKoin;
public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // Start Koin
        KoinApplication koin = KoinAndroidApplication.create(this)
                .modules(loginModule);
        startKoin(new GlobalContext(), koin);
    }
}
