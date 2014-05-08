package lock;


public class PutThread implements Runnable {

    @Override
    public void run() {
        int c = Integer.MAX_VALUE;
        while( (c-- ) > 0){
            
            try {
                Main.notice.notice();
                System.out.println(Thread.currentThread().getId() + ")put");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}
