package com.ezeballos.intercorptest.features.auth.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.os.Bundle;

import com.ezeballos.intercorptest.R;
import com.ezeballos.intercorptest.core.ui.BaseActivityLiveData;
import com.ezeballos.intercorptest.databinding.ActivityLoginBinding;
import com.ezeballos.intercorptest.features.auth.login.viewmodel.ILoginViewModel;
import com.ezeballos.intercorptest.features.auth.login.viewmodel.LoginViewModel;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

public class LoginActivity extends BaseActivityLiveData {

    @NonNull
    private ActivityLoginBinding binding = (ActivityLoginBinding) vBinding;

    private Lazy<LoginViewModel> viewModel = inject(LoginViewModel.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.getValue();
    }

    @NonNull
    @Override
    protected ViewBinding getViewBinding() {
        return ActivityLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initViews() {

    }

    @Override
    public void initObserversOfViewModel() {

    }
}
