package com.ezeballos.intercorptest.features.register.services;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterService implements IRegisterService {

    @Override
    public void register(@NonNull CustomerDto customerDto) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customer");
        myRef.child("users").child(customerDto.uuid).setValue(customerDto);
    }
}
