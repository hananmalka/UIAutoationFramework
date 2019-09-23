package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Alerts{

	@SerializedName("installs_daily")
	private InstallsDaily installsDaily;

	public void setInstallsDaily(InstallsDaily installsDaily){
		this.installsDaily = installsDaily;
	}

	public InstallsDaily getInstallsDaily(){
		return installsDaily;
	}
}