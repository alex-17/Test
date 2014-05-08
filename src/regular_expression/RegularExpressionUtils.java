package regular_expression;

import java.util.regex.Pattern;


public class RegularExpressionUtils {

    private static Pattern pattern = Pattern.compile("^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$");
    
    public static boolean isIP(String ip){
        return pattern.matcher(ip).matches();
    }
    public static void main(String[] args) {
        
        System.out.println(isIP("222100.2.3.222"));

    }

}
