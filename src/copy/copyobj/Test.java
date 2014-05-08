package copy.copyobj;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class Test {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Obj o = new Obj();
        o.setArr(new int[]{1,2});
        o.setI(100);
        o.setS("hello");
        
         Obj o2  = (Obj) BeanUtils.cloneBean(o);
         System.out.println(o);
         System.out.println(o2);
        System.out.println(o == o2);
        System.out.println(o2.getArr());
    }

}
