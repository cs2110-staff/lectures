package lecture2;

public class HeapDemo {

	int x = 0;
	String y = "hello";
	
	public static void main(String[] args) {
		HeapDemo hd = new HeapDemo();
		// <object>.<thing> means go find the <thing> in <object> 
		hd.x = 1;
		
		HeapDemo hd2 = new HeapDemo();
		hd2.x = 2;
		
		HeapDemo hd3 = hd2;
		hd3.x = 3;
	}

}
