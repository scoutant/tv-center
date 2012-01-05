package org.scoutant.tvcenter.model;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

/**
 * the TV Guide Channel is a collection of programs. 
 * <channel id="C1.telepoche.com">
 *  <display-name>TF1</display-name>
 *  <icon src="http://telepoche.guidetele.com/medias/chaines/tf1.gif" />
 * </channel>
 */
public class Channel {
	
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
		return true;
	}

	public boolean right(){
		if (index>=programs.size()-1) return false;
		index++;
		return true;
	}
	
	public Program program(){
		return programs.get(index);
	}

	public void init() {
		// TODO adjust against current time
		index=0;
	}
}

