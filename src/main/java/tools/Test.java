package tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import static java.lang.System.out;


@SuppressWarnings("all")
public class Test {
	/**
	 * @Title:@param args
	 * @Description:方法
	 * @param:request
	 * @return:MapEntry<String,Object>
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		/*
		List<String> testList=new ArrayList<String>();
		testList.add("com/frame/common/BaseMap.jsp");
		testList.add("com/frame/common/error_503.jsp");
		testList.add("system/page/module/festival/festival_01.01_detail.jsp");
		testList.add("system/frame/common/review.jsp");
		testList.add("system/frame/common/index.jsp");
		testList.add("system/frame/common/operation.jsp");
		testList.add("system/frame/common/festival_11.11.jsp");
		testList.add("system/frame/common/festival_01.01_goods.jsp");
		testList.add("system/frame/common/festival_01.01_detail.jsp");
		testList.add("system/frame/common/festival_10.01.jsp");
		testList.add("com/frame/common/business.jsp");
		
		StringBuffer testBuffer=new StringBuffer();
		testBuffer.append("com/frame/common/BaseMap.jsp,");
		testBuffer.append("com/frame/common/error_503.jsp,");
		testBuffer.append("system/page/module/festival/festival_01.01_detail.jsp,");
		testBuffer.append("system/frame/common/review.jsp,");
		testBuffer.append("system/frame/common/index.jsp,");
		testBuffer.append("system/frame/common/operation.jsp,");
		testBuffer.append("system/frame/common/festival_11.11.jsp,");
		testBuffer.append("system/frame/common/festival_01.01_goods.jsp,");
		testBuffer.append("system/frame/common/festival_01.01_detail.jsp,");
		testBuffer.append("system/frame/common/festival_10.01.jsp,");
		testBuffer.append("com/frame/common/business.jsp,");
		String testString=testBuffer.toString();
		long a=System.nanoTime();
		//在最好的一行加上:
		testList.contains("com/frame/common/business.jsp");
		System.out.println("List执行耗时 : "+(System.nanoTime()-a)+"纳秒");
		
		a=System.nanoTime();
		//在最好的一行加上:
		testString.contains("com/frame/common/business.jsp");
		System.out.println("String执行耗时 : "+(System.nanoTime()-a)+"纳秒"); 
		*/
		//System.out.println((int)Math.ceil(13*1.0/2));
		//System.out.println(13/3+13%3);
		//String str="ds好似你SDFsdf打扫!";
		//byte a[]=str.getBytes();
		//int l=4;
		//String tt=str.substring(0,l);		
		//if(tt.getBytes().length!=l){
			//System.out.println(str.substring(0,l));
		//}else{
			//System.out.println(tt);
		//}
		/*
		String s="duck";
		StringBuffer sb=new StringBuffer("duck");
		String e=new String("duck");
		String t=e;
		System.out.println(s.equals(sb));
		System.out.println(s.equals(e));
		System.out.println(t.equals(sb));
		System.out.println(s==t);*/
		//String str="";
		//System.out.println(str.concat("abc"));
		//对称加密测试
		//System.out.println(CipherCode.getEncryptCode("CDCS:"));
		//System.out.println(CipherCode.getEncryptCode("CDCS:",-1));
		//System.out.println(CipherCode.decryptAES("FA92F4182867F9EEB53800F37E83008E", "CONSTANT_SECRET_KEY_CDCS-ODS*tydic@123"));
		//System.out.println(new CdcsFilter().decryptAES("FA92F4182867F9EEB53800F37E83008E"));

		//System.out.println(Constant.CDCS_KEY);
		
		//System.out.println(CipherCode.encryptAES("baicd", Constant.CDCS_KEY));
		//企业数据管理平台
		//System.out.println(CipherCode.encryptMD5("Qysj@9991"));
		//跨域比对
		//System.out.println(CipherCode.encryptMD5("KYBD@7102"));
		//客服
		//System.out.println(CipherCode.encryptMD5("KHFX@6217"));
		//System.out.println(CipherCode.decryptAES("FA92F4182867F9EEB53800F37E83008E", Constant.CDCS_KEY));
		//System.out.println(CipherCode.encryptMD5("KHFX@6217"));
		//KeyPair kp=CipherCode.getKeyPair("tydic@123");
		//System.out.println(CipherCode.encryptRSA("KHFX@6217",kp));
		//System.out.println(CipherCode.decryptRSA("B7097E69E3085200E6BC4FA92098D91CE6A3F3212ECC8AD4D1B4F17BBD8362E3",kp));

