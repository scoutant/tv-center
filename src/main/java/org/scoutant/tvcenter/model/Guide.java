package org.scoutant.tvcenter.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		String str="index : " + index +"\n";
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
	
	
	public Channel channel(int p) {
		return channels.get( p);
	}
	
	public Channel channel() {
		return channels.get(index);
	}
	
	private int index;

	public boolean down(){
		if (index>= channels.size()-1) return false;
		index++;
		channel().init();
		fireEvent();
		return true;
	}
	public boolean up(){
		if (index<=0 ) return false;
		index--;
		channel().init();
		fireEvent();
		return true;
	}

	public void init() {
		index=0;
		channel().init();
	}
	
	private List<ChangeListener> listeners = new ArrayList<ChangeListener>();
	public void addListener(ChangeListener l) {
		listeners.add( l);
	}
	private void fireEvent(){
		for(ChangeListener l : listeners) {
			l.stateChanged( new ChangeEvent(this));
		}
	}
	
}
