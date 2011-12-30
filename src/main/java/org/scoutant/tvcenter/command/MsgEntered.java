package org.scoutant.tvcenter.command;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.CommandWith;

public class MsgEntered extends BaseCommand implements CommandWith<String> {
	
	@Override
	public void execute(String value) {
		System.out.println("Message: " + value);
	}

}
