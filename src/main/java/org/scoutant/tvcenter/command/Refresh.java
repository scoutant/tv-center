package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;

public class Refresh extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger( Refresh.class);
	@Override
	public void execute() {
		Program p = App.model().guide.channel().program();
		log.info("program : " + p);
		// TODO ok?
		App.app().programView.stateChanged(null);
	}

}