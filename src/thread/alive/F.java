package thread.alive;

public class F implements Runnable {

    private C c;
    
    public F(C c){
        this.c = c;
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new FExceptionHandler(c));
        t.start();
    }
    @Override
    public void run() {
        int i = 100;
        while(true){
            
            i--;
            if(i < 0){
                int j = 1 / 0;
            }
            
        }
    }
    
    private static class FExceptionHandler implements Thread.UncaughtExceptionHandler{
        private C c;
        public FExceptionHandler(C c){
            this.c = c;
        }
        
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("线程 name:id" + t.getName() + ":" + t.getId() + "出现异常，自行重启中...");
            e.printStackTrace();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            new F(this.c);
        }
        
    }

}
