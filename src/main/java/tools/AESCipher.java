package tools;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;

/**
 * @name CipherCode.java
 * @author hechuan
 * @version 2.0
 * @description 加密解密算法
 * @email hechuan@tydic.com
 * @date Created On : 2018-01-17
 */
public class AESCipher{
	//CipherCode.encryptMD5("_lda@tydic#chinatelecom!qh*971");
	private static final String DEFAULT_AES_KEY="7E174BEFA9F62C846F61BD441DC41E62";
	//======================================================AES加解密======================================================
	public static String encryptAES(String text){
		return encryptAES(text,DEFAULT_AES_KEY);
	}
	//DES对加密
	public static String encryptAES(String text,String desCode){
		String encryptStr="";
		try {
			//创建密码器
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			//初始化
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(desCode.getBytes("UTF-8"),"AES"));
			byte[] result = cipher.doFinal(text.getBytes("UTF-8"));
			encryptStr=Base64.encode(result).replaceAll("\n", "");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return encryptStr;
	}
	//DES解密
	public static String decryptAES(String text){
		return decryptAES(text,DEFAULT_AES_KEY);
	}
	public static String decryptAES(String encryptText,String desCode){
		String decryptStr="";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(desCode.getBytes("UTF-8"),"AES"));//初始化
			byte[] result = cipher.doFinal(Base64.decode(encryptText));
			decryptStr=new String(result);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return decryptStr;
	}
}
