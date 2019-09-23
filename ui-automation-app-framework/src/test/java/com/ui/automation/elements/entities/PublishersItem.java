package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class PublishersItem{

	@SerializedName("id_publisher")
	private int idPublisher;

	@SerializedName("network_publisher_id")
	private String networkPublisherId;

	@SerializedName("internal_publisher_id")
	private String internalPublisherId;

	public void setIdPublisher(int idPublisher){
		this.idPublisher = idPublisher;
	}

	public int getIdPublisher(){
		return idPublisher;
	}

	public void setNetworkPublisherId(String networkPublisherId){
		this.networkPublisherId = networkPublisherId;
	}

	public String getNetworkPublisherId(){
		return networkPublisherId;
	}

	public void setInternalPublisherId(String internalPublisherId){
		this.internalPublisherId = internalPublisherId;
	}

	public String getInternalPublisherId(){
		return internalPublisherId;
	}
}