package org.scoutant.tvcenter.view;

import java.awt.Graphics;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Program;

public class ProgramWidget extends JButton {
	private static final long serialVersionUID = 9140793430723109422L;
	public static final Logger log = Logger.getLogger(ProgramWidget.class);
	public ProgramWidget(String label) {
		super(label);
//		addKeyListener( new KeyPressed());
		setFocusable(false);
	}
	public ProgramWidget(String label, int x,int y) {
		this(label);
		setBounds( x, y, 180, 30);
	}
	public ProgramWidget(Program p, int x,int y) {
		this( p.title, x, y);
	}
	
	@Override
	public void revalidate() {
		super.revalidate();
		log.debug("revalidating...");
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
		log.debug("updating...");
	}
	@Override
	public void repaint() {
		super.repaint();
		log.debug("---------------------------repainting...---------------------------");
	}
	
	
	public void colorize() {
		this.setEnabled(false);
	}
}
