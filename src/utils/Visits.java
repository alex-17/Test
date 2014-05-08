package utils;


public class Visits {

    private long time = 0;
    
    private int removeCount = 0;
    
    private long IGNORE_TIME = 10 * 1000L;
    
    private int REMOVE_COUNT = 60;
    
    private boolean ignore(){
        
        if(time <= 0){
            time = System.currentTimeMillis();
            System.out.println("start:" + time);
        }
        
        if(System.currentTimeMillis() - time <= IGNORE_TIME){
            removeCount++;
        } else {
            removeCount = 0;
            time = System.currentTimeMillis();
        }
        
        if(removeCount >= REMOVE_COUNT ){
            System.out.println("[" + (System.currentTimeMillis() - time) + "],removeCount=" + removeCount);
            time = System.currentTimeMillis();
            System.out.println("end:" + time);
            removeCount = 0;
            return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        Visits v = new Visits();
        boolean t = false;
        for(int i = 0  ; i < 1000000 ; i++){
            t = v.ignore();
            System.out.println(t);
            if(t){
                System.exit(0);
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
