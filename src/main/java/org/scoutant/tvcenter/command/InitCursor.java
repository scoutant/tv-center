package org.scoutant.tvcenter.command;

import java.util.Date;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;
import org.scoutant.tvcenter.App;

public class InitCursor extends BaseCommand implements Command {

	@Override
	public void execute() {
		// TODO position cursor against current time...
		
		
		guide.init();
		new Event("refresh").dispatch();
	}

}
