package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class CappingRulesItem{

	@SerializedName("current")
	private int current;

	@SerializedName("cap")
	private String cap;

	@SerializedName("key")
	private String key;

	public void setCurrent(int current){
		this.current = current;
	}

	public int getCurrent(){
		return current;
	}

	public void setCap(String cap){
		this.cap = cap;
	}

	public String getCap(){
		return cap;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}
}