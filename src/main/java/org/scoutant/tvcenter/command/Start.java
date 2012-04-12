package org.scoutant.tvcenter.command;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;

public class Start extends BaseCommand implements Command {
	private static final Logger log = Logger.getLogger(Start.class);
	@Override
	public void execute() {
//		ProcessBuilder pb = new ProcessBuilder("nautilus", "/tmp");
//		String url = "/tmp/easyphone-en.mpg";
		String url = App.model().guide.channel().url;
		ProcessBuilder pb = new ProcessBuilder("vlc", url);
		log.info("start : " + url);
		try {
			App.model().process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
