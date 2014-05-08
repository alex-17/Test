package copy.copyobj;

import java.io.Serializable;

public class Obj implements Serializable{
    
    private static final long serialVersionUID = -9096045882017629516L;

    private int i;
    
    private String s;
    
    private int[] arr;



    public int getI() {
        return i;
    }

    
    public void setI(int i) {
        this.i = i;
    }

    
    public String getS() {
        return s;
    }

    
    public void setS(String s) {
        this.s = s;
    }

    
    public int[] getArr() {
        return arr;
    }

    
    public void setArr(int[] arr) {
        this.arr = arr;
    }
    
}
