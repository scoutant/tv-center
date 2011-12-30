package org.scoutant.mvc;

import java.util.Map;

import org.scoutant.tvcenter.App;

public class EventWith<T> extends Event {
	private T t;
	public EventWith(String name, T value) {
		super(name);
		this.t = value;
	}
	@Override
	public void dispatch() {
		System.out.println("dispathing from : " + this);
		@SuppressWarnings("rawtypes")
		Map<String, CommandWith> commands = App.context().getBeansOfType( CommandWith.class);
		if (commands==null ) return;
		for (CommandWith<T> c :  commands.values()) {
			if ( name.equals(c.getEvent())) {
				System.out.println( "command : " + c.getClass().getName());
				// here we may run a ClassCastException, but overkill to check with generics...
				c.execute( this.t);
			}
		}
	}
}
