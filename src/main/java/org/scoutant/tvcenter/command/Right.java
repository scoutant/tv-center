package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;

public class Right extends BaseCommand implements Command{

	private static final Logger log = Logger.getLogger(Right.class);
	@Override
	public void execute() {
		guide.channel().right();
		// TODO : watch bounds!!
//		new Event("refresh").dispatch();
		log.debug( "program : " + guide.channel().program());
	}

}
