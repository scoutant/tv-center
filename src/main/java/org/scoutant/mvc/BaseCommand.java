package org.scoutant.mvc;

import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Guide;

public abstract class BaseCommand {
	protected Guide guide = App.model().guide;
	
	protected String event;
	public void setEvent(String value){
		event = value;
	}
	public String getEvent(){
		return event;
	}
}
