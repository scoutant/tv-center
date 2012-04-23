package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;

public class Refresh extends BaseCommand implements Command {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( Refresh.class);
	@Override
	public void execute() {
//		log.info("program : " + App.model().guide.channel().program());
		// TODO ok?
//		App.app().programView.stateChanged(null);
		App.app().stateChanged(null);
	}

}
