package org.scoutant.tvcenter.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;
import org.scoutant.tvcenter.model.Program;

public class ChannelView extends JPanel {

	private static final long serialVersionUID = 7966178905763354703L;
	/** pixels representing 1 minute */ 
	public static final int MINUTE = 8;
	private static final Logger log = Logger.getLogger( ChannelView.class);
	private Channel channel;
	
	public ChannelView( Channel channel) {
		super();
    	setLayout(null);
    	this.channel = channel;
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.red), this.getBorder()));
    	
//    	this.setFocusable(true);
//		this.addKeyListener( new KeyPressed());

		int i=0;
		for(Program p : channel.programs) {
			log.debug("prog " + p);
			add( new ProgramWidget( p, 0+200*i, 10));
			i++;
		}
		
		
	}
}
