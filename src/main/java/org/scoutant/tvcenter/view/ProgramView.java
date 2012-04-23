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
public class ProgramView extends JPanel implements ChangeListener{
	private static final long serialVersionUID = -6376793187222055930L;
	private static final Logger log = Logger.getLogger( ProgramView.class);
	
	public ProgramView( int width, int height) {
		super();
    	setPreferredSize( new Dimension(width, height ));
    	setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	setBackground( Color.lightGray);
    	setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.GREEN), this.getBorder()));
    	setOpaque(false);
    	int h = height/4;
    	add( new TitleView(		width, h));
    	add( new SubtitleView(	width, h));
    	add( new DescView(		width, h));
    	add( new CreditsView(	width, h));
//    	repaint();
	}

	public void refresh(){
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		for (Object o : getComponents())  {
//			log.info("stateChanged, o  : " + o);
			if (o instanceof ChangeListener) {
				((ChangeListener)o).stateChanged(e);
			}
		}
	}
	
	private class TitleView extends ProgramItemView {
		public TitleView(int width, int height) {
			super(width, height, "title", 45);
		}
		private static final long serialVersionUID = 1L;
		@Override
		public void stateChanged(ChangeEvent e) {
			Program p = App.model().guide.channel().program();
			if (p!=null)
			set( p.title);
		}
	}
	private class SubtitleView extends ProgramItemView {
		public SubtitleView(int width, int height) {
			super(width, height, "subtitle", 30);
		}
		private static final long serialVersionUID = 1L;
		@Override
		public void stateChanged(ChangeEvent e) {
			Program p = App.model().guide.channel().program();
			if (p!=null)
			set( p.subtitle);
		}
	}
	private class DescView extends ProgramItemView {
		public DescView(int width, int height) {
			super(width, height, "description", 15, Font.PLAIN);
		}
		private static final long serialVersionUID = 1L;
		@Override
		public void stateChanged(ChangeEvent e) {
			Program p = App.model().guide.channel().program();
			if (p!=null)
			set( p.desc);
		}
	}
	private class CreditsView extends ProgramItemView {
		public CreditsView(int width, int height) {
			super(width, height, "credits", 15, Font.PLAIN);
		}
		private static final long serialVersionUID = 1L;
		@Override
		public void stateChanged(ChangeEvent e) {
			Program p = App.model().guide.channel().program();
			if (p!=null)
			set( p.credits);
		}
	}
	
}