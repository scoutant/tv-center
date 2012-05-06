package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.model.Channel;
import org.scoutant.tvcenter.model.Program;

public class ChannelView extends View {

	private static final long serialVersionUID = 7966178905763354703L;
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( ChannelView.class);
	private List<JComponent> views = new ArrayList<JComponent>();
	
	public ChannelView( Channel channel) {
		super();
    	setLayout(null);
    	channel.addListener(this);
    	setBackground( Color.lightGray);
//    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.red), this.getBorder()));
    	setOpaque(false);
		for(Program p : channel.programs) {
			ProgramWidget pw = new ProgramWidget( p);
			add( pw);
			views.add(pw);
		}
	}

	public void refresh(){
		for (JComponent c : views) {
			c.repaint();
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		refresh();
	}
	
}
