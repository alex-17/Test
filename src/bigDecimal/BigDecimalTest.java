package bigDecimal;

import java.math.BigDecimal;


public class BigDecimalTest {

    public static void main(String[] args) {
       
        /**
         * BigDecimal 由任意精度的整数非标度值 和 32 位的整数标度 (scale) 组成。如果为零或正数，则标度是小数点后的位数。如果为负数，则将该数的非标度值乘以 10 的负 scale 次幂。
         * 因此，BigDecimal 表示的数值是 (unscaledValue × 10-scale)。 
         
        BigDecimal bd = new BigDecimal(10d);
        bd.add(new BigDecimal(0.0001));
        System.out.println(bd.scale());
*/
    	System.out.println(Math.log(5));
    	System.out.println(Math.log(10));
    	System.out.println(Math.log(10 * 2));
    	System.out.println(Math.log(10 * 10));
    }

}
