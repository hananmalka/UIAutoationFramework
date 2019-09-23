package com.ui.automation.elements.entities;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Targeting{

	@SerializedName("age")
	private Age age;

	public void setAge(Age age){
		this.age = age;
	}

	public Age getAge(){
		return age;
	}
}