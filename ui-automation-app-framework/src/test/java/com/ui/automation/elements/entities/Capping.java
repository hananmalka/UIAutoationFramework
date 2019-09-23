package com.ui.automation.elements.entities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Capping{

	@SerializedName("alerts")
	private Alerts alerts;

	@SerializedName("additional_data")
	private AdditionalData additionalData;

	@SerializedName("automatically_managed")
	private boolean automaticallyManaged;

	@SerializedName("email_list")
	private List<Object> emailList;

	@SerializedName("capping_rules")
	private List<CappingRulesItem> cappingRules;

	@SerializedName("lastUpdate")
	private String lastUpdate;

	@SerializedName("options")
	private List<Object> options;

	@SerializedName("start")
	private String start;

	@SerializedName("end")
	private String end;

	@SerializedName("enabled")
	private boolean enabled;

	@SerializedName("advertiser_id")
	private int advertiserId;

	@SerializedName("limitations")
	private List<Object> limitations;

	public void setAlerts(Alerts alerts){
		this.alerts = alerts;
	}

	public Alerts getAlerts(){
		return alerts;
	}

	public void setAdditionalData(AdditionalData additionalData){
		this.additionalData = additionalData;
	}

	public AdditionalData getAdditionalData(){
		return additionalData;
	}

	public void setAutomaticallyManaged(boolean automaticallyManaged){
		this.automaticallyManaged = automaticallyManaged;
	}

	public boolean isAutomaticallyManaged(){
		return automaticallyManaged;
	}

	public void setEmailList(List<Object> emailList){
		this.emailList = emailList;
	}

	public List<Object> getEmailList(){
		return emailList;
	}

	public void setCappingRules(List<CappingRulesItem> cappingRules){
		this.cappingRules = cappingRules;
	}

	public List<CappingRulesItem> getCappingRules(){
		return cappingRules;
	}

	public void setLastUpdate(String lastUpdate){
		this.lastUpdate = lastUpdate;
	}

	public String getLastUpdate(){
		return lastUpdate;
	}

	public void setOptions(List<Object> options){
		this.options = options;
	}

	public List<Object> getOptions(){
		return options;
	}

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setEnd(String end){
		this.end = end;
	}

	public String getEnd(){
		return end;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public boolean isEnabled(){
		return enabled;
	}

	public void setAdvertiserId(int advertiserId){
		this.advertiserId = advertiserId;
	}

	public int getAdvertiserId(){
		return advertiserId;
	}

	public void setLimitations(List<Object> limitations){
		this.limitations = limitations;
	}

	public List<Object> getLimitations(){
		return limitations;
	}
}