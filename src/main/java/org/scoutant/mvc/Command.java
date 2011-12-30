package org.scoutant.mvc;

public interface Command {
	String getEvent();
	void execute();
}
