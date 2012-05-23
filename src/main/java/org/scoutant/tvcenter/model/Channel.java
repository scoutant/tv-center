package org.scoutant.tvcenter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
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
	private static final String[][] p = { 
		{ "C1.telepoche.com", "rtp://@232.0.1.17:8200"},
		{ "C2.telepoche.com", "rtp://@232.0.1.1:8200"},
		{ "C3.telepoche.com", "rtp://@232.0.1.2:8200"},
		{ "C4.telepoche.com", "rtp://@232.0.5.1:8200"},
		{ "C5.telepoche.com", "rtp://@232.0.1.5:8200"},
		{ "C6.telepoche.com", "rtp://@232.0.1.10:8200"},
		{ "C7.telepoche.com", "rtp://@232.0.1.4:8200"},
		{ "C9.telepoche.com", "rtp://@232.0.1.7:8200"},
		{ "C28.telepoche.com", "rtp://@232.0.1.9:8200"},
		{ "C193.telepoche.com", "rtp://@232.0.1.11:8200"},
		{ "C38.telepoche.com", "rtp://@232.0.1.43:8200"},
		{ "C169.telepoche.com", "rtp://@232.0.1.8:8200"},
		{ "C195.telepoche.com", "rtp://@232.0.1.12:8200"},
	};
	private static Map<String, String> playlist = new HashMap<String, String>();
	static {
		for (int j=0; j<p.length; j++) {
			playlist.put(p[j][0], p[j][1]);
		}
	}
	
	private static final long serialVersionUID = 9126743732976715575L;
	public String id;
	public List<Program> programs = new ArrayList<>();
	public String name;
	public String icon;
	public String url;
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(Channel.class);
	private int index;
	
	public Channel(Attributes atts) {
		this.id = atts.getValue("id");
		this.url = playlist.get( id);
//		log.debug("playlist lengt : " + playlist.size());
	}

	public Channel add(Program o) {
		programs.add(o);
		return this;
	}
	
	@Override
	public String toString() {
		String str = "index : " + index +" ----- ";  
		str += "Channel [ id=" + id + ", name=" + name + ", icon=" + icon + ", url=" + url ;
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
	
	public Program program(int time){
		for (Program p : programs) {
			if (time < p.stop ) return p;
		}
		return null;
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
	
	

	private List<ChangeListener> listeners = new ArrayList<>();
	public void addListener(ChangeListener l) {
		listeners.add( l);
	}
	private void fireEvent(){
		for(ChangeListener l : listeners) {
			l.stateChanged( new ChangeEvent(this));
		}
	}
	
}

