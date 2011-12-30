package org.scoutant.mvc;

import java.util.Map;

import org.scoutant.tvcenter.App;

public class Event {

	protected String name;
	public Event(String name) {
		if (name==null || name.length()==0) { 
			throw new IllegalArgumentException("Event name shall not be null!");
		}
		this.name = name;
	}
	@Override
	public String toString(){
		return "Event : " + this.name;
	}
	public void dispatch() {
//		System.out.println("dispathing from : " + this);
		Map<String, Command> commands = App.context().getBeansOfType( Command.class);
		if (commands==null ) return;
		for (Command c :  commands.values()) {
			if ( name.equals(c.getEvent())) {
//				System.out.println( "command : " + c.getClass().getName());
				c.execute();
			}
		}
	}
}
