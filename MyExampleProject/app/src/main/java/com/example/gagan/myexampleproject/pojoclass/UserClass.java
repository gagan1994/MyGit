package com.example.gagan.myexampleproject.pojoclass;

import android.text.TextUtils;

import com.example.gagan.myexampleproject.interfaces.uiinterface.HomeFragmentRecyclerModel;
import com.example.gagan.myexampleproject.utilhelper.Utils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Gagan on 3/1/2018.
 */

public class UserClass implements HomeFragmentRecyclerModel {
    @SerializedName("username")
    private String name;
    @SerializedName("pic")
    private String imageUri;
    @SerializedName("description")
    private String description;

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getHeading() {
        return Utils.checkNull(name);
    }


    @Override
    public String getDescription() {
        return Utils.checkNull(description);
    }

    @Override
    public String getImageurl() {
        return Utils.checkImageNull(imageUri);
    }
}
