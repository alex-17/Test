package jvm.gc;

import java.util.ArrayList;
import java.util.List;

public class T3 {

    static class ObjectM {
        public byte[] place = new byte[1024 * 1024];
    }
    
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<ObjectM> list = new ArrayList<ObjectM>();
        for(int i = 0 ; i < 30; i++){
            Thread.sleep(500);
            list.add(new ObjectM());
        }
        long end = System.currentTimeMillis();
        System.out.println( (end - start) );
    }

}
