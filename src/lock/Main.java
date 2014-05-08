package lock;


public class Main {

    public static BlockingNotice notice = new BlockingNotice(5);
    
    public static void main(String[] args) throws InterruptedException {

        new Thread(new TakeThread()).start();
        
        
        Thread.sleep(20000);
        
        new Thread(new PutThread()).start();
        
        new Thread(new PutThread()).start();
        
        Thread.sleep(Integer.MAX_VALUE);
    }

}
