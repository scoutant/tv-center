package org.scoutant.tvcenter.command;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;

public class InitCursor extends BaseCommand implements Command {

	@Override
	public void execute() {
		// TODO position cursor against current time...
		guide.init();
		new Event("refresh").dispatch();
	}

}