		//System.out.println(27/1.5);
		//System.out.println(27/2.0);
		//System.out.println(27/4.0);
		//System.out.println("LDA@intf:["+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"]");
		/*
    	String date=new SimpleDateFormat("[yyyy-MM-dd HH:mm]").format(new Date());
    	System.out.println("--date:"+date);
    	System.out.println("--encode:"+CipherCode.multipleMD5(date));
    	System.out.println("--encode:"+CipherCode.encryptMD5(date));
    	
		String thisTime=CipherCode.encryptMD5(Constant.SYS_PREFIX+date);
		System.out.println(thisTime);
		System.out.println(CipherCode.bsEncode(thisTime));
		
		System.out.println(CipherCode.encryptMD5(Constant.SYS_PREFIX+"[2018-01-19 14:11]"));
		System.out.println(CipherCode.encryptMD5(Constant.SYS_PREFIX+"[2018-01-19 14:10]"));
		*/
		/*
		String encode=CipherUtil.getTimeEncode();
		System.out.println(encode);
		
		
		System.out.println(CipherCode.bsDecode(encode.trim()).trim().toUpperCase());
		
		Date curDate=new Date();
		String thisTime=CipherCode.multipleMD5(Constant.SYS_PREFIX+new SimpleDateFormat("[yyyy-MM-dd HH:mm]").format(curDate));
		String prevTime=CipherCode.multipleMD5(Constant.SYS_PREFIX+new SimpleDateFormat("[yyyy-MM-dd HH:mm]").format(new Date(curDate.getTime()-60000)));
		String nextTime=CipherCode.multipleMD5(Constant.SYS_PREFIX+new SimpleDateFormat("[yyyy-MM-dd HH:mm]").format(new Date(curDate.getTime()+60000)));
		

		System.out.println(thisTime);
		System.out.println(prevTime);
		System.out.println(nextTime);
		
		*/
		//System.out.println(CipherCode.bsEncode(CipherCode.multipleMD5(Constant.SYS_PREFIX+"[2018-01-19 16:26]")));
		
		//String encode=CipherUtil.getTimeEncode();
		//System.out.println(encode);
		
		//String[] ar=CipherCode.bsDecode(encode).split(":");
		//System.out.println(ar[1]);
		
		//System.out.println(CipherCode.multipleMD5(Constant.SYS_PREFIX+new SimpleDateFormat("[yyyy-MM-dd HH:mm]").format(new Date(Long.valueOf(ar[0]))),2,2));
		
		//String time=new Date().getTime()+"";
        
        //System.out.println(time);
        //System.out.println(new StringBuffer(time).reverse().toString());
        
