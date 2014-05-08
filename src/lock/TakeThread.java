package lock;

public class TakeThread implements Runnable {

    @Override
    public void run() {

        while(true){
            
            try {
                System.out.println("take:" + Main.notice.waitNotice());
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        
    }

}
