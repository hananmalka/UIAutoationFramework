package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AlertsItem{

	@SerializedName("orderIndex")
	private int orderIndex;

	@SerializedName("label")
	private String label;

	@SerializedName("type")
	private String type;

	@SerializedName("enabled")
	private boolean enabled;

	public void setOrderIndex(int orderIndex){
		this.orderIndex = orderIndex;
	}

	public int getOrderIndex(){
		return orderIndex;
	}

	public void setLabel(String label){
		this.label = label;
	}

	public String getLabel(){
		return label;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}

	public boolean isEnabled(){
		return enabled;
	}
}