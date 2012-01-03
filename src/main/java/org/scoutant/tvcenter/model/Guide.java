package org.scoutant.tvcenter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * the TV Guide is a collection of Tv programs organized by channel
 */
public class Guide {
	
	public List<Channel> channels = new ArrayList<Channel>();
	public Guide add(Channel o) {
		channels.add( o);
		return this;
	}
	
	@Override 
	public String toString() {
		String str="";
		for (Channel c : channels) {
			str += c + "\n";
		}
		return str;
	}
	
	public Channel find(String value) {
		for(Channel c : channels) {
			if (c.id.equals(value)) return c;
		}
		return null;
	}
	
	
}
