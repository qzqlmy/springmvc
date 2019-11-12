package tools;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @name ParamsTool.java
 * @author hechuan
 * @version 1.1.0
 * @description 参数工具
 * @email hechuan@tydic.com
 * @date Created On : 2017-10-17
 */

public class Common{
	//object中获取字符串
	public static String getStrValue(Object o){
		if(o!=null){
			return o.toString().trim();
		}
		return "";
	}
	//获取当前时间
	public static String getDate(){
		return getDate("yyyyMMddHHmmss");
	}
	//获取当前时间
	public static String getDate(String formatter){
		return new SimpleDateFormat(formatter).format(new Date());
	}
	//获取当前时间
	public static String getMilliDate(){
		return new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date());
	}
	//随机数
	public static int getRandom(int base,int scope){
		int random=base+(int)(Math.random()*scope);
		return random;
	}
	//获取随机文件后缀
	public static String getSuffix(){
		return getMilliDate()+getRandom(1000,1000);
	}

	//处理兼容以前的调用方式参数
	public static Map<String,Object>  getParams(Map<String,Object> params){
		Map<String,Object> newParams=new HashMap<String,Object>();
		
		for(String key : params.keySet()){
			String thisKey=key;
			int index=key.indexOf(".");
			if(index>-1){
				thisKey=key.substring(index);
			}
			newParams.put(thisKey, params.get(key));
		}  
		
		return newParams;
	}

	//-----------------------------返回值封装-------------------------
	
	//封装返回值

	//封装返回值
	public static Map<String,Object> result(String code,String message){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("code", code);
		result.put("message", message);
		result.put("callTime", getTime());
		return result;
	}
	//封装返回值
	public static Map<String,Object> success(String code,Object data){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("code", code);

		result.put("data", data);
		result.put("callTime", getTime());
		return result;
	}
	//封装返回值
	public static Map<String,Object> success(String code,String message,Object data){
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("code", code);
		result.put("message", message);
		result.put("data", data);
		result.put("callTime", getTime());
		return result;
	}
	//获取当前时间字符串
	public static String getTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	//获取请求参数中的Map
	public static Map<String, Object> getPackageMap(Map<String, Object> params){
		Map<String, Object> result=new HashMap<String,Object>();
		for(String key:params.keySet()){
			int index=key.indexOf(".");
			if(index>-1){
				result.put(key.substring(index+1), params.get(key));
			}
		}
		return result;
	}
	
	//参数字符串
	public static String paramsToString(Map<String, Object> params) {
        StringBuilder paramsStr=new StringBuilder();
        if(params != null&&params.size()>0){
        	boolean frist=true;
        	for(String key:params.keySet()){
        		if(frist){
                    frist=false;
        		}else{
                    paramsStr.append(";");
        		}
                paramsStr.append(key);
                paramsStr.append("=");
                paramsStr.append(params.get(key));
        	}
        }
        return paramsStr.toString();
    }
    //获取请求的IP地址
	public static String getIpAddr(HttpServletRequest request){
        String ip=request.getHeader("X-Real-IP");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("X-Forwarded-For");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip=request.getRemoteAddr();
        }
        
        System.out.println("===ip:"+ip+"===");

        return ip;
    }

	//参数解密
	public static Map decodeParams(Map<String, Object> params){
		Map jsonMap = new HashMap();
		//解密参数
		try{
			String jsonData = Common.getStrValue(params.get("jsonData"));
			System.out.println("decodeParams---jsonData:"+jsonData);
			if(jsonData.length()>0){
				if(jsonData.contains("%")) jsonData = URLDecoder.decode(jsonData,"UTF-8");
				String decodeStr = CipherCode.decryptAES(jsonData);
				System.out.println("decodeParams---decodeStr:"+decodeStr);
				if(decodeStr.length()>0&&decodeStr.contains("{")){
					jsonMap = new ObjectMapper().readValue(decodeStr, Map.class);
				}
			}
		} catch (Exception e) {
			System.out.println("---------------------------参数解密失败！---------------------------");
			e.printStackTrace();
		}
		
		return jsonMap;
	}
	//加密返回结果
	public static String encodeResult(Object result){
		String encodeResult = null;
		try {
			String jsonResult = new ObjectMapper().writeValueAsString(result);
			encodeResult = CipherCode.encryptAES(jsonResult).replaceAll("\\n", "");
		} catch (JsonProcessingException e) {
			System.out.println("---------------------------加密返回结果失败！---------------------------");
			e.printStackTrace();
		}
		return encodeResult;
	}
}
