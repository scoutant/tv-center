package org.scoutant.tvcenter.view;

import java.awt.Color;

import javax.swing.ImageIcon;
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
		setBounds(x, y, width, 48);
		Border roundedBorder = new LineBorder(Color.black, 2, true);
		setBorder(roundedBorder);
		// TODO how to add an icon?
		setIcon(new ImageIcon("/home/coutant/2012/tv-center/src/main/resources/ksame_1433_24.png"));
	}

	public ProgramWidget(Program p) {
		super(p.title);
		this.program = p;
		int width = (int) ((program.stop-program.start)*MINUTE -2*PADDING);
		setBounds( (p.start-App.model().vpTime)*MINUTE + PADDING, 10, width, GuideView.CHANNEL_HEIGHT-10);
		
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
		if (program==null) return; 
		if (program.equals( App.model().guide.channel().program())) {
			setBackground( Color.CYAN);
		} else {
			setBackground( Color.white);
		}
		setForeground( program.record ? Color.RED : Color.BLACK);
	}
	
	public void colorize() {
		this.setEnabled(false);
	}
}