package com.ezeballos.intercorptest.features.verify.view;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ezeballos.intercorptest.core.ui.BaseActivityLiveData;
import com.ezeballos.intercorptest.core.ui.Event;
import com.ezeballos.intercorptest.core.ui.None;
import com.ezeballos.intercorptest.databinding.ActivityVerifyCodeBinding;
import com.ezeballos.intercorptest.features.register.view.RegisterActivity;
import com.ezeballos.intercorptest.features.verify.viewmodel.VerifyViewModel;
import com.ezeballos.intercorptest.features.verify.viewmodel.VerifyViewModelDelegate;

import kotlin.Lazy;

import static com.ezeballos.intercorptest.features.register.view.RegisterActivity.UUID_KEY;
import static org.koin.java.KoinJavaComponent.inject;

public class VerifyCodeActivity extends BaseActivityLiveData<ActivityVerifyCodeBinding> implements IVerifyCodeView {

    public static final String OTP_CODE_SEND_KEY = "OTP_CODE_SEND";
    private String codeSendKey;

    private Lazy<VerifyViewModel> viewModel = inject(VerifyViewModel.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(OTP_CODE_SEND_KEY)){
            codeSendKey = getIntent().getStringExtra(OTP_CODE_SEND_KEY);
        }
    }

    @NonNull
    @Override
    protected ActivityVerifyCodeBinding getViewBinding() {
        return ActivityVerifyCodeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initObserversOfViewModel() {
        VerifyViewModelDelegate delegateVm = viewModel.getValue().getDelegate();
        observe(delegateVm.getGoToRegisterPage(), this::goToRegisterPage);
        observe(delegateVm.getHideMessage(), this::hideMessage);
        observe(delegateVm.getHideProgress(), this::hideProgress);
        observe(delegateVm.getRequestFocusOnCode(), this::requestFocusOnCode);
        observe(delegateVm.getShowMessage(), this::showMessage);
        observe(delegateVm.getShowProgress(), this::showProgress);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getValue().subscribeToEventBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.getValue().unsubscribeToEventBus();
    }

    @Override
    public void showProgress(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.spinnerLoader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.spinnerLoader.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(@NonNull Event<String> event) {
        if(!event.isHasBeenHandled()){
            vBinding.txtMessage.setVisibility(View.VISIBLE);
            vBinding.txtMessage.setText(event.getContentIfNotHandled());
        }
    }

    @Override
    public void hideMessage(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.txtMessage.setVisibility(View.GONE);
        }
    }

    @Override
    public void clearCodeText(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.txtMessage.setText("");
        }
    }

    @Override
    public void requestFocusOnCode(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.editVerifyCode.requestFocus();
            showKeyboard(vBinding.editVerifyCode);
        }
    }

    @Override
    public void goToRegisterPage(@NonNull Event<String> event) {
        if(!event.isHasBeenHandled()){
            Intent intent = new Intent(VerifyCodeActivity.this, RegisterActivity.class);
            intent.putExtra(UUID_KEY,event.getContentIfNotHandled());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClickVerifyLogin(@NonNull View view) {
        if (viewModel.isInitialized()){
            viewModel.getValue().doVerifyCode(vBinding.editVerifyCode.getText().toString(),codeSendKey);
        }
    }
}
