package thread;

public class TCPServer implements Runnable{

    private Info info = null;
    
    public TCPServer(Info info){
        this.info = info;
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new TcpServerExceptionHandler(info));
        t.start();
    }
    
    public void run() {
        System.out.println(info.toString());
        int i = 10;
        
        while(i > 0){
            i--;
            System.out.println(i);
        }
        
        int j = 1 / 0;
        
    }
    
    private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler{

        private Info info = null;
        
        public TcpServerExceptionHandler(Info info){
            this.info = info;
        }
        
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("线程 name:id" + t.getName() + ":" + t.getId() + "出现异常，自行重启中...");
            e.printStackTrace();
            new TCPServer(this.info);
        }
        
    }

}
