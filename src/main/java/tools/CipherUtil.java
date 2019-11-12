package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.sun.xml.internal.messaging.saaj.util.Base64;

/**
 * @name CipherUtil.java
 * @author hechuan
 * @version 2.0
 * @description 加密解密算法
 * @email hechuan@tydic.com
 * @date Created On : 2018-01-17
 */
public class CipherUtil{
	private static final String SYS_PREFIX="___!LDA@qh#intf$-0971:";
	
	//======================================================MD5算法======================================================
	//MD5加密 32位
	public static String encryptMD5(String text){
		String encryptStr="";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(text.getBytes("UTF-8"));
			encryptStr = parseByteToHexStr(md5Bytes);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return encryptStr;
	}
	//MD5多重加密
	private static String multipleMD5(String text,int reCount,int offset){
		String encryptStr=text;
		StringBuilder sb=new StringBuilder();
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			for(int c=0;c<reCount;c++){
				byte[] digest=messageDigest.digest(encryptStr.getBytes("UTF-8"));
				for(int i=0;i<digest.length;i++){
					int result = digest[i]&0xff;
					String hexString = Integer.toHexString(result)+offset;
					if(hexString.length()<2){
						sb.append("0");
					}
					sb.append(hexString);
				}
				encryptStr = sb.toString(); 
				sb.delete(0, sb.length()); 
			}
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println( encryptStr);
		return encryptStr;
	}
	//base64编码
	private static String bsEncode(String str){
		try {
			return new String(Base64.encode(str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	//base64编码
	private static String getMD5Time(Date d){
		return multipleMD5(SYS_PREFIX+new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(d),2,2);
	}
	//base64解码
	public static String getTimeEncode(){
		Date curDate=new Date();
		String md5=getMD5Time(curDate);
		//System.out.println(curDate.getTime());
		//System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(curDate));
		return bsEncode(new StringBuffer(curDate.getTime()+"").reverse().toString()+":"+md5);
	}
	//======================================================工具方法======================================================
	//将二进制转换16进制显示 
	private static String parseByteToHexStr(byte bt[]){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<bt.length;i++){
			String hex=Integer.toHexString(bt[i]&0xFF);
			if(hex.length()==1){
				hex='0'+hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(multipleMD5(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()),2,2));
		
	}
}
