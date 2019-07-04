package lecture8;

public class GenericsDemo {
	// Simple class hierarchy: /////////////////////////////////////////////////

	interface Animal { String beCute(); }
	
	interface Bird   extends Animal { String sing(); }
	interface Mammal extends Animal { String giveMilk(); }

	interface Dog    extends Mammal { String bark(); }
	interface Cat    extends Mammal { String purr(); }

	class DogImpl implements Dog {
		public String giveMilk() { return "Dog Milk?  Okay..."; }
		public String beCute()   { return "Roll over"; }
		public String bark()     { return "Woof"; }		
	}
	
	class BirdImpl implements Bird {
		public String beCute() { return "Fly"; }
		public String sing()   { return "Tweet"; }
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	interface Collection<E> {
		void add(E value);
		E get();
	}

	void milkAll(Collection<? extends Mammal> c) {
		// always safe: we don't know what ? is, but whatever it is, it is a
		// Mammal, so we can call getMilk() on it.
		c.get().giveMilk();
		
		// not possible: c.add(new BirdImpl())
		// because at runtime, the ? could actually be Dog or Mammal or Cat
	}
	
	void addDog(Collection<? super Dog> c) {
		// always safe: we don't know what ? is, but whatever it is, a DogImpl
		// is one (since a DogImpl is a Dog and a Dog is a ?)
		c.add(new DogImpl());
		
		// not possible:
		//   c.get().bark()
		// because at runtime, the ? could actually be Object, Animal, or Mammal
		
		// however we can do:
		c.get().toString();
		// because  no matter what the ? is, it must be a subtype of Object
		// (only because everything is a subtype of Object).
	}

	//////////////////////////////////////////////////////////////////////////////
	
	void showSubtypes () {
		Collection<Animal> ca = null; // we're focused on static types,
		Collection<Bird>   cb = null; // so we don't actually implement or
		Collection<Mammal> cm = null; // create actual collections.
		Collection<Dog>    cd = null; //
		Collection<Cat>    cc = null; // you could add a simple implementation
		Collection<Object> co = null; // if you like.
		
		// the method types above work as desired:

		milkAll(cm); milkAll(cd); milkAll(cc);     // OK
		// milkAll(ca); milkAll(cb); milkAll(co);  // not OK

		addDog(ca); addDog(cd); addDog(co); addDog(cm); // OK
		// addDog(cb); addDog(cc);                      // not OK

		// here's a bunch of collection types to play with:
		// cqea: Collection of Unknown type that Extends Animal
		Collection<? extends Animal> cuea = null;
		Collection<? extends Bird>   cueb = null;
		Collection<? extends Mammal> cuem = null;
		Collection<? extends Dog>    cued = null;
		Collection<? extends Cat>    cuec = null;
		Collection<?>                cueo = null;
		// Note: <?> is shorthand for <? extends Object> (since everything extends Object)
		
		// cusa: Collection of Unknown that is a Supertype of Animal
		Collection<? super Animal> cusa = null;
		Collection<? super Bird>   cusb = null;
		Collection<? super Mammal> cusm = null;
		Collection<? super Dog>    cusd = null;
		Collection<? super Cat>    cusc = null;
		// Question: what's another name for <? super Object>?

		// Here you can experiment with the is-a relationship.  If A is-a B then
    // b = a; will work

		cueo = cc; // Collection<Cat> is-a Collection<?>
		// cc = cueo; // Collection<?> is not a Collection<Cat>
	}
}
