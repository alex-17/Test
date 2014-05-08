package security.test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import xtea.SecurityRandomUtil;
import xtea.XTEA;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
	    
	    System.out.println(DigestUtils.md5Hex("files/apps/xxxx_20130918101554496.apk"));
	    
/*System.out.println("Security Random=" + SecurityRandomUtil.getRandomString(30));
		String random = "7HtX6dT.xzzsquYnlwK";//存在的意义不大
		System.out.println("random=" + random);
		
		String str = "1396232753795";//String.valueOf(new Date().getTime() + (60 * 1000) );//当前时间+一分钟
		
		System.out.println("str=" + str);
		
		byte[] info = (str + random).getBytes("UTF-8");
		
		System.out.println("info length=" + info.length);
		
		XTEA xtea = new XTEA();
		String key = "sl7C?4L@DxZod**-9CdT";//SecurityRandomUtil.getRandomString(20);
		byte[] keyByte =  key.getBytes("UTF-8");
		xtea.setKey(keyByte);
		System.out.println("key=" + key);
		
		
		xtea.encrypt(info, 0, info.length);
		
		//info = Compressor.getInstance().compress(info);//压缩
		
		String encryptStr = Base64.encodeBase64String(info);
		
		System.out.println("Encrypt str=" + encryptStr);
		
		
		byte[] rs = Base64.decodeBase64(encryptStr);
		
	//	rs = Compressor.getInstance().decompress(rs);//解压缩
		
		xtea.decrypt(rs, 0, rs.length);
		
		String rsStr = new String(rs,"UTF-8");
		
		System.out.println("info=" + rsStr );
		
		System.out.println(0x9E3779B9);*/
		
		
	}

}
