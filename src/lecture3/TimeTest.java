package lecture3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class TimeTest {

	@Test
	void test() {
		Time t = new Time();
		t.setMinute(17);
		//check t.getMinute is 17
		assertEquals(t.minute(), 17);
		
		t.setHour(3);
		assertEquals(t.hour(), 3);
	}

}
