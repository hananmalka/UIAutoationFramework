package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AdditionalData{

	@SerializedName("campaign_name")
	private String campaignName;

	@SerializedName("app_package")
	private String appPackage;

	@SerializedName("entity_link")
	private String entityLink;

	@SerializedName("advertiser_name")
	private String advertiserName;

	public void setCampaignName(String campaignName){
		this.campaignName = campaignName;
	}

	public String getCampaignName(){
		return campaignName;
	}

	public void setAppPackage(String appPackage){
		this.appPackage = appPackage;
	}

	public String getAppPackage(){
		return appPackage;
	}

	public void setEntityLink(String entityLink){
		this.entityLink = entityLink;
	}

	public String getEntityLink(){
		return entityLink;
	}

	public void setAdvertiserName(String advertiserName){
		this.advertiserName = advertiserName;
	}

	public String getAdvertiserName(){
		return advertiserName;
	}
}