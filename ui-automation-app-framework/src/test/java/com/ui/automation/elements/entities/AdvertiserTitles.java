package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AdvertiserTitles{

	@SerializedName("id_tracking_solution")
	private int idTrackingSolution;

	@SerializedName("id_advertiser")
	private String idAdvertiser;

	@SerializedName("advertisers")
	private Advertisers advertisers;

	@SerializedName("package_name")
	private String packageName;

	@SerializedName("description")
	private String description;

	@SerializedName("id_advertiser_title")
	private String idAdvertiserTitle;

	@SerializedName("trackingSolution")
	private TrackingSolution trackingSolution;

	public void setIdTrackingSolution(int idTrackingSolution){
		this.idTrackingSolution = idTrackingSolution;
	}

	public int getIdTrackingSolution(){
		return idTrackingSolution;
	}

	public void setIdAdvertiser(String idAdvertiser){
		this.idAdvertiser = idAdvertiser;
	}

	public String getIdAdvertiser(){
		return idAdvertiser;
	}

	public void setAdvertisers(Advertisers advertisers){
		this.advertisers = advertisers;
	}

	public Advertisers getAdvertisers(){
		return advertisers;
	}

	public void setPackageName(String packageName){
		this.packageName = packageName;
	}

	public String getPackageName(){
		return packageName;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setIdAdvertiserTitle(String idAdvertiserTitle){
		this.idAdvertiserTitle = idAdvertiserTitle;
	}

	public String getIdAdvertiserTitle(){
		return idAdvertiserTitle;
	}

	public void setTrackingSolution(TrackingSolution trackingSolution){
		this.trackingSolution = trackingSolution;
	}

	public TrackingSolution getTrackingSolution(){
		return trackingSolution;
	}
}