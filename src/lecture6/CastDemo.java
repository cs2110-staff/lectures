package lecture6;

public class CastDemo {
	public static interface Animal {
		/** prints out something cute. */
		void beCute();
	}
	
	public static interface Mammal extends Animal {
		void giveMilk();
	}
	
	public static interface Dog extends Mammal {
		void bark();
		
		/** do a cute trick, and print out the result */
		void beCute();
	}
	
	public static class Lizard implements Animal {

		@Override
		public void beCute() {
			System.out.println("Liz.");
		}
		
	}
	
	public static class DogImpl implements Dog {
		@Override
		public void giveMilk() {
			System.out.println("Here is some dog milk");
		}

		@Override
		public void beCute() {
			System.out.println("I'm shaking hands");
		}

		@Override
		public void bark() {
			System.out.println("bork bork bork");
		}
		
	}
	
	public static class CowImpl implements Mammal {

		@Override
		public void beCute() {
			System.out.println("moo");
		}

		@Override
		public void giveMilk() {
			System.out.println("here is some cow milk");
		}
		
	}
	
	public static interface AnimalLover {
		void love(Animal a);
	}
	
	public static interface DogLover {
		public void love(Dog d);
	}
	
	// DogLover shouldn't implement AnimalLover
	public static class DogLoverImpl implements DogLover /* implements AnimalLover */ {

		public void love(Dog d) {
			// terrible implementation!
			// will crash if handed a Lizard, for example:
			// ((Dog) d).bark();
			
			// also bad:
			//if (d instanceof Dog)
			//	((Dog) d).bark();
			//else
			//	System.out.println("I'm pretending you're a dog!");
			d.bark();
		}
		
	}
	
	// question: is every AnimalLover a dog lover?
	// i.e. should AnimalLoverImpl implement DogLover?
	public static class AnimalLoverImpl implements AnimalLover , DogLover {

		// I did this Demo at the end of lecture.  It turns out that though I
		// was right about the concept, Java has an additional restriction in
		// place that doesn't let us use love(Animal) to implement love(Dog)
		// we can fix this by adding an overloaded implementation (below)
		@Override
		public void love(Animal a) {
			a.beCute();
		}
		
		public void love(Dog d) {
			Animal a = d;
			love(a); 
		}
	}
	
	public static void main(String[] args) {
		Animal a;
		a = new DogImpl(); // OK, every DogImpl is a Animal
		a.beCute();
		
		a = new Lizard();
		
		// Mammal m = a; // error!
		System.out.println(a instanceof Mammal);
		Mammal m = (Mammal) a; // compiles, but dangerous
	}
	
	public static void demoPrimitives() {
		double d = Double.MAX_VALUE;
		System.out.println("double " + d);
		
		int i = (int) d;
		System.out.println("int " + i);
		
		char c = (char) i;
		System.out.println("char " + c);
		System.out.println("int again: " + (int) c);
	}
}
