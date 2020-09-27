package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.core.firebase.FirebaseAuthListener
import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

@JvmField
val firebaseModule = module {
    factory { createFirebaseAuth() }
    factory { createFirebaseSupportMethods() }
    factory { createFirebaseAuthListener() }
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
