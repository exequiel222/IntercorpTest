package com.ezeballos.intercorptest.features.auth.login.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ezeballos.intercorptest.core.ui.BaseActivityLiveData;
import com.ezeballos.intercorptest.core.ui.Event;
import com.ezeballos.intercorptest.core.ui.None;
import com.ezeballos.intercorptest.databinding.ActivityLoginBinding;
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModel;
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModelDelegate;
import com.ezeballos.intercorptest.features.home.MainActivity;
import com.ezeballos.intercorptest.features.register.view.RegisterActivity;
import com.ezeballos.intercorptest.features.verify.view.VerifyCodeActivity;

import kotlin.Lazy;

import static com.ezeballos.intercorptest.features.auth.login.services.gmail.GmailLoginService.REQ_LOG_GOOGLE;
import static com.ezeballos.intercorptest.features.register.view.RegisterActivity.UUID_KEY;
import static com.ezeballos.intercorptest.features.verify.view.VerifyCodeActivity.OTP_CODE_SEND_KEY;
import static org.koin.java.KoinJavaComponent.bind;
import static org.koin.java.KoinJavaComponent.inject;

public class LoginActivity extends BaseActivityLiveData<ActivityLoginBinding> implements ILoginView{

    private Lazy<LoginViewModel> viewModel = inject(LoginViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getValue();
    }

    @NonNull
    @Override
    protected ActivityLoginBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initObserversOfViewModel() {
        LoginViewModelDelegate delegateVm = viewModel.getValue().getDelegate();
        observe(delegateVm.getGoToHomePage(), this::goToRegisterPage);
        observe(delegateVm.getGoToVerifyPage(), this::goToVerifyPage);
        observe(delegateVm.getHideMessage(), this::hideMessage);
        observe(delegateVm.getHideProgress(), this::hideProgress);
        observe(delegateVm.getRequestFocusOnAreaCode(), this::requestFocusOnAreaCode);
        observe(delegateVm.getRequestFocusOnPhone(), this::requestFocusOnPhoneNumber);
        observe(delegateVm.getShowMessage(), this::showMessage);
        observe(delegateVm.getShowProgress(), this::showProgress);
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
    public void clearAreaCodeText(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.editPhoneAreaCode.setText("");
        }
    }

    @Override
    public void clearNumberPhoneText(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.editPhoneNumber.setText("");
        }
    }

    @Override
    public void requestFocusOnPhoneNumber(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.editPhoneNumber.requestFocus();
            showKeyboard(vBinding.editPhoneNumber);
        }
    }

    @Override
    public void requestFocusOnAreaCode(@NonNull Event<None> event) {
        if(!event.isHasBeenHandled()){
            vBinding.editPhoneAreaCode.requestFocus();
            showKeyboard(vBinding.editPhoneAreaCode);
        }
    }

    @Override
    public void goToVerifyPage(@NonNull Event<String> event) {
        if(!event.isHasBeenHandled()){
            Intent intent = new Intent(this, VerifyCodeActivity.class);
            intent.putExtra(OTP_CODE_SEND_KEY, event.getContentIfNotHandled());
            startActivity(intent);
        }
    }

    @Override
    public void goToRegisterPage(@NonNull Event<String> event) {
        if(!event.isHasBeenHandled()){
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra(UUID_KEY, event.getContentIfNotHandled());
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getValue().addFirebaseListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.getValue().removeFirebaseLister();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_LOG_GOOGLE){
            viewModel.getValue().handleResultFromActivity(data);
        }else{
            viewModel.getValue().handleResultFromActivity(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClickLogin(@NonNull final View view) {
        viewModel.getValue().doOtpLogin(
                vBinding.editPhoneAreaCode.getText().toString(),
                vBinding.editPhoneNumber.getText().toString(),
                this);
    }

    @Override
    public void onClickFacebookLogin(@NonNull final View view) {
        viewModel.getValue().doFacebookLogin(this, vBinding.buttonFacebookLogin);
        vBinding.buttonFacebookLogin.requestFocus();
        vBinding.buttonFacebookLogin.performClick();
    }

    @Override
    public void onClickGoogleLogin(@NonNull final View view) {
        vBinding.btnGmail.requestFocus();
        viewModel.getValue().doGmailLogin(this);
    }
}
