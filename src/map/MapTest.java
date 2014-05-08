package map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class MapTest {
    
    public static void main(String[] args) {
        
        Map<String,Double> map = new TreeMap<String,Double>();
        map.put("1", 1d);
        map.put("2", 2d);
        
        Set<Entry<String, Double>> mapSet = map.entrySet();
        System.out.println(mapSet.size());
        
        for(Entry<String, Double> e : mapSet){
            System.out.println("getKey=" + e.getKey());
        }
    }

}
