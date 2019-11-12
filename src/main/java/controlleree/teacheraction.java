package controlleree;

import model.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import until.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@RequestMapping("teacher")
public class teacheraction extends BaseController {
    @Autowired
    private SqlSession sqlSession;
    @RequestMapping("login")
    private String login (teacher teacher){
        List<teacher> teachers =sqlSession.selectList("teacher.login",teacher);
        if(teachers.size()>0){
            session.setAttribute("email", teacher.getEmail());
            return "redirect:/teacher.jsp";
        }else {
            request.setAttribute("message","cu wu de yhm mi ma ");
            return "redirect:/teacherindex.jsp";
        }
    }
    @RequestMapping("logins")
    private String logins (teacher teacher){
        List<teacher> teachers =sqlSession.selectList("teacher.logins",teacher);
        if(teachers.size()>0){
            session.setAttribute("studentid", teacher.getEmail());
            return "redirect:/teacher/queryAllstudent";
        }else {
            System.out.println("yhm cuo wu ");
            session.setAttribute("message","cu wu de yhm mi ma ");

        }return "redirect:/studentindex.jsp";
    }
    public static Document getDocument(String url){
        try {
            //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("queryscore")
    private void queryscore() {
        String subjectnum = request.getParameter("scoreyear");
        String provenice = request.getParameter("provenice");
        Document doc =getDocument("http://kaoshi.edu.sina.com.cn/college/scorelist?tab=batch&wl=&local="+provenice+"&batch=&syear="+subjectnum);
        // 获取目标HTML代码
        Elements elements1 = doc.select("[class=tbL2]");
        //今天
        Elements elements2 = elements1.select("[class=tbL2title]");
        Elements elements3 = elements1.select("[class=tbl2tbody]");
        String today = elements2.get(0).text();
        String score =  elements3.get(0).text();
        ArrayList<HashMap<String, Object>> list=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            score = elements3.get(i).text();
            String [] str=score.split("\\s+");
            HashMap<String,Object> map=new HashMap<String,Object>();
            map.put("year",str[0]);
            map.put("provice",str[1]);
            map.put("subject",str[2]);
            map.put("pici",str[3]);
            map.put("score",str[4]);
            list.add(map);
        }

        response.setContentType("text/html;charset=UTF-8");
        JSONArray re= JSONArray.fromObject(list);
        System.out.println(re);
        String resul=re.toString();
        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
//        Document doc =getDocument("http://www.tianqi.com/xining/");
//        // 获取目标HTML代码
//        Elements elements1 = doc.select("[class=left]");
//        //今天
//        Elements elements2 = elements1.select("[id=ct-main]");
//        Elements elements3 = elements2.select("[class=temp]");
//        String today = elements1.get(0).text();
        Date date = new Date();
        Random r = new Random();
        int ran1 = r.nextInt(10000);
        String id = new SimpleDateFormat("yyyyMMddHHmm").format(date)+ran1;


        System.out.println(id);

    }
    @RequestMapping("queryAllstudent")
    private String queryAllstudent (subject subject){
        session.setAttribute("subjects",sqlSession.selectList("subject.queryAll"));
        System.out.println(sqlSession.selectList("subject.queryAll"));
        Document doc =getDocument("http://www.tianqi.com/xining/");
        // 获取目标HTML代码
        Elements elements1 = doc.select("[class=left]");
        //今天
        Elements elements2 = elements1.select("[id=ct-main]");
        Elements elements3 = elements2.select("[class=temp]");
        String today = elements1.get(0).text();
        session.setAttribute("wether",today);

//        //几号
//        String number = elements3.get(0).text();
//        System.out.println("日期： "+number);
//        // 天气
//        Elements elements4 = elements1.select("[class=wea]");
//        String rain = elements4.get(0).text();
//        System.out.println("天气： "+rain);
//        // 最高温度
//        Elements elements5 = elements1.select("span");
//        String highTemperature = elements5.get(0).text()+"°C";
//        System.out.println("最高温："+highTemperature);
//        // 最低温度
//        Elements elements6 = elements1.select(".tem i");
//        String lowTemperature = elements6.get(0).text();
//        System.out.println("最低温："+lowTemperature);
//        // 风力
//        Elements elements7 = elements1.select(".win i");
//        String wind = elements7.get(2).text();
//        System.out.println("风力："+wind);
        return "redirect:/student.jsp";
    }
    @RequestMapping("queryBypage")
    private void queryBypage() {
        int id = Integer.parseInt(request.getParameter("id"));
        HashMap<String,Object> map=new HashMap<String,Object>();

         map.put("start",(id-1)*10);

         map.put("end", id*10);

        response.setContentType("text/html;charset=UTF-8");
        JSONArray re= JSONArray.fromObject(sqlSession.selectList("subject.queryBypage",map));
        System.out.println(re);
        String resul=re.toString();
        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("getSubjectcoun")
    private void getSubjectcoun() {
        String subject = request.getParameter("subject");
        String subjectnum = request.getParameter("subjectnum");
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("subject",subject);

        map.put("subjectnum", subjectnum);
        HashMap ma=new HashMap();
        ma.put("count", String.valueOf(sqlSession.selectList("subject.getSubjectcon",map).size()));
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
    @RequestMapping("getSubject")
    private void getSubject() {
       String subject = request.getParameter("subject");
        String subjectnum = request.getParameter("subjectnum");
        int id = Integer.parseInt(request.getParameter("id"));
        HashMap<String,Object> map=new HashMap<String,Object>();

        map.put("subject",subject);
        map.put("start",(id-1)*10);

        map.put("end", id*10);
        map.put("subjectnum", subjectnum);

        response.setContentType("text/html;charset=UTF-8");
        JSONArray re= JSONArray.fromObject(sqlSession.selectList("subject.getSubject",map));
        System.out.println(re);
        String resul=re.toString();
        try {
            Writer writer=response.getWriter();
            writer.write(resul);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping("getSubjectcount")
    private void getSubjectcount (){

        System.out.println(sqlSession.selectList("subject.queryAllcount").size());
        String username= String.valueOf(session.getAttribute("studentid"));
        List<GetHeadPortrait> HeadPortraits =sqlSession.selectList("xuanke.queryHeadPortrait",username);
        HashMap map=new HashMap();
        map.put("count", String.valueOf(sqlSession.selectList("subject.queryAllcount").size()));
        if(HeadPortraits.size()>0){
            map.put("url", HeadPortraits.get(0).getHeadPortrait());
        }else{
            map.put("url", "http://localhost:8081/upload/imgs/20190528\\\\1559013868085_909.png");
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
    @RequestMapping("subjectcreate")
    private String teachercreate(subject subject){
        sqlSession .insert("subject.create",subject);
        return "redirect:/teacher/queryAll";
    }
    @RequestMapping("queryAll")
    private String queryAll (subject subject){
        session.setAttribute("subjects",sqlSession.selectList("subject.queryAll"));
        System.out.println(sqlSession.selectList("teacher.queryAll"));

        return "redirect:/subject.jsp";
    }
    @RequestMapping("queryAllss")
    private String queryAllss (subjects subjects){
        session.setAttribute("subjects",sqlSession.selectList("subject.queryAllss"));
        System.out.println(sqlSession.selectList("teacher.queryAll"));

        return "redirect:/subjects.jsp";
    }
    @RequestMapping("query")
    private String query (teacher teacher){
        String studentid=(String)request.getSession().getAttribute("studentid");
        System.out.println(studentid);
        System.out.println(sqlSession.selectList("xuanke.queryAlls",studentid));
        session.setAttribute("students",sqlSession.selectList("xuanke.queryAlls",studentid));
        return "redirect:/students.jsp";
    }
    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable int id) {
        session.setAttribute("subject",sqlSession.selectOne("subject.queryById" ,id));

        return "redirect:/subjectedit.jsp";
    }
    @RequestMapping("modify")
    private String modify(subject subject){
        sqlSession.update("subject.modify",subject);

        return "redirect:/teacher/queryAll";
    }
    @RequestMapping("remove/{id}")
    private String remove(@PathVariable int id) {
        sqlSession.delete("subject.remove",id);

        return "redirect:/teacher/queryAll";
    }


    @RequestMapping("student")
    private String student(student student){
        sqlSession .insert("student.create",student);
        return "redirect:/teacher/queryAllstudents";
    }
    @RequestMapping("queryAllstudents")
    private String queryAllstudent (student student){
        session.setAttribute("students",sqlSession.selectList("student.queryAll"));
        return "redirect:/queryAllstudents.jsp";
    }
    @RequestMapping("queryByIdstudent/{id}")
    private String queryByIdstudent(@PathVariable int id) {
        session.setAttribute("student",sqlSession.selectOne("student.queryById" ,id));

        return "redirect:/studentedit.jsp";
    }
    @RequestMapping("modifystudent")
    private String modifystudent(student student){
        sqlSession.update("student.modify",student);

        return "redirect:/teacher/queryAllstudents";
    }
    @RequestMapping("removestudent/{id}")
    private String removestudent(@PathVariable int id) {
        sqlSession.delete("student.remove",id);

        return "redirect:/teacher/queryAllstudents";
    }
    @RequestMapping(value="ajaxUpload.do", produces = "application/text; charset=utf-8")
    public void ajaxUploadExcel(@RequestParam("upfile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ExcelUplod bbb=new ExcelUplod();

        String msg = bbb.ajaxUploadExcel(request, response);
        response.setContentType("text/html;charset=UTF-8");//这些设置必须要放在getWriter的方法之前，
        response.getWriter().print(msg);
    }

    @RequestMapping(value="excelUplod", produces = "application/text; charset=utf-8")
    private void excelUplod (HttpServletRequest request, HttpServletResponse response){
        ExcelUplod bbb=new ExcelUplod();
        HashMap map=new HashMap();
       try {
            bbb.ajaxUploadExcel(request, response);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
        }


    }

    @RequestMapping("export")
    private void export (){
        String reportHead = "";
        String reportField = "";
        String fileName = "";
        List<Map> inforList=sqlSession.selectList("subject.exportAll");
        System.out.println(inforList);
        List<Map> inforLis= JSONArray.fromObject(inforList);
        if(inforLis!=null&&inforLis.size()>0){
            reportHead = (String) inforLis.get(0).get("reporthead");
            reportField = (String) inforLis.get(0).get("reportfield");
            fileName = (String) inforLis.get(0).get("filename");
        }
        String[] heads = reportHead.split(",");
        String[] fields = reportField.split(",");

        // 创建新的Excel工作簿
        SXSSFWorkbook workbook = null;
         String excelName =fileName;
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            excelName = new String(excelName.getBytes("GBK"), "ISO8859_1");
            response.setHeader("Content-Disposition", "attachment;filename=" + excelName + ".xlsx");
            Excelexport cc=new Excelexport();
            workbook =cc.export_Flight(inforLis,heads,fields);
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        FileOutputStream fileOutputStream = null;
//        try {
//            fileOutputStream = new FileOutputStream("D:\\aaaa.xls");
//            try {
//                workbook.write(fileOutputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                fileOutputStream.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                fileOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
 }
    /**
     * 发送邮件
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/sendEmail",produces="application/json;charset=UTF-8")
    public void sendEmail(HttpServletRequest request)throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        String msg = "ok";   //发送状态
        String toEMAIL = request.getParameter("EMAIL");         //对方邮箱
        String TITLE = request.getParameter("TITLE");          //标题
        String CONTENT = request.getParameter("CONTENT");        //内容
        JavaEmailSende  sendd=new JavaEmailSende();
        try {
            sendd.sendEmail(request, response,toEMAIL, TITLE, CONTENT);
        } catch (IOException e) {
            msg="发送失败！";
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        map.put("result", msg);
        JSONObject re=JSONObject.fromObject(map);
        String resul=re.toString();

       Writer writer=response.getWriter();
        writer.write(resul);
    }

    @RequestMapping("getHeadPortrait")
    private void getHeadPortrait() {
        String username= String.valueOf(session.getAttribute("studentid"));
       List<GetHeadPortrait> HeadPortraits =sqlSession.selectList("xuanke.queryHeadPortrait",username);
        HashMap map=new HashMap();
       if (HeadPortraits.size()>0){
           String url =HeadPortraits.get(0).getHeadPortrait();
           map.put("url", url);
           map.put("code", 0);
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
    @RequestMapping("ajaxlogin")
    private void ajaxlogin() {
        String email =Common.strVal(request.getParameter("cardId"));
        String password=Common.strVal(request.getParameter("password"));
        String loginemail="",loginPassword="";
        loginemail= CipherUtil.decryptRSA(email,CipherUtil.RSA_PRIVATE_KEY);
        loginPassword= CipherUtil.decryptRSA(password,CipherUtil.RSA_PRIVATE_KEY);
        String vCd=request.getParameter("vCd");
        System.out.println(vCd);
        System.out.println("解密后的用户名："+loginemail+"解密后的密码："+loginPassword);
       teacher teacher=new teacher(loginemail,loginPassword);
        session.setAttribute("studentid", loginemail);
        List<teacher> teachers =sqlSession.selectList("teacher.logins",teacher);
        String message=null;
        Map<String,String> map = new HashMap<String,String>();
//        JSONObject jsonObject=new JSONObject(teacher);
        String verificationCode= Common.strVal(session.getAttribute(Common.LOGIN_VERIFICATION_CODE));
        if(verificationCode.length()>0){
            String vCode=Common.strVal(vCd);
            if(verificationCode.toLowerCase().equals(vCode.toLowerCase())){
                if(teachers.size()>0){
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        Writer writer=response.getWriter();
                        writer.write("true");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        Writer writer=response.getWriter();
                        writer.write("false3");
                        //用户名或密码错误
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                response.setContentType("text/html;charset=UTF-8");
                try {
                    Writer writer=response.getWriter();
                    writer.write("false2");
                    //验证码错误！
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            response.setContentType("text/html;charset=UTF-8");
            try {
                Writer writer=response.getWriter();
                writer.write("false1");
                //会话已过期！
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping("getValidCode")
    private void getValidCode() {
        System.out.println("yzmaaaaaaaaa");
        HashMap result=new HashMap(){{ put("code","1001");}};
        String[] codeInfo= VerificationCode.getCode();
        if(codeInfo!=null){
            result.put("code","0000");
            result.put("message","验证码获取成功！");
            System.out.println("验证码获取成功！"+codeInfo[1]);
            HashMap data=new HashMap();
            data.put("code",codeInfo[1]);
            String codee=codeInfo[1];
            data.put("base",session.getAttribute(Common.BASE_ACCESS_PATH));

            result.put("codee",codee);

            response.setContentType("text/html;charset=UTF-8");
            JSONObject re=JSONObject.fromObject(result);
             String resul=re.toString();
            try {
                Writer writer=response.getWriter();
                writer.write(resul);


            } catch (IOException e) {
                e.printStackTrace();
            }
            session.setAttribute(Common.LOGIN_VERIFICATION_CODE,codeInfo[0]);
        }else{
            result.put("message","验证码获取失败！");
            response.setContentType("text/html;charset=UTF-8");

            Writer writer= null;
            try {
                writer = response.getWriter();
                writer.write("flas");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("验证码获取失败！");
        }

    }
    @RequestMapping("vCode")
    public Object verificationCode(HttpSession session){
        session.removeAttribute(Common.LOGIN_INFO);
        HashMap result=new HashMap(){{ put("code","1001");}};
        String[] codeInfo= VerificationCode.getCode();
        if(codeInfo!=null){
            result.put("code","0000");
            result.put("message","验证码获取成功！");
            HashMap data=new HashMap();
            data.put("code",codeInfo[1]);
            data.put("base",session.getAttribute(Common.BASE_ACCESS_PATH));

            result.put("data",data);
            session.setAttribute(Common.LOGIN_VERIFICATION_CODE,codeInfo[0]);
        }else{
            result.put("message","验证码获取失败！");
        }
        return result;
    }

    @RequestMapping("decode")

    public Object findList(@RequestParam Map<String, Object> params) throws Exception{
        //执行查询
        List resultList = null;
        params.putAll(tools.Common.decodeParams(params));
        String sqlName = tools.Common.getStrValue(params.get("sqlName"));

        System.out.println(params);
        return resultList;
    }


}
