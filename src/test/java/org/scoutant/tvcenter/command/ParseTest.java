package org.scoutant.tvcenter.command;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.scoutant.tvcenter.io.ProgramHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.helpers.DefaultHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/context.xml"})

public class ParseTest {

    @Autowired
    private ApplicationContext context;
    
//    @Resource()
//    private File file;
    
    @Test public void testParse() throws Exception {
    	assertTrue( true);
    	
    	Resource res = context.getResource("tv.xml");

    	File file = res.getFile();
    	assertTrue( file.exists());
    	
//    	Class<?> c = Class.forName("org.apache.xerces.parsers.SAXParser");
//    	XMLReader reader = (XMLReader)c.newInstance();
    	SAXParserFactory factory = SAXParserFactory.newInstance();
//    	factory.setValidating(false);
    	SAXParser saxParser = factory.newSAXParser();
    	
    	DefaultHandler handler = new ProgramHandler();

    	saxParser.parse( file, handler );
    	
    }
	
}
