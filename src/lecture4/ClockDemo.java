package lecture4;

import lecture3.TimeWithInvariants;

public class ClockDemo {
	public static void printTime(Time t) {
		System.out.println("hours:" + t.hour() + " minutes: "+t.minutes());		
	}
	
	
	public static void main(String[] args) {
		printTime(new ClockTime());
		printTime(new TimeWithInvariants(3,17));
		
		SecondsTimeImpl st = new SecondsTimeImpl(1,2,3);
		printTime(st);
	}
}
