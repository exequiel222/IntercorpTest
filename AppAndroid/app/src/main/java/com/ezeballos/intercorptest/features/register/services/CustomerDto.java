package com.ezeballos.intercorptest.features.register.services;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CustomerDto {
    public String uuid;
    public String name;
    public String surname;
    public String birthday;

    public CustomerDto() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public CustomerDto(String uuid, String name, String surname, String birthday) {
        this.uuid = uuid;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }
}
