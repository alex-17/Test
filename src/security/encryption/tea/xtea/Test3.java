package security.encryption.tea.xtea;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import compress.all_gzip_zip.Compressor;
import xtea.SecurityRandomUtil;

public class Test3 {

    private static String getFilePath(String fileName){
        if(StringUtils.isNotBlank(fileName)){
            int index = fileName.indexOf("/");
        }
        return null;
    }
    
public static void main(String[] args) throws UnsupportedEncodingException {
        
      /*  for(int jj = 0 ; jj < 100000 ; jj++){
            
            long testNum = new Date().getTime();
            String resultNum;
            String unStr = null;
            System.out.println("test number=" + testNum);
            System.out.println("[" + (unStr = compressNumber(testNum)) + "]");
            System.out.println("num=" +  (resultNum = unCompressNumber( unStr )));
            if((resultNum.equals(String.valueOf(testNum)))){
                System.out.println("ok");
            } else {
                System.out.println("error");
                System.exit(0);
            }
            
            try {
                Thread.sleep(new Random().nextInt(1000)%(1000+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("===================================");
        long l = new Date().getTime();
        System.out.println(l);
        System.out.println(compressNumber(1396592785107L));*/
        
        String fileName = "files/apps/ceshi_20130814_20130820111921411_20131022103140200.apk";
        
        StringBuilder str = buildUrl("files/apps/ceshi_20130814_20130820111921411_20131022103140200.apk",0);
        
        int strLength = 0;
        strLength = (strLength = str.length() / 16) < 0 ? 16 : ((strLength + 1) * 16);
        System.out.println("strLength=" + strLength);
        strLength = strLength - str.length();
        if(strLength > 0){
            str.append(SecurityRandomUtil.getRandomString(strLength));
        }
        
        System.out.println("str=" + str);
        
        byte[] info = str.toString().getBytes("UTF-8");
        
        System.out.println("info length=" + info.length);
        
        XTEA xtea = new XTEA();
        String key = "sl7C?4L@Dsl7C?4L@DxZod**-9CdTsl7C?4L@DxZod**-9CdTxZod**-9CdT";//SecurityRandomUtil.getRandomString(20);
        byte[] keyByte =  key.getBytes("UTF-8");
        xtea.setKey(keyByte);
        System.out.println("key=" + key);
        
        
        xtea.encrypt(info, 0, info.length);
        
        info = Compressor.getInstance().compress(info);//压缩
        
        String encryptStr = Base64.encodeBase64String(info);
        
        System.out.println("Encrypt str=" + encryptStr);
        
        
        byte[] rs = Base64.decodeBase64(encryptStr);
        
        rs = Compressor.getInstance().decompress(rs);//解压缩
        
        xtea.decrypt(rs, 0, rs.length);
        
        String rsStr = new String(rs,"UTF-8");
        
        System.out.println("info=" + rsStr );
        
        System.out.println(0x9E3779B9);
        
        
    }

    private static StringBuilder buildUrl(String fileName,long time){
        if(StringUtils.isBlank(fileName)){
            return null;
        }
        
        StringBuilder subUrl = new StringBuilder();
        long timeBuilder = -1;
        if(time > 0){
            timeBuilder = new Date().getTime();
            timeBuilder += time;
        }
        subUrl.append(timeBuilder);
        
        int f = fileName.lastIndexOf("/");
        String subFilePath = null;
        if(f > 0){
            subFilePath = fileName.substring(0,f + 1);
        }
        
        if("files/apps/".equals(subFilePath)){
            subUrl.append(fileName.substring(f + 1, fileName.length()));
        } else {
            subUrl.append(fileName);
        }
        
        return subUrl;
    }
    
    private static char numberToChar(String numStr){
        int num = Integer.valueOf(numStr);
        if(numStr.length() > 1 && num < 10){
            num += 100;
        }
        return (char) num;
    }
    
    public static String compressNumber(long num) throws UnsupportedEncodingException{
        String numStr = String.valueOf(num);
        StringBuilder str = new StringBuilder();
        for(int i = 0 ; i < numStr.length() - 1 ; i+=2){ 
            str.append( numberToChar(numStr.substring(i, i+2)) );
        }
        
        if(numStr.length() % 2 == 1){
            str.append( numberToChar(numStr.substring(numStr.length() - 1, numStr.length())));
        }
       
        return  Base64.encodeBase64String(str.toString().getBytes("UTF-8"));
    }
    
    public static String unCompressNumber(String numStr) throws UnsupportedEncodingException{
        
        numStr = new String(Base64.decodeBase64(numStr),"UTF-8");
        
        StringBuilder num = new StringBuilder();
        int tmpNum;
        for(int i = 0 ; i < numStr.length(); i++){
            tmpNum = (int) numStr.charAt(i);
            if(tmpNum >= 100){
                tmpNum -= 100;
                if(tmpNum < 10 ){
                    num.append(0);
                }
            }
            
            num.append(tmpNum);
        }
        System.out.println("[" + numStr.substring(numStr.length() - 1, numStr.length()));
        return num.toString();
    }
    
	

}
