package org.scoutant.tvcenter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
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
public abstract class ProgramItemView extends JPanel implements ChangeListener {
	private static final long serialVersionUID = 7407997357438197229L;
	private static final Logger log = Logger.getLogger( ProgramItemView.class);
	private static final int MARGIN = 100;
	private static final int PADDING = 20;
	
	private JLabel v;
	
	public ProgramItemView( int width, int height, String label) {
		super();
    	setLayout(null);
    	setPreferredSize(new Dimension(width, height));
//    	log.debug( "width : " + width + ", height : " + height);
    	setBackground( Color.green);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.MAGENTA), this.getBorder()));
    	setOpaque(false);
    	JLabel l = new JLabel(label, JLabel.RIGHT);
    	Font f = l.getFont();
    	l.setFont( f.deriveFont(f.getStyle() ^ Font.BOLD));
    	l.setBounds(0,  0, MARGIN, height);
    	add ( l);
    	v = new JLabel("Brad Pitt", JLabel.LEFT); 
    	v.setBounds(MARGIN+PADDING,  0, width-MARGIN-PADDING, height);
    	add ( v);
    	repaint();
	}
	

	protected void set( String text) {
		v.setText(text);
//		Program p = App.model().guide.channel().program();
		// TODO the prop refresh!
//		p.title  
	}
	
//	@Override
//	public void stateChanged(ChangeEvent e) {
//	}
	
}
