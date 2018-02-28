package com.example.gagan.google.pojoclass.mappojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Gagan on 2/14/2018.
 */

public class MapResponse {
    @SerializedName("results")
    List<MapPojo> results;

    public List<MapPojo> getResults() {
        return results;
    }

    public void setResults(List<MapPojo> results) {
        this.results = results;
    }
}
