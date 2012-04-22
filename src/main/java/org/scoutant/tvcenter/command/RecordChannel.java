package org.scoutant.tvcenter.command;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;
import org.scoutant.tvcenter.model.Channel;

public class RecordChannel extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger( RecordChannel.class);
	@Override
	public void execute() {
		Channel c = App.model().guide.channel();
		String url = c.url;
//		Process.instance.vlc( ip+" --sout file/ps:/home/coutant/tele/"+channel.normalizedName+"-"+Model.instance.now+".mpeg");		
		ProcessBuilder pb = new ProcessBuilder("vlc", url, "--sout",  "file/ps:/home/coutant/tele/testing.mpeg");
		log.info("recording channel : " + c.id);
		try {
			App.model().process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
