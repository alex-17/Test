package reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        List<String[]> templist = new ArrayList<String[]>();
        //新增一个引用
        List<String[]> list = new ArrayList<String[]>();
        //设的多一点，可以让GC真实发挥
        for(int i=0; i < 1000000; i++){
            String[] tempstr = new String[2];
            templist.add(tempstr);
            list.add(tempstr);
        }

        Map<String[], String[]> map = new WeakHashMap<String[], String[]>();
        for(int i=0; i < 100; i++){
            map.put(templist.get(i), new String[2]);
            templist.set(i, null); //删除掉引用 
            System.gc();
            System.out.println(map.size());
        }
        
    }

}
