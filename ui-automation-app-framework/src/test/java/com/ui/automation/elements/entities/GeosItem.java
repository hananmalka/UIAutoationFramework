package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class GeosItem{

	@SerializedName("id_cc")
	private int idCc;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	public void setIdCc(int idCc){
		this.idCc = idCc;
	}

	public int getIdCc(){
		return idCc;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}