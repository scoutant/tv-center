package org.scoutant.mvc;

public interface CommandWith<T> {
	String getEvent();
	void execute(T t) throws Exception;
}
