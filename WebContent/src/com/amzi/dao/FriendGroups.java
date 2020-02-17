package com.amzi.dao;

public class FriendGroups {
	public String  groupName;
	public boolean admin = false;
	public boolean creator = false;
	public boolean generalUser = false;
	
	public FriendGroups (){
		
	}
	public FriendGroups (String groupName, boolean admin, boolean creator, boolean generalUser){
		this.groupName = groupName;
		this.admin = admin;
		this.creator = creator;
		this.generalUser = generalUser;
	}
	public void setGroupName (String groupName){
		this.groupName = groupName;
		
	}
	public void setAdmin (boolean admin){
		this.admin = admin;
		
	}
	public void setCreator (boolean creator){
		this.creator = creator;
		
	}
	public void setGeneralUser (boolean generalUser){
		this.generalUser = generalUser;
		
	}
}
