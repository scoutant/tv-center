package org.scoutant.mvc;

public abstract class BaseCommand {
	
	protected String event;
	public void setEvent(String value){
		event = value;
	}
	public String getEvent(){
		return event;
	}
}
