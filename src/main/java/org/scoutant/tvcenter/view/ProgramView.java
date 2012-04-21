package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.awt.Dimension;

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
public class ProgramView extends JPanel implements ChangeListener{
	private static final long serialVersionUID = -6376793187222055930L;
	private static final Logger log = Logger.getLogger( ProgramView.class);
	
	public ProgramView( int width, int height) {
		super();
//    	setSize( width, height);
//    	setLayout(null);
    	setPreferredSize( new Dimension(width, height ));
//    	setSize(new Dimension(width, height ));
//    	setBounds(0, 0, width, height);
    	setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.GREEN), this.getBorder()));
    	setOpaque(false);
    	
    	
    	int h = height/3;
    	
    	add( new TitleView(width, h, "TITLE"));
//    	add( new ProgramItemView(width, h));
    	
//    	repaint();
	}

	public void refresh(){
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		for (Object o : getComponents())  {
			log.info("stateChanged, o  : " + o);
			if (o instanceof ChangeListener) {
				((ChangeListener)o).stateChanged(e);
			}
		}
	}
	
	private class TitleView extends ProgramItemView {
		public TitleView(int width, int height, String label) {
			super(width, height, label);
		}
		private static final long serialVersionUID = 1L;
		@Override
		public void stateChanged(ChangeEvent e) {
			Program p = App.model().guide.channel().program();
			if (p!=null)
			set( p.title);
		}
	}
	
}