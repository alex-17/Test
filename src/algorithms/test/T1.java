package algorithms.test;


public class T1 {
    
    public static boolean isDouble(double d){
        if(d > 0 && d < 1){
            return true;
        }
        return false;
    }
    
    public static String toBinaryString(int i){
        StringBuilder sb = new StringBuilder();
        while(i > 0){
            sb.insert(0, i % 2);
            i = i / 2;
        }
        return sb.toString();
    }
    
    public static void print2arr(int[][] a){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(a[i][j] + ",");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        System.out.println((0 + 15) / 2);
        
        System.out.println(2.0e-6 * 100000000.1);
        
        System.out.println((true && false || true && true));
        
        System.out.println((1 + 2.236) / 2);
        
        System.out.println((1 + 2 + 3 + 4.0));
        
        System.out.println((4.1 >= 4));
        
        System.out.println((1 + 2 + "3"));
        
        System.out.println((char) ('a' + 4));
        
        System.out.println(('b' + 'c'));
        
        System.out.println(T1.isDouble(0.12));
        
        System.out.println(Integer.toBinaryString(10));
        
        System.out.println(T1.toBinaryString(10));
        
        int[][] a = new int[3][3];
        a[0][0] = 1;
        a[0][1] = 2;
        a[0][2] = 3;
        
        a[1][0] = 4;
        a[1][1] = 5;
        a[1][2] = 6;
        
        a[2][0] = 7;
        a[2][1] = 8;
        a[2][2] = 9;
        
        T1.print2arr(a);
        System.out.println("--------------------");
        int tmp = 0;
        for(int i = 0; i < 3; i++){
            for(int j = i; j < 3; j++){
                tmp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = tmp;
            }
        }
        
       T1.print2arr(a);
    }

}
