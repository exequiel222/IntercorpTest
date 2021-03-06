package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods
import com.ezeballos.intercorptest.features.auth.login.services.facebook.FacebookLoginService
import com.ezeballos.intercorptest.features.auth.login.services.facebook.IFacebookLoginService
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

@JvmField
val facebookLoginModule = module {
    factory { createFacebookLoginService(get(), get(), get()) }
    // single instance of supports objects
    factory { createCallbackManager() }

}

fun createFacebookLoginService(
        firebaseAuth: FirebaseAuth,
        callbackManager: CallbackManager,
        firebaseSupportMethods: FirebaseSupportMethods
): IFacebookLoginService {
    return FacebookLoginService(firebaseAuth, callbackManager, firebaseSupportMethods)
}

fun createCallbackManager(): CallbackManager {
    return CallbackManager.Factory.create()
}
