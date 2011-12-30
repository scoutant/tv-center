package org.scoutant.mvc;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @author coutant
 * So as to be used directly like so :
 * button.addActionLister( new ActionDispatch( "stop") );
 */
public class MouseDispath extends Event implements MouseListener  {

	public MouseDispath(String name) {
		super(name);
	}

	// TODO différence entre clicked et pressed?
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.dispatch();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	
}
