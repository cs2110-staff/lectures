package lecture4;

public interface Time {
	/** returns the hour represented by this, in range [0..24)
	 */
	int hour();
	
	/** returns the minute within the hour, in range [0..60) */
	int minutes();
}
