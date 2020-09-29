package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.core.firebase.FirebaseSupportMethods
import com.ezeballos.intercorptest.features.auth.login.services.otp.IOtpLoginService
import com.ezeballos.intercorptest.features.auth.login.services.otp.OtpLoginService
import org.koin.dsl.module

@JvmField
val otpLoginModule = module {
    factory { createOtpLoginService(get()) }
}


fun createOtpLoginService(firebaseSupportMethods : FirebaseSupportMethods): IOtpLoginService {
    return OtpLoginService(firebaseSupportMethods)
}
