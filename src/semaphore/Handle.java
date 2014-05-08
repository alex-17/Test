package semaphore;

public class Handle extends Thread {

	private Pool pool;
	
	public Handle(Pool pool){
		this.pool = pool;
	}
	
	@Override
	public void run() {
		
		while(true){
			
			try {
				System.out.println(Thread.currentThread().getId()  + ",item=" + pool.getItem());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
}
