package lecture3;

/** Represents a time during the day. */
public class Time {

	/** Return the hour that this represents, in the range [0..24) */
	public int hours() {
		throw new NotImplementedError();
	}
	
	/** Return the minute that this represents, in the range [0..60) */
	public int minutes() {
		throw new NotImplementedError();
	}
	
	/** Change this to the given hour.
	 * 
	 * @param hour the new hour, in [0..24)
	 */
	public void setHour(int hour) {
		throw new NotImplementedError();
	}
	
	/** Change the minute that this represents
	 * 
	 * @param minute the new minute, in [0..60)
	 */
	public void setMinutes(int minute) {
		throw new NotImplementedError();
	}
	
	/** Return a string representation of this, in the form HH:MM AM/PM */
	public String toString() {
		throw new NotImplementedError();
	}
}
