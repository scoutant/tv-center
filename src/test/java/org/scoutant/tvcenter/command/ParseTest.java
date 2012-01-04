package org.scoutant.tvcenter.command;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.scoutant.tvcenter.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/context.xml"})

public class ParseTest {

    @Autowired
    private ApplicationContext context;
    
    @Test public void testParse() throws Exception {
    	assertTrue( true);
    	
    	Resource res = context.getResource("tv.xml");
    	assertTrue( res.exists());
    	new ParseGuide().execute( res.getInputStream());
    	assertTrue( App.model().guide.channels.size() > 10);
    	assertTrue( App.model().guide.channel(0).programs.size()> 50);
    }
	
}
