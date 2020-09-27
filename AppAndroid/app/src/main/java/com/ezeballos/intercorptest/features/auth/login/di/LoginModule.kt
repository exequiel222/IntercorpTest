package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.features.auth.login.services.facebook.IFacebookLoginService
import com.ezeballos.intercorptest.features.auth.login.services.gmail.IGmailLoginService
import com.ezeballos.intercorptest.features.auth.login.services.otp.IOtpLoginService
import com.ezeballos.intercorptest.features.auth.login.usecase.ILoginUseCase
import com.ezeballos.intercorptest.features.auth.login.usecase.LoginUseCase
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModel
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModelDelegate
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

@JvmField
val loginModule = module {
    // single instance of ViewModel
    viewModel { LoginViewModel(get(), get(), get(), get()) }

    // single instance of viewModel delegate
    factory { createLoginViewModelDelegate() }

    // single instance of useCase
    factory { createILoginUseCase(get(), get(), get()) }
}

fun createILoginUseCase(
        facebookService: IFacebookLoginService,
        gmailService: IGmailLoginService,
        otpService: IOtpLoginService): ILoginUseCase {
    return LoginUseCase(facebookService, gmailService, otpService)
}

fun createLoginViewModelDelegate(): LoginViewModelDelegate {
    return LoginViewModelDelegate()
}
