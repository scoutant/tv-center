package org.scoutant.tvcenter.command;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.scoutant.mvc.BaseCommand;
import org.scoutant.mvc.CommandWith;
import org.scoutant.tvcenter.io.GuideHandler;
import org.xml.sax.helpers.DefaultHandler;

public class ParseGuide extends BaseCommand implements CommandWith<InputStream> {
	private SAXParserFactory factory = SAXParserFactory.newInstance();
	private DefaultHandler handler = new GuideHandler();
	@Override
	public void execute(InputStream is) throws Exception {
		SAXParser saxParser = factory.newSAXParser();
    	factory.setValidating(false);
    	saxParser.parse( is, handler );
	}

}
