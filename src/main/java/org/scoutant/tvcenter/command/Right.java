package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;

public class Right extends BaseCommand implements Command{

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(Right.class);
	@Override
	public void execute() {
		guide.channel().right();
		// TODO : watch bounds!!
		new Event("refresh").dispatch();
//		log.debug( "program : " + guide.channel().program());
	}

}
