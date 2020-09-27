package com.ezeballos.intercorptest.core.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.viewbinding.ViewBinding;

import com.ezeballos.intercorptest.R;

public abstract class BaseActivityLiveData extends AppCompatActivity implements IBaseViewActions {

    protected ViewBinding vBinding;

    @NonNull
    protected abstract ViewBinding getViewBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        vBinding = getViewBinding();
        setContentView(vBinding.getRoot());
        initViews();
        initObserversOfViewModel();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.trans_pop_left_in, R.anim.trans_pop_left_out);
    }

    protected <T> void observe(@NonNull final LiveData<T> liveData,
                               @NonNull final IObserverAction<T> action){
        liveData.observe(this, new Observer<T>() {
            @Override
            public void onChanged(@Nullable final T newName) {
                // Update the UI
                action.run(newName);
            }
        });
    }
}
