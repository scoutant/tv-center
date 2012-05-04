package org.scoutant.tvcenter.command;

import java.util.Date;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;

public class StepForward extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger( StepForward.class); 		
	@Override
	public void execute() {
		// probably now++, but it's better to get sync with actual time to prevent adding small offsets... 
		int now = (int) (new Date().getTime()/1000/60);
		App.model().now = now;
		new Event("refresh").dispatch();
		/*
		// shall we start or stop recording any program?
		for (var i:int=0; i<guide.list.length; i++ ) {
			var channel:Channel = guide.getChannelByIndex(i);
			var program:Program = channel.getAtTime( now);
			if (program == null) return;
			if (program.record && program.a == now) {
				new ProgramEvent("recordProgram", program).dispatch();
			}
			
			// Give some 10 min marging, for many programs happen to extend plannification!
			// if (program.record && (program.z-1) == now) {
			if (program.record && (program.z) == now ) {
				new ProgramEvent("endOfRecording", program).dispatch();
			}
		}*/
//		guide = App.model().guide;
		for (Channel c : guide.channels) {
		}
		
	}
}
