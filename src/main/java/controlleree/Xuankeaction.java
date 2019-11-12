package controlleree;

import model.subject;
import model.teacher;
import model.xuanke;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
@RequestMapping("xuanke")
public class Xuankeaction extends BaseController {
    @Autowired
    private SqlSession sqlSession;
    @RequestMapping("xuankecreate")
    private String teachercreate(xuanke xuanke){
        sqlSession .insert("xuanke.create",xuanke);
        return "redirect:/xuanke/queryAll";
    }
    @RequestMapping("queryAll")
    private String queryAll (xuanke xuanke){
        session.setAttribute("xuankes",sqlSession.selectList("xuanke.queryAll"));
        System.out.println(sqlSession.selectList("teacher.queryAll"));

        return "redirect:/student.jsp";
    }
    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable int id) {
        session.setAttribute("xuanke",sqlSession.selectOne("xuanke.queryById" ,id));

        return "redirect:/subjectedit.jsp";
    }
    @RequestMapping("modify")
    private String modify(xuanke xuanke){
        sqlSession.update("subject.modify",xuanke);

        return "redirect:/xuanke/queryAll";
    }
    @RequestMapping("remove/{id}")
    private String remove(@PathVariable int id) {
        sqlSession.delete("xuanke.remove",id);

        return "redirect:/xuanke/queryAll";
    }
    @RequestMapping("subjectid")
    private void subjectid(xuanke xuanke){
        System.out.println("222222222222");
        String subject=request.getParameter("subject");
        String studentid=request.getParameter("studentid");
        String name=request.getParameter("name");
        System.out.println(subject+"   "+studentid+"  "+name);
        xuanke xuanke1= new xuanke(studentid,name,subject);
        System.out.println(xuanke);
        List<teacher> subjects =sqlSession.selectList("xuanke.queryBystudentid" ,xuanke1);
        System.out.println(subjects);
        if(subjects.size()>0){
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("22222222");
            try {
                Writer writer=response.getWriter();
                writer.write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("11111111111111");
            try {
                Writer writer=response.getWriter();
                writer.write("true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
