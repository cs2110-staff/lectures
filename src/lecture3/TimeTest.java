package lecture3;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


class TimeTest {

	@Test
	void test() {
		TimeDemo t = new TimeDemo(3, 17);
		//t.setMinute(17);
		//check t.getMinute is 17
		assertEquals(t.minute(), 17);
		
		//t.setHour(3);
		assertEquals(t.hour(), 3);
	}

}
