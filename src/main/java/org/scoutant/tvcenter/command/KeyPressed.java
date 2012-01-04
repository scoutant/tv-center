package org.scoutant.tvcenter.command;

import java.awt.event.KeyEvent;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.CommandWith;

public class KeyPressed extends BaseCommand implements CommandWith<Integer> {
	
	@Override
	public void execute(Integer k) {
		switch( k) {
		case KeyEvent.VK_Q : System.exit(0); break;
		case KeyEvent.VK_W : System.exit(0); break;
		}
	}

}
