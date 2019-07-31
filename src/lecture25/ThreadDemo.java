package lecture25;

public class ThreadDemo {
	public static class ExampleThread implements Runnable {
		private int id;
		
		public ExampleThread(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			for (int i = 0; true; i++) {
				System.out.println("Thread " + id + " iteration " + i);
			}
		}
	}
	
	public static void runExample() {
		new Thread(new ExampleThread(1)).start();
		new Thread(new ExampleThread(2)).start();
		new Thread(new ExampleThread(3)).start();
	}
	
	public static class ExampleTask implements Runnable {
		int result = 0;
		int id;
		
		public ExampleTask(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			int result = 0;
			for (int i = 0; i < 10; i++) {
				System.out.println("task " + id + " running");
				result += i*i;
			}
			this.result = result;
		}
	}
	
	public static void forkJoin() throws InterruptedException {
		ExampleTask[] tasks   = new ExampleTask[5];
		Thread[]      threads = new Thread[5];
		
		// fork off threads to execute tasks
		for (int i = 0; i < 5; i++) {
			tasks[i] = new ExampleTask(i);
			threads[i] = new Thread(tasks[i]);
			threads[i].start();
		}
		
		// all threads started, wait for each to finish
		for (int i = 0; i < 5; i++) {
			threads[i].join();
			System.out.println("thread " + i + " done; result = " + tasks[i].result);
		}
	}
	
	public static class Race implements Runnable {
		static int counter = 0;
		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				increment();
			}
		}
		
		// only one thread in a synchronized block for a given object at a time
		// (or if it's static, for a given class at a time).
		public static synchronized void increment() {
			int tmp = counter;
			// really know for sure that tmp = old value of counter
			// so tmp + 1 = old value + 1
			counter = tmp + 1;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[5];
		for (int i = 0; i < 5; i++) {
			threads[i] = new Thread(new Race());
			threads[i].start();
		}
		
		for (int i = 0; i < 5; i++) {
			threads[i].join();
		}
		
		System.out.println(Race.counter);
	}
}
