package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("username")
    @Expose
    private String username;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}