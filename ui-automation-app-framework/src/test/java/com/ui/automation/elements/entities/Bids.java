package com.ui.automation.elements.entities;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Bids{

	@SerializedName("geos")
	private List<GeosItem> geos;

	@SerializedName("rate")
	private String rate;

	public void setGeos(List<GeosItem> geos){
		this.geos = geos;
	}

	public List<GeosItem> getGeos(){
		return geos;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public class BidsFields {

	}
}