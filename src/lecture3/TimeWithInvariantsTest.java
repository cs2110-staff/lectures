package lecture3;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


class TimeWithInvariantsTest {

	@Test
	void test() {
		TimeWithInvariants t = new TimeWithInvariants(3, 17);
		//t.setMinute(17);
		//check t.getMinute is 17
		assertEquals(t.minutes(), 17);
		
		//t.setHour(3);
		assertEquals(t.hour(), 3);
	}

}
