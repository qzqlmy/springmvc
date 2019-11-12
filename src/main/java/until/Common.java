package until;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 名称: Common.java
 * 描述: 常用工具类
 * 作者: Lyndon
 * 版本: 1.0.O
 * 日期: 创建于2019年03月06日
 */
@Slf4j
public class Common {
    //登录信息
    public static final String LOGIN_INFO="__login_info";
    public static final String LOGIN_VERIFICATION_CODE="___login__verification_code";
    public static final String BASE_ACCESS_PATH="___base__access_path";
    //json转换
    private static final ObjectMapper om=new ObjectMapper();

    public static String strVal(Object obj){
        if(obj!=null) return obj.toString().trim();
        return "";
    }
    public static String toJson(Object obj){
        String json=null;
        try {
            json=om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            json=null;
            e.printStackTrace();
        }
        return json;
    }
    public static HashMap json2Map(String json) {
        HashMap jsonMap=null;
        try {
            jsonMap=om.readValue(json, HashMap.class);
        } catch (IOException e) {
            jsonMap=null;
            e.printStackTrace();
        }
        return jsonMap;
    }
    public static ArrayList json2List(String json){
        ArrayList jsonList=null;
        try {
            jsonList=om.readValue(json, ArrayList.class);
        } catch (IOException e) {
            jsonList=null;
            e.printStackTrace();
        }
        return jsonList;
    }
}
