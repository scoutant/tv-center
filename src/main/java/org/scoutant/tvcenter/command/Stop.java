package org.scoutant.tvcenter.command;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;

public class Stop extends BaseCommand implements Command {
	@Override
	public void execute() {
		System.out.println(" Stop Command");
		Process process = App.model().process;
		if (process==null) return;
		process.destroy();
	}

}
