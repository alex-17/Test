package algorithms.test;

import java.util.HashMap;
import java.util.Map;


public class T2 {

    public static void main(String[] args) {
        //[abcdefghijklmnopqrstuvwxyz] <=> [pvwdgazxubqfsnrhocitlkeymj]
        
        String s1 = "abcdefghijklmnopqrstuvwxyz";
        String s2 = "pvwdgazxubqfsnrhocitlkeymj";
        Map<String,String> m1 = new HashMap<String,String>();
        Map<String,String> m2 = new HashMap<String,String>();
        for(int i = 0 ; i < s1.length() ; i++){
            m1.put(String.valueOf(s1.charAt(i)), String.valueOf(s2.charAt(i)));
            m2.put(String.valueOf(s2.charAt(i)),String.valueOf(s1.charAt(i)));
        }
        
        
        String s3 = "Wxgcg txgcg ui p ixgff, txgcg ui p epm. I gyhgwt mrl lig txg ixgff wrsspnd tr irfkg txui hcrvfgs, nre, hfgpig tcm liunz txg crt13 ra \"ixgff\" tr gntgc ngyt fgkgf.";
        String s = null;
        for(int j = 0 ; j < s3.length() ; j++){
            
            s = String.valueOf(s3.charAt(j));
            
            if(m1.get(s) != null){
                System.out.print(m1.get(s));
            } else if(m2.get(s) != null){
                System.out.print(m2.get(s));
            } else {
                System.out.print(s);
            }
            
        }
    }

}
