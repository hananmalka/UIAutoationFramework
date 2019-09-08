package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdvertiserTitles {

    @SerializedName("id_advertiser_title")
    @Expose
    private Integer idAdvertiserTitle;
    @SerializedName("id_advertiser")
    @Expose
    private Integer idAdvertiser;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id_tracking_solution")
    @Expose
    private Integer idTrackingSolution;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("advertisers")
    @Expose
    private List<Advertiser> advertisers;
//    @SerializedName("trackingSolution")
//    @Expose
//    private TrackingSolution trackingSolution;

    public Integer getIdAdvertiserTitle() {
        return idAdvertiserTitle;
    }

    public void setIdAdvertiserTitle(Integer idAdvertiserTitle) {
        this.idAdvertiserTitle = idAdvertiserTitle;
    }

    public Integer getIdAdvertiser() {
        return idAdvertiser;
    }

    public void setIdAdvertiser(Integer idAdvertiser) {
        this.idAdvertiser = idAdvertiser;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getIdTrackingSolution() {
        return idTrackingSolution;
    }

    public void setIdTrackingSolution(Integer idTrackingSolution) {
        this.idTrackingSolution = idTrackingSolution;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Advertiser> getAdvertisers() {
        return advertisers;
    }

    public void setAdvertisers(List<Advertiser> advertisers) {
        this.advertisers = advertisers;
    }

//    public TrackingSolution getTrackingSolution() {
//        return trackingSolution;
//    }
//
//    public void setTrackingSolution(TrackingSolution trackingSolution) {
//        this.trackingSolution = trackingSolution;
//    }

}