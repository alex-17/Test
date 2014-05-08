package jvm.gc;

public class OCount {

    public static void main(String[] args) throws InterruptedException {
       
        Obj o1 = new Obj();
        Obj o2 = new Obj();
        
        o1.o = o2;
        o2.o = o1;
        
    
        System.gc();

        Thread.sleep(10000);
        
        System.out.println(o1);
        System.out.println(o2);
        
    }

}
