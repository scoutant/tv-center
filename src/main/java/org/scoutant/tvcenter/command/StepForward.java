package org.scoutant.tvcenter.command;

import java.util.Date;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;
import org.scoutant.tvcenter.model.Program;

public class StepForward extends BaseCommand implements Command {
	@SuppressWarnings("unused")
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
		log.debug("step forward : " + now);
		for (int i = 0; i<guide.channels.size(); i++) {
			Channel c = guide.channel(i);
			Program p = c.program( now);
			log.debug( "p now for channel : "  + p.title + ", on " + c.name + "stop : " + p.stop + ", eqals? " + (App.model().programRecording==p));
			
			if ( App.model().recording && (p != null) &&  App.model().programRecording==p && ( now == p.stop-1 ) ) {
				guide.index = i;
				new Event("stop").dispatch();
				return;
			}
			if ( (p != null) &&  !App.model().recording && p.record && (p.start <= now) ) {
				guide.index = i;
				new Event("recordProgram").dispatch();
				return;
			}
		}
		
	}
}
