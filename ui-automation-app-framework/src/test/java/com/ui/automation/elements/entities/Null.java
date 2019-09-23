package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Null{

	@SerializedName("id_publisher")
	private Object idPublisher;

	@SerializedName("id_factor")
	private int idFactor;

	@SerializedName("expiration_date")
	private Object expirationDate;

	@SerializedName("id_campaign")
	private Object idCampaign;

	public void setIdPublisher(Object idPublisher){
		this.idPublisher = idPublisher;
	}

	public Object getIdPublisher(){
		return idPublisher;
	}

	public void setIdFactor(int idFactor){
		this.idFactor = idFactor;
	}

	public int getIdFactor(){
		return idFactor;
	}

	public void setExpirationDate(Object expirationDate){
		this.expirationDate = expirationDate;
	}

	public Object getExpirationDate(){
		return expirationDate;
	}

	public void setIdCampaign(Object idCampaign){
		this.idCampaign = idCampaign;
	}

	public Object getIdCampaign(){
		return idCampaign;
	}
}