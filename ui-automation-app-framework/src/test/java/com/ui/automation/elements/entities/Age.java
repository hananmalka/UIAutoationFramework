package com.ui.automation.elements.entities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Age{

	@SerializedName("values")
	private List<String> values;

	@SerializedName("options")
	private List<OptionsItem> options;

	@SerializedName("description")
	private String description;

	@SerializedName("type")
	private String type;

	@SerializedName("id_targeting_type")
	private int idTargetingType;

	public void setValues(List<String> values){
		this.values = values;
	}

	public List<String> getValues(){
		return values;
	}

	public void setOptions(List<OptionsItem> options){
		this.options = options;
	}

	public List<OptionsItem> getOptions(){
		return options;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setIdTargetingType(int idTargetingType){
		this.idTargetingType = idTargetingType;
	}

	public int getIdTargetingType(){
		return idTargetingType;
	}
}