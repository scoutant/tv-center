package org.scoutant.tvcenter.command;

import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.CommandWith;
import org.scoutant.mvc.Event;

public class KeyPressed extends BaseCommand implements CommandWith<Integer> {
	private static final Logger log = Logger.getLogger(KeyPressed.class); 
	
	@Override
	public void execute(Integer k) {
		
//		if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)
		
		switch( k) {
		case KeyEvent.VK_Q: System.exit(0); break;
		case KeyEvent.VK_W: System.exit(0); break;

		case KeyEvent.VK_LEFT:	new Event("left")	.dispatch(); break;
		case KeyEvent.VK_RIGHT: new Event("right")	.dispatch(); break;
		case KeyEvent.VK_UP: 	new Event("up")		.dispatch(); break;
		case KeyEvent.VK_DOWN:	new Event("down")	.dispatch(); break;

		case KeyEvent.VK_ENTER:	new Event("start")	.dispatch(); break;
		case KeyEvent.VK_S: 	new Event("stop")	.dispatch(); break;
		
		case KeyEvent.VK_I: 	new Event("info")	.dispatch(); break;
		
		}
	}

}
