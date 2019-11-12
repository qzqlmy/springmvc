package controlleree;

import model.Book;
import model.admin;
import model.teacher;
import model.user;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("admin")
public class adminaction extends BaseController {

    @Autowired
    private SqlSession sqlSession;
    @RequestMapping("create")
    private String create(admin admin){
        System.out.println(admin);
        sqlSession.insert("admin.create",admin);
        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

        return "redirect:/adminlogin.jsp";
    }
    @RequestMapping("login")
    private String login (admin admin){
        List<admin> admins =sqlSession.selectList("admin.login",admin);
        if(admins.size()>0){
            session.setAttribute("email", admin.getEmail());
            return "redirect:/admin/queryAll";
        }else {
            request.setAttribute("message","cu wu de yhm mi ma ");
            return "redirect:/adminindex.jsp";
        }
    }
    @RequestMapping("teachercreate")
    private String teachercreate(teacher teacher){
   sqlSession .insert("teacher.create",teacher);

        return "redirect:/admin/queryAll";
    }
    @RequestMapping("queryAll")
    private String queryAll (teacher teacher){
    session.setAttribute("teachers",sqlSession.selectList("teacher.queryAll"));
        System.out.println(sqlSession.selectList("teacher.queryAll"));

        return "redirect:/adminhome.jsp";
    }
    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable int id) {
      session.setAttribute("teacher",sqlSession.selectOne("teacher.queryById" ,id));

        return "redirect:/teacheredit.jsp";
    }
    @RequestMapping("modify")
    private String modify(teacher teacher){
   sqlSession.update("teacher.modify",teacher);

        return "redirect:/admin/queryAll";
    }
    @RequestMapping("remove/{id}")
    private String remove(@PathVariable int id) {
      sqlSession.delete("teacher.remove",id);

        return "redirect:/admin/queryAll";
    }
    @RequestMapping("createstudent")
    private String createstudent(admin admin){
        System.out.println(admin);
        sqlSession.insert("admin.createstudent",admin);

        return "redirect:/admin/queryAll";
    }

}
