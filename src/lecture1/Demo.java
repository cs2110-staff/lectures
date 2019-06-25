package lecture1;

public class Demo {

	/** Always returns 0.  This is here as an example of a method;
	 *  for now, ignore the "public".  The "int" is the return type of the method.
	 *  "f" is the name of the method, and "x" is an argument to the method, and must be
	 *  an "int".
	 *  
	 *  @param x an integer, which is ignored.
	 */
	public int f(int x) {
		return 0;
	}
	
	/**
	 * Demonstrates some basic features of Java.
	 */
	public static void main(String[] args) {
		// comment
		/* 
		everything here is a comment.
		 */

		// use Javadoc comments (starting with /**) for information that _users_ will need
		// use inline comments  (starting with //)  for information that _implementors_ will need
		
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
		
		// Integers wrap around:
		System.out.println(Integer.MAX_VALUE + 1);
		
		// bools
		boolean z = true;
		z = false;
		System.out.println(true && false); // true AND false
		System.out.println(true || false); // true OR false
		
		// doubles
		double w = 3.14;
		
		// a char (for character) is a single symbol (letter, number, emoji, etc)
		char t = 'a';
		char t2 = '1';
		// char t3 = 'hello';
		
		// types other than primitive types are Object types.  Operations on Object types
		// are determined by their class.
		String x_string = "hello";
		Demo d = new Demo();
	    int result_of_f = d.f(3);
	}

}
