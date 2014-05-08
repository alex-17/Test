package javabase;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class Base1 {

    public static void to(Object obj){
        StrObj so = (StrObj) obj;
        if(so == null){
            System.out.println("--------");
        }
    }
    
    public static void main(String[] args) {
        boolean f = false;
        System.out.println( f ? "true" : "false");
        
        String n = "1.2";
        System.out.println(NumberUtils.toInt(n,-1));
        
        System.out.println(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()));
        
        to(null);
    }
}
