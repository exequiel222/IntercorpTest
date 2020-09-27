package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.features.auth.login.services.IFacebookLoginService
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
    factory { createILoginUseCase(get()) }
}

fun createILoginUseCase(facebookService: IFacebookLoginService): ILoginUseCase {
    return LoginUseCase(facebookService)
}

fun createLoginViewModelDelegate(): LoginViewModelDelegate {
    return LoginViewModelDelegate()
}
