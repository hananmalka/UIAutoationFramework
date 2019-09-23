package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Advertisers{

	@SerializedName("owner")
	private int owner;

	@SerializedName("id_status")
	private int idStatus;

	@SerializedName("id_advertiser")
	private int idAdvertiser;

	@SerializedName("support_install_intent")
	private boolean supportInstallIntent;

	@SerializedName("is_rev_share")
	private boolean isRevShare;

	@SerializedName("id_sf_advertiser")
	private String idSfAdvertiser;

	@SerializedName("support_launch_intent")
	private boolean supportLaunchIntent;

	@SerializedName("name")
	private String name;

	@SerializedName("salesforce_id")
	private String salesforceId;

	@SerializedName("token")
	private Object token;

	public void setOwner(int owner){
		this.owner = owner;
	}

	public int getOwner(){
		return owner;
	}

	public void setIdStatus(int idStatus){
		this.idStatus = idStatus;
	}

	public int getIdStatus(){
		return idStatus;
	}

	public void setIdAdvertiser(int idAdvertiser){
		this.idAdvertiser = idAdvertiser;
	}

	public int getIdAdvertiser(){
		return idAdvertiser;
	}

	public void setSupportInstallIntent(boolean supportInstallIntent){
		this.supportInstallIntent = supportInstallIntent;
	}

	public boolean isSupportInstallIntent(){
		return supportInstallIntent;
	}

	public void setIsRevShare(boolean isRevShare){
		this.isRevShare = isRevShare;
	}

	public boolean isIsRevShare(){
		return isRevShare;
	}

	public void setIdSfAdvertiser(String idSfAdvertiser){
		this.idSfAdvertiser = idSfAdvertiser;
	}

	public String getIdSfAdvertiser(){
		return idSfAdvertiser;
	}

	public void setSupportLaunchIntent(boolean supportLaunchIntent){
		this.supportLaunchIntent = supportLaunchIntent;
	}

	public boolean isSupportLaunchIntent(){
		return supportLaunchIntent;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setSalesforceId(String salesforceId){
		this.salesforceId = salesforceId;
	}

	public String getSalesforceId(){
		return salesforceId;
	}

	public void setToken(Object token){
		this.token = token;
	}

	public Object getToken(){
		return token;
	}
}