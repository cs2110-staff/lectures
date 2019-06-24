package lectures;

public class Demo {

	public int f(int x) {
		return 0;
	}
	
	/**
	 * Demonstrates some basic features of Java.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// comment
		/* 
		everything here is a comment.
		 */
		System.out.println("hello world");
		System.out.println(3 + 4);
		System.out.println(true);
		
		// Java is _statically_ typed
		// Python, matlab are dynamically typed.
		
		// static means determined when you WRITE the program
		//   (compile-time)
		//
		int x;
		x = 7;
		int y = x / 2;
		
		// primitive types
		// ints
		System.out.println("Max integer:" + Integer.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE + 1);
		
		boolean z = true;
		z = false;
		System.out.println(true && false); // true AND false
		System.out.println(true || false); // true OR false
		
		double w = 3.14;
		
		char t = 'a';
		char t2 = '1';
		// char t3 = 'hello';
		
		String x_string = "hello";
		Demo d = new Demo();
	    int result_of_f = d.f(3);
	    
	    
	}

}
