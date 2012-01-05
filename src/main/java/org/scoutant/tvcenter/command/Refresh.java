package org.scoutant.tvcenter.command;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Program;
import org.scoutant.tvcenter.view.ChannelView;
import org.scoutant.tvcenter.view.ProgramWidget;

public class Refresh extends BaseCommand implements Command {

	private static final Logger log = Logger.getLogger(Refresh.class);

	@Override
	public void execute() {
		Program p = guide.channel().program();
		log.debug( "program : " + p);
		
		// méthode bourinne de parcours:
//		for (ChannelView c: App.app().guide.views) {
//			for (ProgramWidget pw : c.views) {
//				pw.repaint();
//				
//			}
//		}
		
	}

}
