package com.ezeballos.intercorptest.features.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ezeballos.intercorptest.R;
import com.ezeballos.intercorptest.core.ui.BaseActivityLiveData;
import com.ezeballos.intercorptest.databinding.ActivityRegisterBinding;
import com.ezeballos.intercorptest.features.home.MainActivity;
import com.ezeballos.intercorptest.features.register.services.CustomerDto;
import com.ezeballos.intercorptest.features.register.services.IRegisterService;
import com.ezeballos.intercorptest.features.register.services.RegisterService;

public class RegisterActivity extends BaseActivityLiveData<ActivityRegisterBinding> {

    public static final String UUID_KEY = "UUID_KEY";

    IRegisterService service = new RegisterService();
    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(UUID_KEY)){
            uuid = getIntent().getStringExtra(UUID_KEY);
        }
    }

    public void onClickRegister(View view) {
        String name = vBinding.editPhoneNumber.getText().toString();
        String surname = vBinding.editSurname.getText().toString();
        String birthday = vBinding.editAge.getText().toString();
        if (!name.isEmpty() && !surname.isEmpty() && !birthday.isEmpty()){
            service.register(new CustomerDto(uuid,name, surname, birthday));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            vBinding.txtMessage.setVisibility(View.VISIBLE);
            vBinding.txtMessage.setText("Complete los campos requeridos");
        }
    }

    @NonNull
    @Override
    protected ActivityRegisterBinding getViewBinding() {
        return ActivityRegisterBinding.inflate(getLayoutInflater());
    }

    @Override
    public void initObserversOfViewModel() {
        //Nothing for this test
    }
}