        //System.out.println(CipherUtil.getTimeEncode());
		//System.out.println(UUID.fromString("0450ed03-9cea-11e7-bacf-005056a3c744"));
		//System.out.println(UUID.fromString("123"));
        //String pathList="D:\\app\\upload\\1.txt|D:\\app\\upload\\3.pdf|D:\\app\\upload\\2.txt|D:\\app\\upload\\5.txt";
        //String[] filePath=pathList.split("\\|");
        //for(String path:filePath){
        	//System.out.println(path);
        //}
		//String str="sf27349327sdfsfdsfsdfds,";
        //System.out.println(str.substring(0, str.length()-1));
		
		
		/*
		String[] atp = {"Rafael Nadal", "Novak Djokovic",
			"Stanislas Wawrinka",
			"David Ferrer","Roger Federer",
			"Andy Murray","Tomas Berdych",
			"Juan Martin Del Potro"};
		Arrays.sort(atp, (String s1,String s2)->(s1.length() - s2.length()));
		List<String> players=Arrays.asList(atp);
		// 使用 lambda 表达式以及函数操作(functional operation)
		//players.forEach((player) -> System.out.print(player + "; "));
		List<String> selected=new ArrayList<String>();
		players.stream().filter((String p)->(p.length()>13)).forEach((String p)->selected.add(p));
		
		players.forEach(System.out::println);
		selected.forEach((String p)->System.out.println(p));
        Integer value1 = null;
        Integer value2 = new Integer(10);
        Optional op1=Optional.ofNullable(value1);
        Optional op2=Optional.ofNullable(value2);
        //System.out.println(value1.toString());
        System.out.println(op1.toString());
        System.out.println(op1.orElse(0));
        Random random = new Random();
        random.ints(10,100).limit(10).sorted().forEach(System.out::println);
		*/
		/*
		testMap.put("code", "0000");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> innerMap = new HashMap<String,Object>();
		
		innerMap.put("name","admin");
		innerMap.put("phone","18946815719");
		innerMap.put("role","11");
		list.add(innerMap);
		
		innerMap = new HashMap<String,Object>();
		innerMap.put("name","xining");
		innerMap.put("phone","18946815701");
		innerMap.put("role","1024");
		list.add(innerMap);

		innerMap = new HashMap<String,Object>();
		innerMap.put("name","haidong");
		innerMap.put("phone","18946815333");
		innerMap.put("role","1025");
		list.add(innerMap);
		
		testMap.put("code", "0000");
		testMap.put("data", list);
		*/
		//String md5Key = CipherCode.encryptMD5("_lda@tydic#chinatelecom!qh*971");
		//System.out.println(md5Key);//7E174BEFA9F62C846F61BD441DC41E62
		//try {
			/*
			testMap.put("acctPd", "6325233202911942619414710314912319946155");
			testMap.put("acctCd", "admin");
			testMap.put("is_single_login", "0");
			
			String resultJsonStr = new ObjectMapper().writeValueAsString(testMap);
			System.out.println(resultJsonStr);
			String encodeStr = AESCipher.encryptAES(resultJsonStr, "7E174BEFA9F62C846F61BD441DC41E62");
			System.out.println(encodeStr);
			*/
			//String temStr="tx1AFAagxTUNzL6+0VsRLmB0vA3tLPfd+qp3NsFGzEsMGgoC+eqEyg/CX/JBouzv72yD0Z1iKYnaZIvLw6WyOt15S3Qlj9VWe+SXTiLO10AZU8nKLd8BRolgX5Hxe8dtzn0clGcrkmyglkPyLdeqJqC3sQTzrlee8yA6C0GALLhSsfHK6cAS8jK8/k9owoFcu5NFlLdG0v9M1Hl4Pssbb/oCrlJJ7NQfKUI4iKJ91ye8fmUgSyHi/dgx8+Mad3Q1uy/j4WSdEw20Kk5naJ283hxU70zG4FTTlHbRaPgb+pRvbl6bmw9/Ic3zon9VgJwW";
			/*	
			String temStr="ANfc7BlwDVSC8dyooQ/YvREFi+2Wpa7HG+s2z1fxAKTbHDZf3WnSpdZoK1CwR0C1KhVCdGr9Yw2hhk5manz3bikUqhy1H82n2HKl0fnuCjBVFQv8Q17UL06lOjyn65XtQhiYYFOaQpQvTr6voz0AWPB4ZaBqswuJEZgdW/su3Vo=";
			String ts="ODMyMTg0NjI4OTU1MTplYzJkNDJlMzI2MzJmMjIyOTI0ZTJkOTJiNjJhYTI1MDJmZDJlOTJlMDJjODIxNDI=";
			Map<String,String> tsMap = AccessFilter.checkTimeStamp(ts);
			System.out.println(tsMap);
			String decodeStr = CipherCode.decryptAES(temStr);
			System.out.println(decodeStr);
			*/
			//String temStr="ANfc7BlwDVSC8dyooQ%2FYvREFi%2B2Wpa7HG%2Bs2z1fxAKTbHDZf3WnSpdZoK1CwR0C1KhVCdGr9Yw2hhk5manz3bikUqhy1H82n2HKl0fnuCjBVFQv8Q17UL06lOjyn65XtQhiYYFOaQpQvTr6voz0AWPB4ZaBqswuJEZgdW%2Fsu3Vo%3D";
			String temStr="ANfc7BlwDVSC8dyooQ/YvREFi+2Wpa7HG+s2z1fxAKTbHDZf3WnSpdZoK1CwR0C1KhVCdGr9Yw2hhk5manz3bikUqhy1H82n2HKl0fnuCjBVFQv8Q17UL06lOjyn65XtQhiYYFOaQpQvTr6voz0AWPB4ZaBqswuJEZgdW/su3Vo=";

			//temStr=URLEncoder.encode(temStr,"GBK");
			//System.out.println(temStr);
			
			//String destr=URLDecoder.decode(temStr,"UTF-8");
			//System.out.println(URLDecoder.decode(temStr,"UTF-8"));

			//String decodeStr = CipherCode.decryptAES(temStr);//URLDecoder.decode(temStr,"UTF-8")
			//System.out.println(decodeStr);
		
			//System.out.println(URLDecoder.decode(null,"UTF-8"));
			//System.out.println("jslkdjfk%".contains("%"));
			
			
			
		//} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}			

			try {
				
				
				
				
				
				
				
				
				Map<String,Object> testMap = new HashMap<String,Object>();
				testMap.put("os_type", "android");
				testMap.put("is_single_login", "0");
				testMap.put("acctPd", "6325233202911942619414710314912319946155");
				testMap.put("acctCd", "18097209766");
				testMap.put("msgCode", "30047");
				
				String jsonDataStr = new ObjectMapper().writeValueAsString(testMap);
				System.out.println("jsonData加密前："+jsonDataStr);
				
				String jsonData = CipherCode.encryptAES(jsonDataStr).replaceAll(System.getProperty("line.separator"), "");
				System.out.println("jsonData加密后："+jsonData);

				System.out.println(CipherCode.decryptAES(jsonData));
				
				
				
				
				
				
				
				
				
				
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
}
