package org.scoutant.tvcenter.view;

import java.awt.Color;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;

public class ProgramWidget extends JButton {
	private static final long serialVersionUID = 9140793430723109422L;
	public static final Logger log = Logger.getLogger(ProgramWidget.class);
	public static final int MINUTE = 8;
	private Program program;
	public ProgramWidget(Program p, int x,int y) {
		super(p.title);
		this.program = p;
		int width = (int) ((program.stop-program.start)*MINUTE);
		setBounds(x, y, width, 30);
	}

	public ProgramWidget(Program p) {
		super(p.title);
		this.program = p;
		int width = (int) ((program.stop-program.start)*MINUTE);
		setBounds( (p.start-App.model().now)*MINUTE, 10, width, 30);
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
