package lecture2;

public class StaticDemo {

	static int x = 0;
	int y = 2;
	
	public void f() {
		
	}
	
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
		
		StaticDemo.x = 4;
		StaticDemo.g();
		
		StaticDemo sd2 = new StaticDemo();
		sd2.y = 5;
		sd2.x = 6;
		
		//sd.x?
	}

}
