package com.amzi.dao;

public class Message {
	private boolean error;
    private String htmlText;
    private int num;
    private String htmlTextTwo;
    
    public void setText (String message){
    	this.htmlText = message;
    }
    public void setTextTwo (String message){
    	this.htmlTextTwo = message;
    }
    
    public void setNum (int number){
    	num = number;
    }
    
public void setError (boolean error){
	this.error = error;
	
}
}

