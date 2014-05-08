package thread.alive;

public class C implements Runnable {

    
    public C(){
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new CExceptionHandler());
        t.start();
    }
    @Override
    public void run() {
        while(true){
            
            System.out.println("--C--");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static class CExceptionHandler implements Thread.UncaughtExceptionHandler{
        
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("线程 name:id" + t.getName() + ":" + t.getId() + "出现异常，自行重启中...");
            e.printStackTrace();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            new C();
        }
        
    }

}
