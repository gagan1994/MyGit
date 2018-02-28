package com.example.gagan.google.pojoclass.mappojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gagan on 2/14/2018.
 */

public class MapPojo {
    @SerializedName("address_components")
    List<AddressPojo> addressComponents;
    @SerializedName("formatted_address")
    String address;
    @SerializedName("place_id")
    String placeId;
    @SerializedName("types")
    List<String> types;

    public List<AddressPojo> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressPojo> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
