package org.scoutant.tvcenter.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.scoutant.mvc.EventWith;

public class KeyPressed  implements KeyListener{
	@Override
	public void keyTyped(KeyEvent ke) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		new EventWith<Integer>("keypressed", ke.getKeyCode()).dispatch();
	}
}
