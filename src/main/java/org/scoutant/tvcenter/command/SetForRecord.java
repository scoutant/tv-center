package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;

public class SetForRecord extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger( SetForRecord.class); 		
	@Override
	public void execute() {
		// TODO get current program and toggle 'record property'
		Program p = App.model().guide.channel().program();
		p.record = !p.record;
		log.debug("will be recorded : " + p);
		// TODO refresh guide view to get a record icon visible...
		// TODO start direct recording if 'now' happen to be with the program...
		
	}
}
