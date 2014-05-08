package javabase;

public class FunctionPass {

    public void print(String str1,String str2){
        str1 = str1 + "--";
        str2 = str2 + "--";
        System.out.println(str1);
        System.out.println(str2);
    }
    
    public static void main(String[] args) {
        FunctionPass fp = new FunctionPass();
        String s1 = "hello";
        String s2 = "world";
        
        fp.print(s1, s2);
        
        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);
        
        String a = "v";
        
        System.out.println("a=" + a.substring(1, a.length()));
        
    }
    
}
