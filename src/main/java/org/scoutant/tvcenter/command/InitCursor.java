package org.scoutant.tvcenter.command;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Guide;

public class InitCursor extends BaseCommand implements Command {

	@Override
	public void execute() {
		// TODO position cursor against current time...
		guide.init();
	}

}
