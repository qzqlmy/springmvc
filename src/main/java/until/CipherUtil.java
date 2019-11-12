package until;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @名称: <code>CipherUtil</code>
 * @描述: 加密工具的集合,支持AES、RSA、MD5、BASE64等加密
 * @依赖: 不依赖任何包,jdk内部方法调用
 * @注意：由于美国出口限制,很多加密策略受限,如密钥长度限制等;
 *       尽量下载Oracle的无政策限制权限文件（Unlimited Strength Jurisdiction Policy Files）
 *       替换JDK_HOME>jre>lib>security和JRE_HOME>lib>security下的local_policy.jar、US_export_policy.jar这两个文件
 * @作者: Lyndon
 * @版本: 1.0.0
 * @日期: 创建于2019年01月01日
 */

public class CipherUtil {
	public static final String RSA_PUBLIC_KEY="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIKRWOPX2+/OGHDiXetm6ss+h0GWTjSImZXPsQQF4Lwa\n" +
			"e1PQWwShSkiifcxvBT0E7QydiKoTdBoQMHbu4LJIzG0CAwEAAQ==";
	public static final String RSA_PRIVATE_KEY="MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAgpFY49fb784YcOJd62bqyz6HQZZO\n" +
			"NIiZlc+xBAXgvBp7U9BbBKFKSKJ9zG8FPQTtDJ2IqhN0GhAwdu7gskjMbQIDAQABAkBqDQKFNbwn\n"+
			"gF+ruMGORW6qHKShqiLlWppc63AdE2UhgrQPH7PNWwh6LpGLoTpj3YuizjfbPRRwZjOJREC4hEdl\n"+
			"AiEA5hBD3a0ODWyhzM+kCi9qL7D4C4biL5qikNVHCHbUvX8CIQCRSZcfiHO+LuxV0CyJJ99tboc2\n"+
			"J+filtYiGUgJCFdEEwIgIFL4thSyfMBem2AxNB0lVj4PG89/yCB55s+Qg2fuzBECIAZos6TtdQWf\n"+
			"tkc2zrAiNSXzqT1HV2BUfQ+2A8p6NrdHAiEA2i/QlKucj+mkkOmM6uP/vd7JS5YMtemE5dssGD64\n"+
			"GYg=";
	private static final int AES_SIZE_AES=128; //AES密钥默认长度,区块长度固定为128位,密钥长度则可以是128,192或256位
	private static final int DEFAULT_RSA_KEY_SIZE=1024; //RSA密钥默认长度,解密最大长度=DEFAULT_RSA_KEY_SIZE/8,采用padding模式时,padding本身会占用11个字节,故加密最大长度=DEFAULT_RSA_KEY_SIZE/8-11
	private static final String KEY_CHAR_MAP="AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1357908642";
	
