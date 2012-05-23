package org.scoutant.tvcenter.command;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.mvc.Event;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;
import org.scoutant.tvcenter.model.Program;

public class RecordProgram extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger( RecordProgram.class);
	@Override
	public void execute() {
		new Event("stop").dispatch();
		Channel c = App.model().guide.channel();
		Program p = c.program();
		String url = c.url;
		
		log.info("recording channel with name : " + c.name);
		log.info("recording program : " + p.desc);
		log.info("recording program : " + p.title);
//		Process.instance.vlc( ip+" --sout file/ps:/home/coutant/tele/"+channel.normalizedName+"-"+Model.instance.now+".mpeg");
		// TODO change name with program data...
		ProcessBuilder pb = new ProcessBuilder("vlc", url, "--sout",  "file/ps:/home/coutant/tele/" + c.name + "-" + App.model().now +".mpeg");
		try {
			App.model().process = pb.start();
			App.model().recording = true;
			App.model().programRecording = p;
			p.record = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
