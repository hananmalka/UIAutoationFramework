package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class NullItem{

	@SerializedName("id_placement")
	private int idPlacement;

	@SerializedName("id_publisher")
	private Object idPublisher;

	@SerializedName("percentage")
	private int percentage;

	@SerializedName("active")
	private boolean active;

	@SerializedName("id_campaign")
	private Object idCampaign;

	public void setIdPlacement(int idPlacement){
		this.idPlacement = idPlacement;
	}

	public int getIdPlacement(){
		return idPlacement;
	}

	public void setIdPublisher(Object idPublisher){
		this.idPublisher = idPublisher;
	}

	public Object getIdPublisher(){
		return idPublisher;
	}

	public void setPercentage(int percentage){
		this.percentage = percentage;
	}

	public int getPercentage(){
		return percentage;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
	}

	public void setIdCampaign(Object idCampaign){
		this.idCampaign = idCampaign;
	}

	public Object getIdCampaign(){
		return idCampaign;
	}
}