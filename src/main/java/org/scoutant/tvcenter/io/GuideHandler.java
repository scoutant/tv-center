package org.scoutant.tvcenter.io;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;
import org.scoutant.tvcenter.model.Guide;
import org.scoutant.tvcenter.model.Program;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class GuideHandler extends DefaultHandler {

	public static final int CHANNEL = 20;
	public static final int NAME = 21;

	public static final int PROGRAMME = 10;
	public static final int TITLE = 11;
	public static final int SUBTITLE = 12;
	public static final int DESC = 13;
	public static final int CREDITS = 14;
	public static final int YEAR = 15;
	public static final int COUNTRY = 16;
	
	private Guide guide = App.model().guide;
	private Channel c = null;
	private boolean inChannel = false;
	private Program p = null;
	private int element = 0;
	private static Logger log = Logger.getLogger( GuideHandler.class);
	
	public void startDocument () {
		log.info("Start document");
	}
	public void endDocument () {
		log.debug("# programs " + App.model().guide);
		p = null;
		log.info("End document");
	}

	public void startElement (String uri, String name, String e, Attributes atts) {
		if ( e == "channel") {
			c = new Channel(atts);
			inChannel=true;
		}
		if ( e == "icon") {
			if (inChannel) {
				c.icon = atts.getValue("src");
			}
		}
		if ( e == "programme") {
			try {
				p = new Program(atts);
			} catch (ParseException pe) {
				log.error(""+atts, pe);
			}
			if (!atts.getValue("channel").equals(c.id)) {
				c = guide.find( atts.getValue("channel"));
			}
		}
		if (e == "display-name" )	element = NAME;

		if (e == "title" )	element = TITLE;
		if (e == "sub-title") element = SUBTITLE;
		if (e == "desc") 	element = DESC;
		if (e == "director")element = CREDITS;
		if (e == "actor" ) 	element = CREDITS;
		if (e == "date") 	element = YEAR;
		if (e == "country")	element = COUNTRY;
	}

	public void endElement (String uri, String name, String e) {
		if ( e == "programme") {
			c.add(p);
		}
		if ( e == "channel") {
			guide.add(c);
			inChannel =false;
		}
		element=0;
	}

	public void characters (char ch[], int start, int length) {
		StringBuffer s = new StringBuffer();
		s.append( ch, start, length);

		if (element==NAME) c.name = ""+s; 

		if (element==TITLE) p.title = ""+s; 
		if (element==SUBTITLE) p.subtitle = ""+s; 
		if (element==DESC) p.desc += s+". "; 
		if (element==CREDITS) p.credits += s +", "; 
		if (element==YEAR) p.year = ""+s; 
		if (element==COUNTRY) p.country= ""+s; // keeping only last country data 
	}
}

/*
  <channel id="C1.telepoche.com">
    <display-name>TF1</display-name>
    <icon src="http://telepoche.guidetele.com/medias/chaines/tf1.gif" />
  </channel>

*/

/*
<programme start="20120110123500 +0100" stop="20120110133000 +0100" channel="C195.telepoche.com">
<title>Dr Quinn, femme médecin</title>
<title lang="en">Dr. Quinn, Medicine Woman</title>
<sub-title>Mort ou vif</sub-title>
<desc lang="fr">Erza Leenard, porteur d'une déclaration demandant que le territoire du Colorado soit érigé en Etat, s'arrête à Colorado Springs avant d'aller remettre le document à Washington. Pendant qu'il prononce son discours, son fils Caleb est enlevé par un certain McBride, opposé à ce projet</desc>
<desc lang="fr">Erza Leenard, porteur d'une déclaration demandant que le territoire du Colorado soit érigé en État, s'arrête à Colorado Springs avant d'aller remettre le document à Washington</desc>
<credits>
  <director>Chuck Bowman</director>
  <actor>Carl Binder</actor>
  <actor>Sullivan Company</actor>
  <actor>Columbia Broadcasting System (CBS)</actor>
</credits>
<date>1995</date>
*/