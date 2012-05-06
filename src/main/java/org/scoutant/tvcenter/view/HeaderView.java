package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;

/**
 * @author scoutant
 * show informations corresponding the selected program.
 */
public class HeaderView extends View {
	private static final long serialVersionUID = -6376793187222055930L;
	private static final Logger log = Logger.getLogger( HeaderView.class);
	
	public HeaderView( int width, int height) {
		super();
    	setPreferredSize( new Dimension(width, height ));
//    	setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	setLayout( new BoxLayout(this, BoxLayout.LINE_AXIS));
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.ORANGE), this.getBorder()));
    	setOpaque(false);
//    	add( new TitleView(		width, h));
    	add (new ProgramView(getWidth(), height ));
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		log.info("stateChanged, in Headerview : " );
		for (Object o : getComponents())  {
			log.info("stateChanged, o  : " + o);
			if (o instanceof ChangeListener) {
				((ChangeListener)o).stateChanged(e);
			}
		}
	}
	
	
}