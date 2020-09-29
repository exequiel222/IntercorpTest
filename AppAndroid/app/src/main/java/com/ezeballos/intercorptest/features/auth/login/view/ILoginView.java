package com.ezeballos.intercorptest.features.auth.login.view;

import android.view.View;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.core.ui.Event;
import com.ezeballos.intercorptest.core.ui.None;

public interface ILoginView {

    void showProgress(@NonNull Event<None> event);
    void hideProgress(@NonNull Event<None> event);

    void showMessage(@NonNull Event<String> event);
    void hideMessage(@NonNull Event<None> event);

    void clearAreaCodeText(@NonNull Event<None> event);
    void clearNumberPhoneText(@NonNull Event<None> event);

    void requestFocusOnPhoneNumber(@NonNull Event<None> event);
    void requestFocusOnAreaCode(@NonNull Event<None> event);

    void goToVerifyPage(@NonNull Event<String> event);
    void goToRegisterPage(@NonNull Event<String> event);

    void onClickFacebookLogin(@NonNull final View view);
    void onClickGoogleLogin(@NonNull final View view);
    void onClickLogin(@NonNull final View view);
}
