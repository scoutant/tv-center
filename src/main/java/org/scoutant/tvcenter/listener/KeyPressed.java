package org.scoutant.tvcenter.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.log4j.Logger;
import org.scoutant.mvc.EventWith;

public class KeyPressed  implements KeyListener {
	private static final Logger log = Logger.getLogger( KeyPressed.class);
	@Override
	public void keyTyped(KeyEvent ke) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		log.debug( "modifier : " + ke.getModifiers());
//		if (ke.getModifiers() == KeyEvent.CTRL_DOWN_MASK) {
		if (ke.getModifiers() == 2) {
			new EventWith<Integer>("ctrlKeypressed", ke.getKeyCode()).dispatch();
		} else {
			new EventWith<Integer>("keypressed", ke.getKeyCode()).dispatch();
		}
	}
}
