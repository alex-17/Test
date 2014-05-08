package exception;

public class A {

    
    public static int get() throws Exception{
        
        try{
            int j = 1 / 0;
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error");
        }
        System.out.println("----------------------------------------");
        return -1;
        
    }
    
}
