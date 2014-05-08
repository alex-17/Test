package semaphore;

public class Test {

	public static void main(String[] args) throws InterruptedException {

		Pool pool = new Pool();
		
		Handle h1 = new Handle(pool);
		Handle h2 = new Handle(pool);
		Handle h3 = new Handle(pool);
		Handle h4 = new Handle(pool);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		
		System.out.println("-------");
		
		Thread.sleep(4000);
		
		pool.putItem(null);
		pool.putItem(null);
		
		Thread.sleep(Integer.MAX_VALUE);
	}

}
