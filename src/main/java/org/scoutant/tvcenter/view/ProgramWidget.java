package org.scoutant.tvcenter.view;

import javax.swing.JButton;

import org.scoutant.tvcenter.listener.KeyPressed;
import org.scoutant.tvcenter.model.Program;

public class ProgramWidget extends JButton {
	private static final long serialVersionUID = 9140793430723109422L;
	public ProgramWidget(String label) {
		super(label);
		this.addKeyListener( new KeyPressed());
	}
	public ProgramWidget(String label, int x,int y) {
		this(label);
		setBounds( x, y, 180, 30);
	}
	public ProgramWidget(Program p, int x,int y) {
		this( p.title, x, y);
	}

}
