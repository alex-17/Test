package exception;

public class ExceptionTest {

    public static void main(String[] args) {
        
        try {
            System.out.println(A.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
