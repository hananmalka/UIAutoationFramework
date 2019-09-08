package com.ui.automation.elements.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackingSolution {

    @SerializedName("id_tracking_solution")
    @Expose
    private Integer idTrackingSolution;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("click_url")
    @Expose
    private String clickUrl;
    @SerializedName("impression_url")
    @Expose
    private String impressionUrl;
    @SerializedName("mandatory_parameters")
    @Expose
    private String mandatoryParameters;
    @SerializedName("optional_placeholders")
    @Expose
    private String optionalPlaceholders;
    @SerializedName("validate_impression_url")
    @Expose
    private Boolean validateImpressionUrl;
    @SerializedName("validate_click_url")
    @Expose
    private Boolean validateClickUrl;
    @SerializedName("allow_impression_autofill")
    @Expose
    private Boolean allowImpressionAutofill;
    @SerializedName("require_click_url")
    @Expose
    private Boolean requireClickUrl;
    @SerializedName("validate_landing_page_url")
    @Expose
    private Boolean validateLandingPageUrl;
    @SerializedName("parametersReplacements")
    @Expose
    private List<Object> parametersReplacements = null;

    public Integer getIdTrackingSolution() {
        return idTrackingSolution;
    }

    public void setIdTrackingSolution(Integer idTrackingSolution) {
        this.idTrackingSolution = idTrackingSolution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getImpressionUrl() {
        return impressionUrl;
    }

    public void setImpressionUrl(String impressionUrl) {
        this.impressionUrl = impressionUrl;
    }

    public String getMandatoryParameters() {
        return mandatoryParameters;
    }

    public void setMandatoryParameters(String mandatoryParameters) {
        this.mandatoryParameters = mandatoryParameters;
    }

    public String getOptionalPlaceholders() {
        return optionalPlaceholders;
    }

    public void setOptionalPlaceholders(String optionalPlaceholders) {
        this.optionalPlaceholders = optionalPlaceholders;
    }

    public Boolean getValidateImpressionUrl() {
        return validateImpressionUrl;
    }

    public void setValidateImpressionUrl(Boolean validateImpressionUrl) {
        this.validateImpressionUrl = validateImpressionUrl;
    }

    public Boolean getValidateClickUrl() {
        return validateClickUrl;
    }

    public void setValidateClickUrl(Boolean validateClickUrl) {
        this.validateClickUrl = validateClickUrl;
    }

    public Boolean getAllowImpressionAutofill() {
        return allowImpressionAutofill;
    }

    public void setAllowImpressionAutofill(Boolean allowImpressionAutofill) {
        this.allowImpressionAutofill = allowImpressionAutofill;
    }

    public Boolean getRequireClickUrl() {
        return requireClickUrl;
    }

    public void setRequireClickUrl(Boolean requireClickUrl) {
        this.requireClickUrl = requireClickUrl;
    }

    public Boolean getValidateLandingPageUrl() {
        return validateLandingPageUrl;
    }

    public void setValidateLandingPageUrl(Boolean validateLandingPageUrl) {
        this.validateLandingPageUrl = validateLandingPageUrl;
    }

    public List<Object> getParametersReplacements() {
        return parametersReplacements;
    }

    public void setParametersReplacements(List<Object> parametersReplacements) {
        this.parametersReplacements = parametersReplacements;
    }

}