	/* -----------------------------------------------------------------------
	   --------------------------- 对称加密算法 [AES] --------------------------
	   ----------------------------------------------------------------------- 
	   描述：AES填充模式采用AES/ECB/PKCS5Padding与CryptoJS使用的padding保持一致，
	   其中PKCS5Padding对应js的CryptoJS.pad.Pkcs7 
	   ----------------------------------------------------------------------- */
	/**
	 * AES密钥生成
	 * @param	keySize	指定密钥长度
	 * @param	desSeed	指定密钥种子
	 * @return	字符串密钥
	 */
	public static String genAESKey(int keySize,String desSeed){
		String desKey=null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(desSeed.getBytes());
			keyGenerator.init(keySize, secureRandom);
			desKey=Base64.encode(keyGenerator.generateKey().getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return desKey;
	}
	/**
	 * AES密钥生成
	 * @param	desSeed	指定密钥种子
	 * @return	字符串密钥
	 */
	public static String genAESKey(String desSeed){
		return genAESKey(AES_SIZE_AES,desSeed);
	}
	/**
	 * AES密钥生成
	 * @param	keySize	指定密钥长度
	 * @return	字符串密钥
	 */
	public static String genAESKey(int keySize){
		String desKey=null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(keySize,SecureRandom.getInstance("SHA1PRNG"));
			desKey=Base64.encode(keyGenerator.generateKey().getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return desKey;
	}
	/**
	 * AES密钥生成-随机
	 * @return	字符串密钥
	 */
	public static String genAESKey(){
		return genAESKey(AES_SIZE_AES);
	}
	/**
	 * AES加密
	 * @param	text	待加密的文本
	 * @param	desKey	密钥
	 * @return	经base64编码后的密文
	 */
	public static String encryptAES(String text,String desKey){
		String secretText=null;
		try {
			SecretKeySpec key=new SecretKeySpec(desKey.getBytes(), "AES");
			Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");//创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);//如需要偏移需要增加new IvParameterSpec(iv)参数
			byte[] result = cipher.doFinal(text.getBytes("UTF-8"));
			secretText=Base64.encode(result);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return secretText;
	}
	/**
	 * AES解密
	 * @param	secretText	密文
	 * @param	desKey	密钥
	 * @return	明文字符串
	 */
	public static String decryptAES(String secretText,String desKey){
		String text=null;
		try {
			SecretKeySpec key=new SecretKeySpec(desKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);//如需要偏移需要增加new IvParameterSpec(iv)参数
			byte[] result = cipher.doFinal(Base64.decode(secretText));
			text=new String(result,"UTF-8");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return text;
	}
	
	/* ------------------------------------------------------------------------
	   --------------------------- 非对称加密算法 [RSA] --------------------------
	   ------------------------------------------------------------------------
	   描述：RSA密钥长度必须是64的倍数，在512~65536之间。默认是1024
	   RSA填充模式采用RSA/ECB/PKCS1Padding与jsencrypt.min.js保持一致
	   注：RSA速度较慢.一般用AES产生随机密钥,然后AES加密明文;再把密钥给RSA加密生成密钥密文,
	      最后将加密明文和密钥密文一起发送.接到后先通过RSA获取密钥,再用AES解密
	   ----------------------------------------------------------------------- */
	/**
	 * RSA公钥/私钥对生成
	 * @param	keySize	密钥大小
	 * @param	rsaSeed	密钥种子
	 * @return	密钥对
	 */
	public static KeyPair genRSAKey(int keySize, String rsaSeed){
		KeyPair kpRSA = null;
		try {
			SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(rsaSeed.getBytes("UTF-8")); 
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(keySize,secureRandom);
			kpRSA = generator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		
		return kpRSA;
	}
	/**
	 * RSA公钥/私钥对生成
	 * @param	rsaSeed	密钥种子
	 * @return	密钥对
	 */
	public static KeyPair genRSAKey(String rsaSeed){
		return genRSAKey(DEFAULT_RSA_KEY_SIZE,rsaSeed);
	}
	/**
	 * RSA公钥/私钥对生成
	 * @param	keySize	密钥大小
	 * @return	密钥对
	 */
	public static KeyPair genRSAKey(int keySize){
		KeyPair kpRSA = null;
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(keySize,SecureRandom.getInstance("SHA1PRNG"));
			kpRSA = generator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		
		return kpRSA;
	}
	/**
	 * RSA公钥/私钥对生成
	 * @return	密钥对
	 */
	public static KeyPair genRSAKey(){
		return genRSAKey(DEFAULT_RSA_KEY_SIZE);
	}
	/**
	 * RSA分组加密(注：不同密钥加解密的长度不同)
	 * @param	text	待加密文本
	 * @param	publicKey	公钥
	 * @param	keySize		密钥大小
	 * @return	经base64编码后的密文
	 */
	public static String encryptRSA(String text,String publicKey,int keySize) {
		String secretText=encryptRSACommon(text, publicKey, keySize);
		if(secretText!=null&&secretText.startsWith("KEY_SIZE:")){
			String[] keySizeInfo=secretText.split(":");
			try {
				keySize=Integer.parseInt(keySizeInfo[1]);
				secretText=encryptRSACommon(text, publicKey, keySize);
			} catch(Exception e) {
				e.printStackTrace();

			}
		}
		
		return secretText;
	}
	/**
	 * RSA分组加密(注：不同密钥加解密的长度不同)
	 * @param	text	待加密文本
	 * @param	publicKey	公钥
	 * @param	keySize		密钥大小
	 * @return	经base64编码后的密文
	 */
	private static String encryptRSACommon(String text,String publicKey,int keySize) {
		String secretText=null;
		ByteArrayOutputStream out=null;
		try{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pk=keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(publicKey)));
			Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			out=new ByteArrayOutputStream();
			byte[] textByte=text.getBytes("UTF-8"),cacheByte;
			int offset=0,index=1,textLength=textByte.length,diffVal=0,encryptBlockLimit=keySize/8-11;
			//分组加密
			while((diffVal=textLength-offset)>0){
				if(diffVal>encryptBlockLimit) cacheByte=cipher.doFinal(textByte, offset, encryptBlockLimit);
				else cacheByte=cipher.doFinal(textByte, offset, diffVal);
				out.write(cacheByte, 0, cacheByte.length);
				offset=index++*encryptBlockLimit;
			}
			secretText=Base64.encode(out.toByteArray());
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			int encryptLimit= Integer.valueOf(e.getMessage().replaceAll("[a-zA-Z ]+", ""));
			encryptLimit=(encryptLimit+11)*8;

			secretText="KEY_SIZE:"+encryptLimit;
		} finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return secretText;
		
	}
	/**
	 * RSA分组加密(注：不同密钥加解密的长度不同)
	 * @param	text		待加密文本
	 * @param	publicKey	公钥
	 * @return	经base64编码后的密文
	 */
	public static String encryptRSA(String text,String publicKey) {
		return encryptRSA(text, publicKey, DEFAULT_RSA_KEY_SIZE);
	}
	/**
	 * RSA分组解密(注：不同密钥加解密的长度不同)
	 * @param	secretText	加密文本
	 * @param	privateKey	私钥
	 * @param	keySize		密钥大小
	 * @return	明文字符串
	 */
	private static String decryptRSA(String secretText,String privateKey,int keySize) {
		String text=decryptRSACommon(secretText, privateKey, keySize);
		if(text!=null&&text.startsWith("KEY_SIZE:")){
			String[] keySizeInfo=text.split(":");
			try {
				keySize=Integer.parseInt(keySizeInfo[1]);
				text=decryptRSACommon(secretText, privateKey, keySize);
			} catch(Exception e) {

				e.printStackTrace();
			}
		}
		
		return text;
	}
	/**
	 * RSA分组解密(注：不同密钥加解密的长度不同)
	 * @param	secretText	加密文本
	 * @param	privateKey	私钥
	 * @param	keySize		密钥大小
	 * @return	明文字符串
	 */
	private static String decryptRSACommon(String secretText,String privateKey,int keySize) {
		String text=null;
		ByteArrayOutputStream out=null;
		try{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey pk=keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(privateKey)));
			Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pk);
			out=new ByteArrayOutputStream();
			byte[] secretByte=Base64.decode(secretText),cacheByte;
			int offset=0,index=1,textLength=secretByte.length,diffVal=0,decryptBlockLimit=keySize/8;
			//分组解密
			while((diffVal=textLength-offset)>0){
				if(diffVal>decryptBlockLimit) cacheByte=cipher.doFinal(secretByte, offset, decryptBlockLimit);
				else cacheByte=cipher.doFinal(secretByte, offset, diffVal);
				out.write(cacheByte, 0, cacheByte.length);
				offset=index++*decryptBlockLimit;
			}
			text=new String(out.toByteArray(),"UTF-8");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			int decryptLimit = Integer.valueOf(e.getMessage().replaceAll("[a-zA-Z ]+", ""));
			decryptLimit=decryptLimit*8;

			text="KEY_SIZE:"+decryptLimit;
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Base64DecodingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return text;
		
	}
	/**
	 * RSA分组解密(注：不同密钥加解密的长度不同)
	 * @param	secretText	加密文本
	 * @param	privateKey	私钥
	 * @return	明文字符串
	 */
	public static String decryptRSA(String secretText,String privateKey) {
		return decryptRSA(secretText,privateKey,DEFAULT_RSA_KEY_SIZE);
	}

	/* ------------------------------------------------------------------------
	   --------------------------- 不可逆加密算法 [MD5] --------------------------
	   ------------------------------------------------------------------------
	   描述：MD5包含标准模式和加盐模式
	   ----------------------------------------------------------------------- */
	/**
	 * MD5加密---标准模式
	 * @param	text	待加密文本
	 * @return	经base64编码后的密文
	 */
	public static String encryptMD5(String text){
		String secretText=null;
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			byte[] md5Bytes=md5.digest(text.getBytes("UTF-8"));
			secretText=Base64.encode(md5Bytes);
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return secretText;
	}
	/**
	 * MD5加密---加盐模式
	 * @param	text	待加密文本
	 * @param	cycleAndOffset	循环与偏移量
	 * @return	经base64编码后的密文
	 */
	public static String encryptMD5(String text,int cycleAndOffset){
		String secretText=null;
		try{
			secretText=text;
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			for(int c=0;c<cycleAndOffset;c++){
				byte[] digest=messageDigest.digest(secretText.getBytes("UTF-8"));
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<digest.length;i++){
					String hexString=Integer.toHexString(digest[i]&0xA0+cycleAndOffset);
					if(hexString.length()<2) sb.append("0");
					sb.append(hexString);
				}
				secretText=Base64.encode(sb.toString().getBytes());
			}
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return secretText;
	}
	/**
	 * MD5加密---加盐模式
	 * @param	text	待加密文本
	 * @param	cycle	循环量
	 * @param	offset	偏移量
	 * @return	经base64编码后的密文
	 */
	public static String encryptMD5(String text,int cycle,int offset){
		String secretText=text;
		StringBuilder sb=new StringBuilder();
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			for(int c=0;c<cycle;c++){
				byte[] digest=messageDigest.digest(secretText.getBytes("UTF-8"));
				for(int i=0;i<digest.length;i++){
					int result = digest[i]&0xff;
					String hexString = Integer.toHexString(result)+offset;
					if(hexString.length()<2) sb.append("0");
					sb.append(hexString);
				}
				secretText = sb.toString(); 
				sb.delete(0, sb.length()); 
			}
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return secretText;
	}

	/* -----------------------------------------------------------------------
	   --------------------------- 编码算法 [base64] --------------------------
	   -----------------------------------------------------------------------
	   描述：利用base64编码与简单规则加密
	   ----------------------------------------------------------------------- */
	/**
	 * base64编码-简单加密
	 * @param	text	待加密文本
	 * @return	经base64编码后的密文
	 */
	public String encryptB64(String text){
		String secretText="";
		if(text.length()>0){
			int charLen=KEY_CHAR_MAP.length();
			int len=(int)Math.random()*10;
			StringBuilder fristSecretText=new StringBuilder();
			for(int i=0;i<len;i++){
				fristSecretText.append(KEY_CHAR_MAP.charAt((int)(charLen*Math.random())));
			}
			fristSecretText.append("0");
			if(len<10){
				fristSecretText.append(Base64.encode(text.getBytes()));
			}
			fristSecretText.append(len);
			secretText=Base64.encode(fristSecretText.toString().getBytes());
		}
		
		return secretText;
	}
	/**
	 * base64编码-解密
	 * @param	secretText	密文
	 * @return	明文字符串
	 */
	public String decryptB64(String secretText){
		String text=null;
		if(secretText.length()>0){
			try{
				String fristText=new String(Base64.decode(secretText));
				try{
					int prefixLen=Integer.parseInt(fristText.substring(fristText.length()-2));
					text=new String(Base64.decode(fristText.substring(prefixLen, fristText.length()-2)));
				}catch(Exception e){
					e.printStackTrace();
				}
			} catch (Base64DecodingException e) {
				e.printStackTrace();
			}
		}
		
		return text;
	}
}
