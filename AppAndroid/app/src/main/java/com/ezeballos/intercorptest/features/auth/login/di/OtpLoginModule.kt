package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.features.auth.login.services.otp.IOtpLoginService
import com.ezeballos.intercorptest.features.auth.login.services.otp.OtpLoginService
import org.koin.dsl.module

@JvmField
val otpLoginModule = module {
    factory { createOtpLoginService() }
}


fun createOtpLoginService(): IOtpLoginService {
    return OtpLoginService()
}
