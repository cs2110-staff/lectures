package lecture7;

public class CollectionsDemo {
	public static interface Collection<E> {
		void add(E o);
		E get();
		E get(int pos);
		int size();
	}
	
	public static void printAll(Collection<?> c) {
		for (int i = 0; i < c.size(); i++)
			System.out.println(c.get(i));
		// c.add(new Object());
	}
	
	public static void makeAllCute(Collection<? extends Animal> c) {
		for (int i = 0; i < c.size(); i++)
			c.get(i).beCute();
		
	}
	
	static interface Animal {
		void beCute();
	}
	
	static interface Dog extends Animal {
		void bark();
	}
	

	static class DogImpl implements Dog {

		@Override
		public void beCute() {
			System.out.println("rolling over now.");
		}

		@Override
		public void bark() {
			System.out.print("woof");
		}
		
	}
	
//	public interface DogCollection {
//		void add(Dog d);
//		Dog get();
//		int size();
//	}
//	
	public static void main(String args[]) {
		// c only contains Dogs
		Collection<Dog> c = null;
		
		c.add(new DogImpl());
		
		Dog d = c.get();
		d.bark();
		
		printAll(c);
		makeAllCute(c);
		
		// error: c.add has type add(CollectionsDemo), not add(String) c.add("Hello");
	}
}
