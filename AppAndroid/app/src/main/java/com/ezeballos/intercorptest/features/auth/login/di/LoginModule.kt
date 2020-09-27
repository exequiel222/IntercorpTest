package com.ezeballos.intercorptest.features.auth.login.di

import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModel
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModelDelegate
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

@JvmField
val loginModule = module {
    // single instance of ViewModel
    viewModel { LoginViewModel(get()) }

    // single instance of viewModel delegate
    factory { createLoginViewModelDelegate() }
}

fun createLoginViewModelDelegate(): LoginViewModelDelegate {
    return LoginViewModelDelegate()
}
