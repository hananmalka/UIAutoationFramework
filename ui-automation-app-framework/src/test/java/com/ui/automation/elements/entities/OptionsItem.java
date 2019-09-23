package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class OptionsItem{

	@SerializedName("inputType")
	private String inputType;

	@SerializedName("value")
	private String value;

	public void setInputType(String inputType){
		this.inputType = inputType;
	}

	public String getInputType(){
		return inputType;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
}