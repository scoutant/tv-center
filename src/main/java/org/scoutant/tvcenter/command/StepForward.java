package org.scoutant.tvcenter.command;

import java.util.Date;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;
import org.scoutant.tvcenter.App;

public class StepForward extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger( StepForward.class); 		
	@Override
	public void execute() {
		// probably now++, but it's better to get sync with actual time to prevent adding small offsets... 
		int now = (int) (new Date().getTime()/1000/60);
		App.model().now = now;
		new Event("refresh").dispatch();
	}
}
