package com.ezeballos.intercorptest.features.auth.login.view;

import androidx.annotation.NonNull;

import com.ezeballos.intercorptest.core.ui.Event;

public interface ILoginView {

    void showProgress(@NonNull Event<Void> event);
    void hideProgress(@NonNull Event<Void> event);

    void showMessage(@NonNull Event<Void> event);
    void hideMessage(@NonNull Event<Void> event);

    void clearAreaCodeText(@NonNull Event<Void> event);
    void clearNumberPhoneText(@NonNull Event<Void> event);

    void requestFocusOnUserName(@NonNull Event<Void> event);
    void requestFocusOnPassword(@NonNull Event<Void> event);

    void goToNextPage(@NonNull Event<Void> event);
}
