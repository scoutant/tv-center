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
public abstract class ProgramItemView extends View {
	private static final long serialVersionUID = 7407997357438197229L;
	private static final Logger log = Logger.getLogger( ProgramItemView.class);
	private static final int MARGIN = 100;
	private static final int PADDING = 20;
	
	private JLabel v = new JLabel("Brad Pitt", JLabel.LEFT); ;
	private Font f = v.getFont();
	
	public ProgramItemView( int width, int height, String label, int size) {
		super();
    	setLayout(null);
    	setPreferredSize(new Dimension(width, height));
//    	log.debug( "width : " + width + ", height : " + height);
    	setBackground( Color.green);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.MAGENTA), this.getBorder()));
    	setOpaque(false);
    	JLabel l = new JLabel(label, JLabel.RIGHT);
    	l.setBounds(0,  0, MARGIN, height);
    	add ( l);
    	v.setBounds(MARGIN+PADDING,  0, width-MARGIN-PADDING, height);
    	v.setFont( f.deriveFont(f.getStyle() ^ Font.BOLD, size));
    	add ( v);
    	repaint();
	}

	public ProgramItemView( int width, int height, String label, int size, int boldness) {
		this(width, height, label, size);
		v.setFont( f.deriveFont(f.getStyle() ^ boldness, size));
	}
	

	protected void set( String text) {
		v.setText(text);
	}
	
}
