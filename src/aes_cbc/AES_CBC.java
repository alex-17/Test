package aes_cbc;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import xtea.SecurityRandomUtil;


public class AES_CBC {
    
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public SecretKeySpec myDesKey = null;

    Cipher c = null;

    public IvParameterSpec iv = null;

    public AES_CBC(String key) throws Exception {
        myDesKey = new SecretKeySpec(Base64.decodeBase64(key.getBytes(UTF8)),"AES" );
        c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        iv = new IvParameterSpec(new byte[16]);
    }

    public String doEncryption(String s) throws Exception {
        // Initialize the cipher for encryption
        c.init(Cipher.ENCRYPT_MODE, myDesKey, iv);

        // sensitive information
        byte[] text = s.getBytes(UTF8);

        // Encrypt the text
        byte[] textEncrypted = c.doFinal(text);
        return Base64.encodeBase64String(textEncrypted);

    }

    public String doDecryption(String s) throws Exception {

        // Initialize the same cipher for decryption
        c.init(Cipher.DECRYPT_MODE, myDesKey, iv);

        // Decrypt the text
        byte[] textDecrypted = c.doFinal(Base64.decodeBase64(s.getBytes(UTF8)));

        return (new String(textDecrypted));
    }
    
    public static void main(String[] args) throws Exception {
        String key = SecurityRandomUtil.getRandomString(16);
        System.out.println("before:" + key);
        key = Base64.encodeBase64String(key.getBytes(UTF8));
        System.out.println("after:" + key);
        AES_CBC t = new AES_CBC(key);
        String str = "11111111111111112大王";
        System.out.println( t.doEncryption(str) );
    }
    
}
