package com.ui.automation.elements.entities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class InstallsDaily{

	@SerializedName("alerts")
	private List<AlertsItem> alerts;

	@SerializedName("rule")
	private String rule;

	public void setAlerts(List<AlertsItem> alerts){
		this.alerts = alerts;
	}

	public List<AlertsItem> getAlerts(){
		return alerts;
	}

	public void setRule(String rule){
		this.rule = rule;
	}

	public String getRule(){
		return rule;
	}
}