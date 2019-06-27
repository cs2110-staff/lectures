package lecture3;

public class TimeWithInvariants implements lecture4.Time {
	
	// Note: in a real program, I probably wouldn't use this representation.
	
	// class invariant: in range [0..60*24)
	// class invariant: minutesSinceMidnight == 60*hour + minute;
	private int minutesSinceMidnight;
	
	// class invariant: in range [0..60)
	private int minute;
	
	// class invariant: in range [0..24)
	private int hour;
	
	/** intialize this to represent the hour h and minute m.
	 * @param m the minutes, in [0..60)
	 * @param h the hour, in [0..24)
	 */
	public TimeWithInvariants(int m, int h) {
		assert m >= 0 && m < 60;
		assert h >= 0 && h < 24;
		this.hour = h;
		this.minute = m;
		this.minutesSinceMidnight = h*60 + m;
	}
	
	/** return the minute within the hour, in [0..60) */
	public int minutes() {
		return minutesSinceMidnight % 60;
	}
	
	/** return the hour, in [0..24) */
	public int hour() {
		// same as "return hour"
		return this.minutesSinceMidnight / 60;
	}
	
	/** update this object changing the minute to m
	 *  
	 * @param m the new minutes, in range [0..60) // precondition
	 */
	public void setMinute(int minute) {
		assert minute >= 0 && minute < 60;
		this.minute = minute;
		this.minutesSinceMidnight = 60*this.hour + minute;
	}
	
	/** update this object, changing the hour to h
	 * 
	 * @param h the new hour, in range [0..24)
	 */
	public void setHour(int h) {
		assert h >= 0 && h < 24;
		this.hour = h;
		this.minutesSinceMidnight = this.minutes() + 60*h;
	}
	
}
