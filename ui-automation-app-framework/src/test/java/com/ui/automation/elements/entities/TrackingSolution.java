package com.ui.automation.elements.entities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TrackingSolution{

	@SerializedName("optional_placeholders")
	private String optionalPlaceholders;

	@SerializedName("validate_click_url")
	private boolean validateClickUrl;

	@SerializedName("validate_landing_page_url")
	private boolean validateLandingPageUrl;

	@SerializedName("parametersReplacements")
	private List<Object> parametersReplacements;

	@SerializedName("allow_impression_autofill")
	private boolean allowImpressionAutofill;

	@SerializedName("id_tracking_solution")
	private int idTrackingSolution;

	@SerializedName("mandatory_parameters")
	private String mandatoryParameters;

	@SerializedName("name")
	private String name;

	@SerializedName("validate_impression_url")
	private boolean validateImpressionUrl;

	@SerializedName("logo")
	private String logo;

	@SerializedName("impression_url")
	private String impressionUrl;

	@SerializedName("click_url")
	private String clickUrl;

	@SerializedName("require_click_url")
	private boolean requireClickUrl;

	public void setOptionalPlaceholders(String optionalPlaceholders){
		this.optionalPlaceholders = optionalPlaceholders;
	}

	public String getOptionalPlaceholders(){
		return optionalPlaceholders;
	}

	public void setValidateClickUrl(boolean validateClickUrl){
		this.validateClickUrl = validateClickUrl;
	}

	public boolean isValidateClickUrl(){
		return validateClickUrl;
	}

	public void setValidateLandingPageUrl(boolean validateLandingPageUrl){
		this.validateLandingPageUrl = validateLandingPageUrl;
	}

	public boolean isValidateLandingPageUrl(){
		return validateLandingPageUrl;
	}

	public void setParametersReplacements(List<Object> parametersReplacements){
		this.parametersReplacements = parametersReplacements;
	}

	public List<Object> getParametersReplacements(){
		return parametersReplacements;
	}

	public void setAllowImpressionAutofill(boolean allowImpressionAutofill){
		this.allowImpressionAutofill = allowImpressionAutofill;
	}

	public boolean isAllowImpressionAutofill(){
		return allowImpressionAutofill;
	}

	public void setIdTrackingSolution(int idTrackingSolution){
		this.idTrackingSolution = idTrackingSolution;
	}

	public int getIdTrackingSolution(){
		return idTrackingSolution;
	}

	public void setMandatoryParameters(String mandatoryParameters){
		this.mandatoryParameters = mandatoryParameters;
	}

	public String getMandatoryParameters(){
		return mandatoryParameters;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValidateImpressionUrl(boolean validateImpressionUrl){
		this.validateImpressionUrl = validateImpressionUrl;
	}

	public boolean isValidateImpressionUrl(){
		return validateImpressionUrl;
	}

	public void setLogo(String logo){
		this.logo = logo;
	}

	public String getLogo(){
		return logo;
	}

	public void setImpressionUrl(String impressionUrl){
		this.impressionUrl = impressionUrl;
	}

	public String getImpressionUrl(){
		return impressionUrl;
	}

	public void setClickUrl(String clickUrl){
		this.clickUrl = clickUrl;
	}

	public String getClickUrl(){
		return clickUrl;
	}

	public void setRequireClickUrl(boolean requireClickUrl){
		this.requireClickUrl = requireClickUrl;
	}

	public boolean isRequireClickUrl(){
		return requireClickUrl;
	}
}