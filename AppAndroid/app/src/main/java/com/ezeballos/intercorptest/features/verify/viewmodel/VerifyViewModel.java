package com.ezeballos.intercorptest.features.verify.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.ezeballos.intercorptest.features.verify.services.events.VerifyCodeFailEvent;
import com.ezeballos.intercorptest.features.verify.services.events.VerifyCodeSuccessEvent;
import com.ezeballos.intercorptest.features.verify.usecase.IVerifyUseCase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class VerifyViewModel extends ViewModel implements IVerifyViewModel {
    @NonNull
    private VerifyViewModelDelegate delegate;

    @NonNull
    private IVerifyUseCase useCase;

    public VerifyViewModel(@NonNull VerifyViewModelDelegate delegate,
                           @NonNull IVerifyUseCase useCase) {
        this.delegate = delegate;
        this.useCase = useCase;
    }

    @Override
    public void doVerifyCode(@Nullable String code, @NonNull String codeSentKey) {
        delegate.showProgressPostValue();
        delegate.showMessagePostValue("Validando código");
        if (useCase.isTheCodeValid(code)){
            useCase.verifyFirebaseCode(code, codeSentKey);
        }else{
            delegate.hideProgressPostValue();
            delegate.showMessagePostValue("Código inválido");
        }
    }

    public void subscribeToEventBus(){
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void unsubscribeToEventBus(){
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessVerify(@NonNull VerifyCodeSuccessEvent event){
        delegate.hideProgressPostValue();
        delegate.hideMessagePostValue();
        delegate.goToRegisterPagePostValue(event.getUuid());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailVerify(@NonNull VerifyCodeFailEvent event){
        delegate.hideProgressPostValue();
        delegate.showMessagePostValue(event.getMessage());
    }

    @NonNull
    public VerifyViewModelDelegate getDelegate(){
        return delegate;
    }
}
