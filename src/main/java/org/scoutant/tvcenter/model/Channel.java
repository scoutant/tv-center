package org.scoutant.tvcenter.model;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.scoutant.tvcenter.App;
import org.xml.sax.Attributes;

/**
 * the TV Guide Channel is a collection of programs. 
 * <channel id="C1.telepoche.com">
 *  <display-name>TF1</display-name>
 *  <icon src="http://telepoche.guidetele.com/medias/chaines/tf1.gif" />
 * </channel>
 */
public class Channel implements Serializable {
	
	private static final long serialVersionUID = 9126743732976715575L;
	public String id;
	public List<Program> programs = new ArrayList<Program>();
	public String name;
	public String icon;

	private int index;
	
	public Channel(Attributes atts) {
		this.id = atts.getValue("id");
	}

	public Channel add(Program o) {
		programs.add(o);
		return this;
	}
	
	@Override
	public String toString() {
		String str = "index : " + index +" ----- ";  
		str += "Channel [ id=" + id + ", name=" + name + ", icon=" + icon ;
		str += ", # programs : " + programs.size();
		return str + "]";
	}

	public boolean left(){
		if (index<=0) return false;
		index--;
		fireEvent();
		return true;
	}

	public boolean right(){
		if (index>=programs.size()-1) return false;
		index++;
		fireEvent();
		return true;
	}
	
	public Program program(){
		return programs.get(index);
	}

	public void init() {
		index=indexNow();
		fireEvent();
	}
	
	private int indexNow(){
		for (int i=0; i<programs.size(); i++) {
			if (programs.get(i).stop>App.model().now) return i; 
		}
		return 0;
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

