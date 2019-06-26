package lecture3;

public class Time {
	
	int minute;
	int hour;
	
	/** return the minute within the hour, in [0..60) */
	public int minute() {
		return minute;
	}
	
	/** return the hour, in [0..24) */
	public int hour() {
		// same as "return hour"
		return this.hour;
	}
	
	/** update this object changing the minute to m
	 *  
	 * @param m the new minutes, in range [0..60) // precondition
	 */
	public void setMinute(int m) {
		this.minute = m;
	}
	
	/** update this object, changing the hour to h
	 * 
	 * @param h the new hour, in range [0..24)
	 */
	public void setHour(int h) {
		this.hour = h;
	}
}
