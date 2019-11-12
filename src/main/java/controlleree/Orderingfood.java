package controlleree;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import until.CipherCode;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;

@Component

@RequestMapping("Ordering")
public class Orderingfood extends BaseController {
    @Autowired
    private SqlSession sqlSession;
    @RequestMapping("querycount")
    private void querycount(){
        String foodname = request.getParameter("foodname");
        String price = request.getParameter("price");
        String test = request.getParameter("test");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",test);
        map.put("foodname", foodname);
        map.put("price", price);

        JSONArray rea=JSONArray.fromObject(sqlSession.selectList("subject.querylistfood",map));
        int cc=rea.size();
                System.out.println(cc);
//        HashMap ma=new HashMap();

        map.put("count", cc);

        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(map);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @RequestMapping("foodcreate")
    private void foodcreate(){
        String foodmodel= request.getParameter("leixin");
        String foodname = request.getParameter("foodname");
        String src = request.getParameter("src");
        String price = request.getParameter("price");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map.put("foodurl",src);
        map.put("foodname", foodname);
        map.put("price", price);
       int cc= sqlSession .insert("subject.createfood",map);
        System.out.println("______________"+foodname);
//        HashMap ma=new HashMap();
        HashMap<String,Object> ma=new HashMap<String,Object>();
        if (cc==1){
            ma.put("code", 0);
        }else{
            ma.put("code", 1);
        }

        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(ma);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @RequestMapping("editor/{foodname}")
    private String foodlistone(@PathVariable String foodname){
        HashMap ma=new HashMap();
        HashMap<String,Object> mall=sqlSession.selectOne("subject.foodlistone" ,foodname);
        System.out.println(mall);
        response.setContentType("text/html;charset=UTF-8");

        session.setAttribute("foodlist",mall);
        return "redirect:/goodsManage/goodsmodify.jsp";
    }
    @RequestMapping("queryfoodlist")
    private void queryfoodlist () {
        String foodmodel= request.getParameter("foodmodel");
        String foodname = request.getParameter("foodname");
        String price = request.getParameter("price");
        int pagecruent =Integer.parseInt( request.getParameter("pagecruent"));
        int pagenum=5;
        int start=(pagecruent-1)*pagenum;
        System.out.println(pagenum);
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map.put("foodname", foodname);
        map.put("start", start);
        map.put("end", pagenum);
        map.put("price", price);
        if(start==0){
            map.put("index", 1);
        }
        List<Map> inforList = sqlSession.selectList("subject.querylistfood",map);
        System.out.println(inforList);
        response.setContentType("text/html;charset=UTF-8");
        JSONArray re=JSONArray.fromObject(inforList);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        session.setAttribute("foodlist",inforLis);
    }
    @RequestMapping("queryfoodlistq")
    private String queryfoodlistq () {
        HashMap<String,Object> map=new HashMap<String,Object>();
        List<Map> inforList = sqlSession.selectList("subject.querylistfood");
        System.out.println(inforList);
        List<Map> inforLis = JSONArray.fromObject(inforList);
        session.setAttribute("foodlist",inforLis);
        return "redirect:/layui/three.html";
       // aaaaaaaaaaaaa
    }
    @RequestMapping("foodupdate")
    private void foodupdate () {
        String foodmodel= request.getParameter("leixin");
        String foodname = request.getParameter("foodname");
        String src = request.getParameter("src");
        String price = request.getParameter("price");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map.put("foodurl",src);
        map.put("foodname", foodname);
        map.put("price", price);
//
       sqlSession.update("subject.modifyfood",map);
        System.out.println(sqlSession.update("subject.modifyfood",map));
        HashMap<String,Object> ma=new HashMap<String,Object>();



        response.setContentType("text/html;charset=UTF-8");
        if(sqlSession.update("subject.modifyfood",map)==1){
            ma.put("code",0);
        }else{
            ma.put("code",1);
        }

        JSONObject re=JSONObject.fromObject(ma);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @RequestMapping("createording")
    private void createording () {
        String obj  = request.getParameter("data").replace('{',' ').replace('}',' ').trim();
        String price = request.getParameter("price");
        String[] aa=obj.split(",");
        ArrayList<HashMap<String, Object>> list=new ArrayList<>();
        HashMap<String,Object> ma=new HashMap<String,Object>();
        Date date = new Date();
        Random r = new Random();
        int ran1 = r.nextInt(10000);
        String ordingid = new SimpleDateFormat("yyyyMMddHHmm").format(date)+ran1;
        session.removeAttribute("ordinginfo");
        session.removeAttribute("ordinglist");
        System.out.println(session.getAttribute("ordinginfo"));
        System.out.println(session.getAttribute("ordinglist"));
        for (int i = 0; i < aa.length; i++) {
            String s = aa[i].replace('"',' ').replace('-',':').trim();
            String[] f=s.split(":");
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(i<aa.length-1) {
                for (int j = 0; j < f.length; j++) {
                    map.put("foodname", f[0]);
                    map.put("foodprice", f[1]);
                    map.put("foodcount", f[2]);
                    map.put("ordingid", ordingid);
                    map.put("listdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                }
                list.add(map);
            }
            else {
               ma.put("price",f[0]);
                ma.put("ordingid",ordingid);

            }

        }

        session.setAttribute("ordinginfo",ma);
        session.setAttribute("ordinglist",list);
            HashMap map=new HashMap();
        if (session.getAttribute("ordinginfo")!=null && session.getAttribute("ordinglist")!=null){
            map.put("code", 0);
            sqlSession .insert("subject.ordingcreate",list);
            System.out.println(1111);
        }else{
            map.put("code", 1);
        }


        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(map);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    //获取数组同一个元素出现的次数
    private int getArrcount(String[] arr, String a) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.get(a) != null) {
                map.put(a, map.get(a) + 1);
            } else {
                map.put(a, 1);
            }

        }
//得到map中所有的键
        Set<String> keyset = map.keySet();
//创建set集合的迭代器
        Iterator<String> it = keyset.iterator();

        if (it.hasNext()) {
            String key = it.next();
            Integer value = map.get(key);
            System.out.print(key + "共有" + value + "次    ");
            return value;
        }else{
            return 0;
        }

    }
    @RequestMapping("getrenxiao")
    private String getrenxiao () {
        /*
        热销菜查询

         */
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",1);
        List<Map> inforList = sqlSession.selectList("subject.queryrxfood",map);
        System.out.println(inforList);
        List<Map> inforLis = JSONArray.fromObject(inforList);
        session.setAttribute("subjects",inforLis);
       /*
      荤菜查询
       */
        HashMap<String,Object> map1=new HashMap<String,Object>();
        map1.put("foodmodel",2);
        List<Map> inforList1 = sqlSession.selectList("subject.queryhcfood",map1);
        System.out.println(inforList);
        List<Map> inforLis1 = JSONArray.fromObject(inforList1);
        session.setAttribute("subjects1",inforLis1);
        //素菜查询
        HashMap<String,Object> map2=new HashMap<String,Object>();
        map2.put("foodmodel",3);
        List<Map> inforList2 = sqlSession.selectList("subject.queryscfood",map2);
        List<Map> inforLis2 = JSONArray.fromObject(inforList2);
        session.setAttribute("subjects2",inforLis2);

        //凉菜查询
        HashMap<String,Object> map3=new HashMap<String,Object>();
        map3.put("foodmodel",4);
        List<Map> inforList3 = sqlSession.selectList("subject.querylcfood",map3);
        List<Map> inforLis3 = JSONArray.fromObject(inforList3);
        session.setAttribute("subjects3",inforLis3);

        //今日促销
        HashMap<String,Object> map4=new HashMap<String,Object>();
        map4.put("foodmodel",5);
        List<Map> inforList4 = sqlSession.selectList("subject.querycxfood",map4);
        List<Map> inforLis4 = JSONArray.fromObject(inforList4);
        session.setAttribute("subjects4",inforLis4);

        return "redirect:/Ordering/index.jsp";
    }
    @RequestMapping("remove")
    private void removea() {
        String foodname = request.getParameter("foodname");
        System.out.println(foodname);
        sqlSession.delete("subject.removefood",foodname);
        HashMap<String,Object> map=new HashMap<String,Object>();
        List<Map> inforList = sqlSession.selectList("subject.querylistfood");
        System.out.println(inforList);
        List<Map> inforLis = JSONArray.fromObject(inforList);
        session.setAttribute("foodlist",inforLis);
        response.setContentType("text/html;charset=UTF-8");
        HashMap<String,Object> ma=new HashMap<String,Object>();
        ma.put("code",0);
        JSONObject re=JSONObject.fromObject(ma);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @RequestMapping("remove/{foodname}")
    private String remove(@PathVariable String foodname) {
        System.out.println(foodname);
        sqlSession.delete("subject.removefood",foodname);
        HashMap<String,Object> map=new HashMap<String,Object>();
        List<Map> inforList = sqlSession.selectList("subject.querylistfood");
        System.out.println(inforList);
        List<Map> inforLis = JSONArray.fromObject(inforList);
        session.setAttribute("foodlist",inforLis);
        return "redirect:/layui/three.html";
    }
    @RequestMapping("ordinginfo")
    private void ordinginfo () {

       Map aa= sqlSession.selectOne("subject.selording");
       Map cc= sqlSession.selectOne("subject.selording2");

        HashMap bb=new HashMap();
       bb.put("ordingcount",aa.get("ordingcount"));
       bb.put("ordingprice",aa.get("ordingprice"));
       bb.put("ordingcount2",cc.get("ordingcount"));
       bb.put("ordingprice2",cc.get("ordingprice"));
        System.out.println(bb);
        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(bb);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private  void removeJceLimit()
    {
        //去除JCE加密限制，只限于Java1.8
        try {
            Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, false);



        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace(System.err);
        }
    }
    @RequestMapping("querylistcount")
    private void querylistcount(@RequestParam Map<String, Object> params) {
        System.out.println(params);
        String encrypt = (String) params.get("encrypt");
        //String encrypt = request.getParameter("encrypt");
        Map jsonMap=new HashMap<String,Object>();
        removeJceLimit();
        String decodeStr = CipherCode.decryptAES(encrypt);
        try {
            jsonMap = new ObjectMapper().readValue(decodeStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String staff_id= (String) jsonMap.get("staff_id");
        System.out.println("解密后："+jsonMap);
//        String foodname = request.getParameter("foodname");
//        String ordingid = request.getParameter("ording");
//        String starttime = request.getParameter("starttime");
//        String endttime = request.getParameter("endttime");

//        map.put("ordingid",ordingid);
//        map.put("foodname", foodname);
//        map.put("starttime", starttime);
//        map.put("endttime", endttime);

        JSONArray rea=JSONArray.fromObject(sqlSession.selectList("subject.querylistcount",jsonMap));
        int cc=rea.size();

//        HashMap ma=new HashMap();

        jsonMap.put("count", cc);

        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(jsonMap);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("queryfoodlista")
    private void queryfoodlista () {
        String foodmodel= request.getParameter("key[foodmodel]");
        String foodname = request.getParameter("key[foodname]");
        String price = request.getParameter("key[price]");
        System.out.println(foodname);
       String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        int cc=(Integer.parseInt( request.getParameter("limit")));
        int pagecruent =(Integer.parseInt( request.getParameter("page"))-1)*cc;
        String aa= String.valueOf(pagecruent);
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map.put("foodname", foodname);
        map.put("start", aa);
        map.put("end", limit);
        map.put("price", price);
        HashMap<String,Object> map2=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map2.put("foodname", foodname);
        map2.put("price", price);
        List<Map> inforList = sqlSession.selectList("subject.querylistfood",map);
        List<Map> inforLis= sqlSession.selectList("subject.querylistfood",map2);
        HashMap<String,Object> ma=new HashMap<String,Object>();
        ma.put("code",0);
        ma.put("count",inforLis.size());
        ma.put("msg","");
        System.out.println(inforList);
        response.setContentType("text/html;charset=UTF-8");
        JSONArray rea=JSONArray.fromObject(inforList);
        ma.put("data",rea);
        JSONObject re=JSONObject.fromObject(ma);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        session.setAttribute("foodlist",inforLis);
    }
    @RequestMapping("queryordinglista")
    private void queryordinglista () {
        String foodname = request.getParameter("key[foodname]");
        String ordingid = request.getParameter("key[ordingid]");
        String starttime = request.getParameter("key[starttime]");
        String endttime = request.getParameter("key[endttime]");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        int cc=(Integer.parseInt( request.getParameter("limit")));
        int pagecruent =(Integer.parseInt( request.getParameter("page"))-1)*cc;

        String aa= String.valueOf(pagecruent);
        System.out.println(pagecruent);
        HashMap<String,Object> map=new HashMap<String,Object>();
        HashMap<String,Object> map2=new HashMap<String,Object>();
        map.put("ordingid",ordingid);
        map.put("foodname", foodname);
        map.put("start", aa);
        map.put("end", limit);
        map.put("starttime", starttime);
        map.put("endttime", endttime);


        map2.put("ordingid",ordingid);
        map2.put("foodname", foodname);

        map2.put("starttime", starttime);
        map2.put("endttime", endttime);

        List<Map> inforList = sqlSession.selectList("subject.querylistcount",map);
        List<Map> inforList2 = sqlSession.selectList("subject.querylistcount",map2);
        HashMap<String,Object> ma=new HashMap<String,Object>();
        ma.put("code",0);
        ma.put("count",inforList2.size());
        ma.put("msg","");

        response.setContentType("text/html;charset=UTF-8");
        JSONArray re=JSONArray.fromObject(inforList);
        ma.put("data",re);
        JSONObject rea=JSONObject.fromObject(ma);
        System.out.println(re);
        System.out.println(rea);
        String resul=rea.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        session.setAttribute("foodlist",inforLis);
    }
    @RequestMapping("queryordinglist")
    private void queryordinglist () {
        String foodname = request.getParameter("foodname");
        String ordingid = request.getParameter("ordingid");
        String starttime = request.getParameter("starttime");
        String endttime = request.getParameter("endttime");
        int pagecruent =Integer.parseInt( request.getParameter("pagecruent"));
        int pagenum=5;
        int start=(pagecruent-1)*pagenum;
        System.out.println(pagenum);
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("ordingid",ordingid);
        map.put("foodname", foodname);
        map.put("start", start);
        map.put("end", pagenum);
        map.put("starttime", starttime);
        map.put("endttime", endttime);

        if(start==0){
            map.put("index", 1);
        }
        List<Map> inforList = sqlSession.selectList("subject.querylistcount",map);
        System.out.println(inforList);
        response.setContentType("text/html;charset=UTF-8");
        JSONArray re=JSONArray.fromObject(inforList);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        session.setAttribute("foodlist",inforLis);
    }
    @RequestMapping("querylistxl")
    private void querylistxl () {
        List<Map> inforList = sqlSession.selectList("subject.querylistxl");
        System.out.println(inforList);
        response.setContentType("text/html;charset=UTF-8");
        JSONArray re=JSONArray.fromObject(inforList);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("weeklist")
    private void weeklist () {
        List<Map> inforList = sqlSession.selectList("subject.queryweeklist");
       if(inforList.size()==0){
           sqlSession.update("subject.getUserCount");
            System.out.println(inforList.size());
        }
        List<Map> inforLis = sqlSession.selectList("subject.weeklist1");
        response.setContentType("text/html;charset=UTF-8");
        JSONArray re=JSONArray.fromObject(inforLis);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("querylistxlall")
    private void querylistxlall () {
        List<Map> inforList = sqlSession.selectList("subject.querylistxl");
        Map aa= sqlSession.selectOne("subject.selording");
        Map cc= sqlSession.selectOne("subject.selording2");

        HashMap<String,Object> bb=new HashMap<String,Object>();
        bb.put("ordingcount",aa.get("ordingcount"));
        bb.put("ordingprice",aa.get("ordingprice"));
        bb.put("ordingcount2",cc.get("ordingcount"));
        bb.put("ordingprice2",cc.get("ordingprice"));

        response.setContentType("text/html;charset=UTF-8");
        JSONArray re=JSONArray.fromObject(inforList);
        bb.put("data",re);
        JSONObject rea=JSONObject.fromObject(bb);
        String resul=rea.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("querymenu3")
    private void querymenu3 () {
        HashMap<String,Object> bb=new HashMap<String,Object>();
        bb.put("code",0);
        bb.put("msg","");
        List<Map> inforList = sqlSession.selectList("subject.queryfristmenu");
        List<Map> data =new ArrayList<>();
        for (int i = 0; i < inforList.size(); i++) {

            String name= String.valueOf(inforList.get(i).get("menu"));
            String title= String.valueOf(inforList.get(i).get("tile"));
            String icon= String.valueOf(inforList.get(i).get("icon"));
            if(name.equals("主页")){
                HashMap<String,Object> a=new HashMap<String,Object>();
                HashMap<String,Object> menu=new HashMap<String,Object>();
                menu.put("menu",name);
                List<Map> list = sqlSession.selectList("subject.querysecondmenu",menu);
                JSONArray lis=JSONArray.fromObject(list);
                a.put("title",title);
                a.put("icon",icon);
                a.put("list",lis);
                data.add(a);

            }else{
                HashMap<String,Object> a=new HashMap<String,Object>();
                HashMap<String,Object> menu=new HashMap<String,Object>();
                menu.put("menu",title);
                List<Map> list = sqlSession.selectList("subject.querysecondmenu",menu);
                JSONArray lis=JSONArray.fromObject(list);
                a.put("name",name);
                a.put("title",title);
                a.put("icon",icon);
                a.put("list",lis);
                data.add(a);
            }

        }

        JSONArray li=JSONArray.fromObject(data);
        bb.put("data",li);
        JSONObject rea=JSONObject.fromObject(bb);
        String resul=rea.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("querymenu2")
    private void querymenu2 () {
        HashMap<String, Object> bb = new HashMap<String, Object>();
        List<Map> inforList = sqlSession.selectList("subject.queryfristmenu");
        List<Map> data = new ArrayList<>();
        for (int i = 0; i < inforList.size(); i++) {
            String id = String.valueOf(inforList.get(i).get("id"));
            String name = String.valueOf(inforList.get(i).get("menu"));
            String title = String.valueOf(inforList.get(i).get("tile"));
            String icon = String.valueOf(inforList.get(i).get("icon"));

            HashMap<String, Object> a = new HashMap<String, Object>();
            HashMap<String, Object> menu = new HashMap<String, Object>();
            menu.put("menu", title);
            List<Map> list = sqlSession.selectList("subject.querysecondmenu", menu);
            List<Map> children = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                HashMap<String, Object> b = new HashMap<String, Object>();
                b.put("name",String.valueOf(list.get(j).get("title")));
                b.put("id",String.valueOf(i+1)+String.valueOf(j+1));
                children.add(b);
            }
            JSONArray lis = JSONArray.fromObject(children);
            a.put("id",i);
            a.put("name", title);

            a.put("children", lis);
            data.add(a);


        }
        JSONArray li=JSONArray.fromObject(data);
        bb.put("data",li);
        JSONObject rea=JSONObject.fromObject(bb);
        String resul=rea.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("addmenu1")
    private void addmenu1 () {
        String tile = request.getParameter("tile");
        String menu = request.getParameter("menu");
        String icon = request.getParameter("icon");
        String model = request.getParameter("model");
        String fullname2 = request.getParameter("fullname2");
        String jump = request.getParameter("jump");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("tile",tile);
        map.put("menu",menu);
        map.put("icon", icon);
        map.put("fullname2", fullname2);
        map.put("jump", jump);
        int cc;
        if(model.equals("1")){
            cc= sqlSession .insert("subject.createfristmenu",map);
        }else{
            cc= sqlSession .insert("subject.createsecondmenu",map);
        }
       HashMap<String,Object> ma=new HashMap<String,Object>();
        if (cc==1){
            ma.put("code", 0);
        }else{
            ma.put("code", 1);
        }

        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(ma);
        String resul=re.toString();

        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("queryonemenu")
    private void queryonemenu () {
        HashMap<String, Object> bb = new HashMap<String, Object>();
        bb.put("code",0);
        String tile = request.getParameter("tile");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("tile",tile);
        List<Map> list = sqlSession.selectList("subject.queryfristmenu", map);

            bb.put("code",0);
            JSONArray lis = JSONArray.fromObject(list);
            bb.put("data",lis);

        response.setContentType("text/html;charset=UTF-8");

        String resul=lis.toString();


        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("queryonemenu1")
    private void queryonemenu1 () {
        String tile = request.getParameter("tile");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("tile",tile);
        Map aa= sqlSession.selectOne("subject.querymenu",map);
        response.setContentType("text/html;charset=UTF-8");
        JSONObject re=JSONObject.fromObject(aa);
        String resul=re.toString();


        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("removemenu")
    private void removemenu () {
        String title = request.getParameter("title");
        String model = request.getParameter("model");
        HashMap<String,Object> map=new HashMap<String,Object>();
        if(model.equals("3")){
            int aa=sqlSession.delete("subject.removemenu2",title);
            System.out.println("shanchu "+aa);
        }else{
            sqlSession.delete("subject.removemenu1",title);
        }

    }
}
