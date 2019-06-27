package lecture4;

import java.time.LocalTime;

import lecture3.NotImplementedError;

public class ClockTime implements Time {

	@Override
	public int hour() {
		return LocalTime.now().getHour();
	}

	@Override
	public int minutes() {
		return LocalTime.now().getMinute();
	}

}
