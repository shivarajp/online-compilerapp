package com.masai.onlinecompiler.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResultResponseModel {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("language")
    @Expose
    private String lang;

    public String getRequestId() {
        return lang;
    }

    public void setRequestId(String requestId) {
        this.lang = requestId;
    }

    public String getData() {
        return code;
    }

    public void setData(String data) {
        this.code = data;
    }

}
