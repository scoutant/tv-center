package org.scoutant.tvcenter.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.log4j.Logger;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;

public class ProgramWidget extends JButton {
	private static final long serialVersionUID = 9140793430723109422L;
	public static final Logger log = Logger.getLogger(ProgramWidget.class);
	public static final int MINUTE = 8;
	public static final int PADDING = 3;
	private Program program;
	public ProgramWidget(Program p, int x,int y) {
		super(p.title);
		this.program = p;
		int width = (int) ((program.stop-program.start)*MINUTE);
		setBounds(x, y, width, 30);
		Border roundedBorder = new LineBorder(Color.black, 2, true);
		setBorder(roundedBorder);
	}

	public ProgramWidget(Program p) {
		super(p.title);
		this.program = p;
		int width = (int) ((program.stop-program.start)*MINUTE -2*PADDING);
		setBounds( (p.start-App.model().now)*MINUTE + PADDING, 10, width, 30);
//		Border roundedBorder = new LineBorder(Color.black, 1, true);
//		setBorder(roundedBorder);
//		setBorder( new SoftBevelBorder(BevelBorder.LOWERED));
		// TODO no effect with setBounds(.) approach?
		putClientProperty("JComponent.sizeVariant", "large");
	}

	// TODO add gradient...
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
