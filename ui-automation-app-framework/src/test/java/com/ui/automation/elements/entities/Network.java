package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Network{

	@SerializedName("id_status")
	private int idStatus;

	@SerializedName("went_live")
	private Object wentLive;

	@SerializedName("source")
	private Object source;

	@SerializedName("status")
	private String status;

	public void setIdStatus(int idStatus){
		this.idStatus = idStatus;
	}

	public int getIdStatus(){
		return idStatus;
	}

	public void setWentLive(Object wentLive){
		this.wentLive = wentLive;
	}

	public Object getWentLive(){
		return wentLive;
	}

	public void setSource(Object source){
		this.source = source;
	}

	public Object getSource(){
		return source;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}