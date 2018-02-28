package com.example.gagan.google.pojoclass.db;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import com.example.gagan.google.interfaces.Validity;
import com.example.gagan.google.utils.Constant;
import com.example.gagan.google.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan on 2/15/2018.
 */

public class User extends BaseObservable implements Validity {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String uri;
    private String profilePicId;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean Valid() {
        ;
        if (Utils.checkIsEmpty(new String[]{name, email, phone})) {
            return false;
        }
        if (!Utils.CheckPhoneValid(phone))
            return false;
        return true;
    }

    @Override
    public String validString() {
        if (TextUtils.isEmpty(name)) return "Name cant be empty";
        if (TextUtils.isEmpty(email)) return "Email cant be empty";
        if (TextUtils.isEmpty(phone)) return "Phone number cant be empty";
        if (!Utils.CheckPhoneValid(phone)) return "Phone number not valid";
        return "";
    }

    public void setProfilePicId(String profilePicId) {
        this.profilePicId = profilePicId;
    }

    public String getProfilePicId() {
        return profilePicId;
    }
}
