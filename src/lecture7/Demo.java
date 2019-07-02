package lecture7;

public class Demo {

	int value;
	
	Demo() {
		this.value = 42;
	}
	
	@Override
	public String toString() {
		return "demo with value " + this.value;
	}
	
	@Override
	public boolean equals(Object other) {
		return true;
	}
	
	public boolean equals(Demo d) {
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object o1 = new Demo();
		Demo   o2 = new Demo();
		
		System.out.println("value of o1 is :" + o1);
		
		System.out.println(o1.equals(o2));
		System.out.println(o1 == o2);
		
		
	}

}
