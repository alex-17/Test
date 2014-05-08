package jvm.gc;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();
        for(int i = 0 ; i < 1000000 ; i++){
            
            list.add(String.valueOf(Math.random()));
            
        }
        
        long end = System.currentTimeMillis();
        System.out.println( (end - start) );
        Thread.sleep(Integer.MAX_VALUE);
        
        for(String s : list){
            System.out.println(s);
        }
        
    }

}
