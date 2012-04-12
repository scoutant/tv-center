package org.scoutant.tvcenter.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.model.Channel;

/**
 * @author scoutant
 * show informations corresponding the selected program.
 */
public class ProgramView extends JPanel implements ChangeListener{
	private static final long serialVersionUID = -6376793187222055930L;
	private static final Logger log = Logger.getLogger( ProgramView.class);
	
	public ProgramView( int width, int height) {
		super();
    	setSize( width, height);
    	setLayout(null);
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.GREEN), this.getBorder()));
    	setOpaque(false);
	}

	public void refresh(){
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		refresh();
	}
	
}
