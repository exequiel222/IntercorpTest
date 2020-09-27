package com.ezeballos.intercorptest.features.auth.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ezeballos.intercorptest.core.ui.Event;
import com.ezeballos.intercorptest.core.ui.None;

public class LoginViewModelDelegate {

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

    //region Request focus on area code edit text
    private MutableLiveData<Event<None>> requestFocusOnAreaCode = new MutableLiveData<>();
    public LiveData<Event<None>> getRequestFocusOnAreaCode(){
        return requestFocusOnAreaCode;
    }
    public void requestFocusOnAreaCodePostValue(){
        requestFocusOnAreaCode.setValue(new Event<>(new None()));
    }
    //endregion

    //region Request focus on phone edit text
    private MutableLiveData<Event<None>> requestFocusOnPhone = new MutableLiveData<>();
    public LiveData<Event<None>> getRequestFocusOnPhone(){
        return requestFocusOnPhone;
    }
    public void requestFocusOnPhonePostValue(){
        requestFocusOnPhone.setValue(new Event<>(new None()));
    }
    //endregion

    //region Goto verify page
    private MutableLiveData<Event<None>> goToVerifyPage = new MutableLiveData<>();
    public LiveData<Event<None>> getGoToVerifyPage(){
        return goToVerifyPage;
    }
    public void goToVerifyPagePostValue(){
        goToVerifyPage.setValue(new Event<>(new None()));
    }
    //endregion

    //region Goto home page
    private MutableLiveData<Event<None>> goToHomePage = new MutableLiveData<>();
    public LiveData<Event<None>> getGoToHomePage(){
        return goToHomePage;
    }
    public void goToHomePagePostValue(){
        goToHomePage.setValue(new Event<>(new None()));
    }
    //endregion
}
