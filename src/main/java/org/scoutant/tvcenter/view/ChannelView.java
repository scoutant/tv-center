package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.model.Channel;
import org.scoutant.tvcenter.model.Program;

public class ChannelView extends JPanel implements ChangeListener{

	private static final long serialVersionUID = 7966178905763354703L;
	/** pixels representing 1 minute */ 
	public static final int MINUTE = 8;
	private static final Logger log = Logger.getLogger( ChannelView.class);
	private Channel channel;
	private List<JComponent> views = new ArrayList<JComponent>();
	
	public ChannelView( Channel channel) {
		super();
    	setLayout(null);
    	this.channel = channel;
    	channel.addListener(this);
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.red), this.getBorder()));
    	
//    	this.setFocusable(true);
//		this.addKeyListener( new KeyPressed());

		int i=0;
		for(Program p : channel.programs) {
//			ProgramWidget pw = new ProgramWidget( p, 0+200*i, 10);
			ProgramWidget pw = new ProgramWidget( p);
			add( pw);
			views.add(pw);
			i++;
		}
	}

	// TODO define a Refreasable interface?
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
