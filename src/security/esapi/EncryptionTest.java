package security.esapi;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.errors.IntegrityException;

import compress.all_gzip_zip.Compressor;

public class EncryptionTest {

    public static final String OWASP_PROPERTIES = "org.owasp.esapi.resources";
    
    public static void main(String[] args) throws EncryptionException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IntegrityException, InterruptedException {

        //String propertiesPath = EncryptionTest.class.getClassLoader().get;
        
        System.setProperty(OWASP_PROPERTIES,"F:/draft/esapi");
        
        String str = "hello";
        
        System.out.println(ESAPI.encryptor().encrypt(new PlainText(str)));
        System.out.println("sign=" + ESAPI.encryptor().sign(str));
        String seal = ESAPI.encryptor().seal(String.valueOf(new Date().getTime()), (new Date().getTime() + 10000L));
        System.out.println("seal=" + seal);
        System.out.println("seal size =" + seal.length());
        System.out.println("seal byte size =" + seal.getBytes("UTF-8").length);
        System.out.println("compress =" + Compressor.getInstance().compress(seal.getBytes("UTF-8")).length);
        System.out.println("now unseal=" + ESAPI.encryptor().unseal(seal));
        Thread.sleep(15000);
        System.out.println("unseal=" + ESAPI.encryptor().unseal(seal));
        
        //ESAPI.encryptor().decrypt(ciphertext)
       // System.out.println(Base64.decode( str ));
       // System.out.println(Base64.encodeBytes( Base64.decode( str ) ));
       // System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64String(binaryData));
        
    }
    
    public static byte[] initkey() throws NoSuchAlgorithmException{  
        //实例化密钥生成器  
        KeyGenerator kg=KeyGenerator.getInstance("AES");  
        //初始化密钥生成器，AES要求密钥长度为128位、192位、256位  
        kg.init(256);  
        //生成密钥  
        SecretKey secretKey=kg.generateKey();  
        //获取二进制密钥编码形式  
        return secretKey.getEncoded();  
    }
    
    public static Key toKey(byte[] key){  
        //实例化DES密钥  
        //生成密钥  
        SecretKey secretKey=new SecretKeySpec(key,"AES");  
        return secretKey;  
    }  

    public static byte[] encrypt(byte[] data,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{  
        //还原密钥  
        Key k=toKey(key);  
        /** 
         * 实例化 
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现 
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC") 
         */  
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCSPadding");  
        //初始化，设置为加密模式  
        cipher.init(Cipher.ENCRYPT_MODE, k);  
        //执行操作  
        return cipher.doFinal(data);  
    }  
    
}
