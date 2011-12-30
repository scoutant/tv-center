package org.scoutant.tvcenter.command;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;

public class Start extends BaseCommand implements Command {

	@Override
	public void execute() {
		System.out.println("Start");
	}

}
