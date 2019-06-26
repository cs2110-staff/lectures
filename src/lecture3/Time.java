package lecture3;

public class Time {
	
	/** return the minute within the hour, in [0..60) */
	public int minute() {
		throw new NotImplementedError();
	}
	
	/** return the hour, in [0..24) */
	public int hour() {
		throw new NotImplementedError();
	}
	
	/** update this object changing the minute to m
	 *  
	 * @param m the new minutes, in range [0..60) // precondition
	 */
	public void setMinute(int m) {
		throw new NotImplementedError();
	}
	
	/** update this object, changing the hour to h
	 * 
	 * @param h the new hour, in range [0..24)
	 */
	public void setHour(int h) {
		throw new NotImplementedError();
	}
}
