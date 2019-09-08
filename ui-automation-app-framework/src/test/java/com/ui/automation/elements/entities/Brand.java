package com.ui.automation.elements.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brand {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("values")
    @Expose
    private List<String> values = null;
    @SerializedName("id_targeting_type")
    @Expose
    private Integer idTargetingType;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("description")
    @Expose
    private String description;
//    @SerializedName("is_free_text_allowed")
//    @Expose
//    private IsFreeTextAllowed isFreeTextAllowed;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public Integer getIdTargetingType() {
        return idTargetingType;
    }

    public void setIdTargetingType(Integer idTargetingType) {
        this.idTargetingType = idTargetingType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public IsFreeTextAllowed getIsFreeTextAllowed() {
//        return isFreeTextAllowed;
//    }
//
//    public void setIsFreeTextAllowed(IsFreeTextAllowed isFreeTextAllowed) {
//        this.isFreeTextAllowed = isFreeTextAllowed;
//    }

}