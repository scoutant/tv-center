package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;

public class Stop extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger(Stop.class);
	@Override
	public void execute() {
		log.info("Stop");
		Process process = App.model().process;
		if (process==null) return;
		process.destroy();
		App.model().recording = false;
		App.model().programRecording = null;
	}

}
