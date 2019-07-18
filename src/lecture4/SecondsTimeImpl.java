package lecture4;

import common.NotImplementedError;

public class SecondsTimeImpl extends lecture3.TimeWithInvariants implements SecondsTime {

	/** initialize this to hold s seconds, m minutes, h hours. */
	public SecondsTimeImpl(int s, int m, int h) {
		super(m,h);
		throw new NotImplementedError();
	}

	/** update seconds of this time
	 * 
	 * @param s the new seconds, in range [0..60)
	 */
	public void setSeconds(int s) {
		throw new NotImplementedError();
	}
	
	/** return seconds of this time, in range [0..60) */
	public int seconds() {
		throw new NotImplementedError();
	}
}
