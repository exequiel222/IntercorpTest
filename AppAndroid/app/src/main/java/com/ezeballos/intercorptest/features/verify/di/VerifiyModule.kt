package com.ezeballos.intercorptest.features.verify.di

import com.ezeballos.intercorptest.features.verify.services.IVerifyService
import com.ezeballos.intercorptest.features.verify.services.VerifyService
import com.ezeballos.intercorptest.features.verify.usecase.IVerifyUseCase
import com.ezeballos.intercorptest.features.verify.usecase.VerifyUseCase
import com.ezeballos.intercorptest.features.verify.viewmodel.IVerifyViewModel
import com.ezeballos.intercorptest.features.verify.viewmodel.VerifyViewModel
import com.ezeballos.intercorptest.features.verify.viewmodel.VerifyViewModelDelegate
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import com.google.firebase.auth.FirebaseAuth


@JvmField
val verifyModule = module {
    // single instance of ViewModel
    viewModel { VerifyViewModel(get(), get()) }

    // single instance of viewModel delegate
    factory { createVerifyViewModelDelegate() }

    // single instance of useCase
    factory { createIVerifyUseCase(get()) }

    factory { createVerifyService(get()) }
}

fun createVerifyViewModelDelegate() :VerifyViewModelDelegate{
    return VerifyViewModelDelegate()
}

fun createIVerifyUseCase(service: IVerifyService):IVerifyUseCase{
    return VerifyUseCase(service)
}

fun createVerifyService(auth: FirebaseAuth): IVerifyService {
    return VerifyService(auth)
}
