package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Notifications{

	@SerializedName("icon_url")
	private String iconUrl;

	@SerializedName("layout")
	private int layout;

	@SerializedName("background_color")
	private String backgroundColor;

	@SerializedName("collapsed_background_image_url")
	private String collapsedBackgroundImageUrl;

	@SerializedName("expanded_background_image_url")
	private String expandedBackgroundImageUrl;

	@SerializedName("banner_url")
	private String bannerUrl;

	@SerializedName("text_color")
	private String textColor;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	public void setIconUrl(String iconUrl){
		this.iconUrl = iconUrl;
	}

	public String getIconUrl(){
		return iconUrl;
	}

	public void setLayout(int layout){
		this.layout = layout;
	}

	public int getLayout(){
		return layout;
	}

	public void setBackgroundColor(String backgroundColor){
		this.backgroundColor = backgroundColor;
	}

	public String getBackgroundColor(){
		return backgroundColor;
	}

	public void setCollapsedBackgroundImageUrl(String collapsedBackgroundImageUrl){
		this.collapsedBackgroundImageUrl = collapsedBackgroundImageUrl;
	}

	public String getCollapsedBackgroundImageUrl(){
		return collapsedBackgroundImageUrl;
	}

	public void setExpandedBackgroundImageUrl(String expandedBackgroundImageUrl){
		this.expandedBackgroundImageUrl = expandedBackgroundImageUrl;
	}

	public String getExpandedBackgroundImageUrl(){
		return expandedBackgroundImageUrl;
	}

	public void setBannerUrl(String bannerUrl){
		this.bannerUrl = bannerUrl;
	}

	public String getBannerUrl(){
		return bannerUrl;
	}

	public void setTextColor(String textColor){
		this.textColor = textColor;
	}

	public String getTextColor(){
		return textColor;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}
}