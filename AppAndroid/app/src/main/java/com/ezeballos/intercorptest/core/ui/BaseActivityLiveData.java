package com.ezeballos.intercorptest.core.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivityLiveData<ViewBindingT extends ViewBinding> extends AppCompatActivity implements IBaseViewActions {

    protected ViewBindingT vBinding;

    @NonNull
    protected abstract ViewBindingT getViewBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        vBinding = getViewBinding();
        setContentView(vBinding.getRoot());
        initViews();
        initObserversOfViewModel();
    }

    protected <T> void observe(@NonNull final LiveData<T> liveData,
                               @NonNull final IObserverAction<T> action){
        // Update the UI
        liveData.observe(this, action::run);
    }

    protected void showKeyboard(@NonNull final EditText editText){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
}
