package org.scoutant.tvcenter.view;

import java.awt.Color;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;

public class ProgramWidget extends JButton {
	private static final long serialVersionUID = 9140793430723109422L;
	public static final Logger log = Logger.getLogger(ProgramWidget.class);
	private Program program;
	public ProgramWidget(String label) {
		super(label);
		setFocusable(false);
	}
	public ProgramWidget(String label, int x,int y) {
		this(label);
		setBounds( x, y, 180, 30);
	}
	public ProgramWidget(Program p, int x,int y) {
		this( p.title, x, y);
		this.program = p;
	}
	
	@Override
	public void repaint() {
		super.repaint();
		if (program!=null && program.equals( App.model().guide.channel().program())) {
			setBackground( Color.CYAN);
		} else {
			setBackground( Color.white);
		}
	}
	
	public void colorize() {
		this.setEnabled(false);
	}
}
