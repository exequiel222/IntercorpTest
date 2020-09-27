package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.core.firebase.FirebaseAuthListener
import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods
import com.ezeballos.intercorptest.features.auth.login.services.FacebookLoginService
import com.ezeballos.intercorptest.features.auth.login.services.IFacebookLoginService
import com.facebook.CallbackManager
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

@JvmField
val socialModule = module {
    factory { createFacebookLoginService(get(), get(), get()) }

    // single instance of supports objects
    factory { createFirebaseAuth() }
    factory { createFirebaseSupportMethods() }
    factory { createFirebaseAuthListener() }
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

fun createFirebaseAuth(): FirebaseAuth {
    return FirebaseAuth.getInstance()
}

fun createFirebaseSupportMethods(): FirebaseSupportMethods {
    return FirebaseSupportMethods()
}

fun createFirebaseAuthListener(): FirebaseAuthListener {
    return FirebaseAuthListener()
}
