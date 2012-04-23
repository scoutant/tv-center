package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;

public class Left extends BaseCommand implements Command{

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(Left.class);
	@Override
	public void execute() {
		guide.channel().left();
		// TODO : watch bounds!!
		new Event("refresh").dispatch();
	}

}
