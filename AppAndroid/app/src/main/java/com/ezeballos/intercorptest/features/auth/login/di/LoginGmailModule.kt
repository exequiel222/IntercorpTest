package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods
import com.ezeballos.intercorptest.features.auth.login.services.gmail.GmailLoginService
import com.ezeballos.intercorptest.features.auth.login.services.gmail.IGmailLoginService
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

@JvmField
val gmailLoginModule = module {
    factory { createGmailService(get(), get()) }
}

fun createGmailService(
        firebaseAuth: FirebaseAuth,
        firebaseSupportMethods: FirebaseSupportMethods
) : IGmailLoginService{
    return GmailLoginService(firebaseAuth, firebaseSupportMethods)
}
