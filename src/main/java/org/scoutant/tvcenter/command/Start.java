package org.scoutant.tvcenter.command;

import java.io.IOException;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.Command;
import org.scoutant.tvcenter.App;

public class Start extends BaseCommand implements Command {

	@Override
	public void execute() {
//		ProcessBuilder pb = new ProcessBuilder("nautilus", "/tmp");
		ProcessBuilder pb = new ProcessBuilder("vlc", "/tmp/easyphone-en.mpg");
		System.out.println("Start");
		try {
			App.model().process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
