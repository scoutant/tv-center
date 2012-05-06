package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;

/**
 * @author scoutant
 * show informations corresponding the selected program.
 */
public class HeaderView extends View {
	private static final long serialVersionUID = -6376793187222055930L;
	private static final Logger log = Logger.getLogger( HeaderView.class);
	private JLabel icon;
	
	public HeaderView( int width, int height) {
		super();
    	setPreferredSize( new Dimension(width, height ));
    	setLayout( new BoxLayout(this, BoxLayout.LINE_AXIS));
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.ORANGE), this.getBorder()));
    	setOpaque(false);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		log.info("stateChanged, in Headerview : " );
		if (icon!=null) remove( icon);
		Channel c = App.model().guide.channel();
		if (c==null) return;
		try {
//			icon = new JLabel( new ImageIcon( new URL("http://telepoche.guidetele.com/medias/chaines/france4.gif") ));
			icon = new JLabel( new ImageIcon( new URL( c.icon) ));
			add( icon );
		} catch (MalformedURLException exception) {
			log.error(exception);
		}
	}
}