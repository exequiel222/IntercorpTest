package com.ezeballos.intercorptest.features.verify.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ezeballos.intercorptest.core.ui.Event;
import com.ezeballos.intercorptest.core.ui.None;

public class VerifyViewModelDelegate {
    //region Show progress
    private MutableLiveData<Event<None>> showProgress = new MutableLiveData<>();
    public LiveData<Event<None>> getShowProgress(){
        return showProgress;
    }
    public void showProgressPostValue(){
        showProgress.setValue(new Event<>(new None()));
    }
    //endregion

    //region Hide progress
    private MutableLiveData<Event<None>> hideProgress = new MutableLiveData<>();
    public LiveData<Event<None>> getHideProgress(){
        return hideProgress;
    }
    public void hideProgressPostValue(){
        hideProgress.setValue(new Event<>(new None()));
    }
    //endregion

    //region Show message
    private MutableLiveData<Event<String>> showMessage = new MutableLiveData<>();
    public LiveData<Event<String>> getShowMessage(){
        return showMessage;
    }
    public void showMessagePostValue(String message){
        showMessage.setValue(new Event<>(message));
    }
    //endregion

    //region Hide message
    private MutableLiveData<Event<None>> hideMessage = new MutableLiveData<>();
    public LiveData<Event<None>> getHideMessage(){
        return hideMessage;
    }
    public void hideMessagePostValue(){
        hideMessage.setValue(new Event<>(new None()));
    }
    //endregion

    //region Request focus on code edit text
    private MutableLiveData<Event<None>> requestFocusOnCode = new MutableLiveData<>();
    public LiveData<Event<None>> getRequestFocusOnCode(){
        return requestFocusOnCode;
    }
    public void requestFocusOnCodePostValue(){
        requestFocusOnCode.setValue(new Event<>(new None()));
    }
    //endregion

    //region Goto register page
    private MutableLiveData<Event<String>> goToRegisterPage = new MutableLiveData<>();
    public LiveData<Event<String>> getGoToRegisterPage(){
        return goToRegisterPage;
    }
    public void goToRegisterPagePostValue(String uuid){
        goToRegisterPage.setValue(new Event<>(uuid));
    }
    //endregion
}
