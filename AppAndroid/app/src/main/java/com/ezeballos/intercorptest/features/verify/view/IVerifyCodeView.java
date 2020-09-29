package com.ezeballos.intercorptest.features.verify.view;

import android.view.View;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.core.ui.Event;
import com.ezeballos.intercorptest.core.ui.None;

public interface IVerifyCodeView {

    void showProgress(@NonNull Event<None> event);
    void hideProgress(@NonNull Event<None> event);

    void showMessage(@NonNull Event<String> event);
    void hideMessage(@NonNull Event<None> event);

    void clearCodeText(@NonNull Event<None> event);

    void requestFocusOnCode(@NonNull Event<None> event);

    void goToRegisterPage(@NonNull Event<String> event);

    void onClickVerifyLogin(@NonNull final View view);

}
