package lecture2;

/** Demonstrate the difference between static variables and fields */
public class StaticDemo {

	// x is associated with the class StaticDemo; there is only one x
	static int x = 0;
	
	// y is a field; there is one y inside every StaticDemo object
	int y = 2;
	
	// f is like a field: there is (conceptually) one f inside every StaticDemo object
	public void f() {
		
	}
	
	// g is like a static variable: there is only one function g, it is associated with the class
	public static void g() {
		
	}
	
	public static void main(String[] args) {
		StaticDemo.x = 1;
		StaticDemo.g();
		
		// error because y doesn't exist: StaticDemo.y = 3;
		// error because f doesn't exist: StaticDemo.f();
		
		StaticDemo sd = new StaticDemo();
		sd.y = 3;
		sd.f();
		
		// these are technically allowed, but shouldn't be used
		// because x and g are not part of sd, they are part of StaticDemo
		// Notice that eclipse gives a warning.
		sd.x = 4;
		sd.g();
		
		StaticDemo sd2 = new StaticDemo();
		sd2.y = 5;
		sd2.x = 6;
		
		//sd.x?
	}
}
