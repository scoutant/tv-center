package org.scoutant.tvcenter.utils;

import static org.junit.Assert.*;
import org.junit.Test;

public class DateUtilsTest {
	
	 // start="20100110175500 +0100"
	@Test public void testParse() throws Exception {
		long m1 = DateUtils.parse( "20100110175500 +0100");
		assertNotNull(m1);
		long m2 = DateUtils.parse( "20100111175500 +0100");
		assertEquals(m1 + 24*60, m2);
	}

}
