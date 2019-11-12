package controlleree;




import model.Book;
import model.teacher;
import model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequestMapping("user")
public class useraction  extends BaseController{
    @Autowired
    private controlleree.userdao userdao;
    @Autowired
    private controlleree.bookdao bookdao;
//private SqlSession sqlSession;
@RequestMapping("create")
    private String create(user user){
//  sqlSession.insert("user.create",user);
    userdao.create(user);
    return "redirect:/login.jsp";
}
@RequestMapping("login")
    private String login(user user){
    List<user> users= userdao.login(user);
//            sqlSession.selectList("user.login",user);
    if(users.size()>0){
session.setAttribute("email",user.getEmail());
 return "redirect:/user/queryAll";
    }else{
request.setAttribute("message","cu wu de yhm mi ma ");
        return "redirect:/login.jsp";
    }

}
@RequestMapping("bookcreate")
    private String bookcreate(Book book){
//    sqlSession .insert("book.create",book);
    bookdao.create(book);
    return "redirect:/user/queryAll";
}
@RequestMapping("queryAll")
    private String queryAll (Book book){
//    session.setAttribute("books",sqlSession.selectList("book.queryAll"));
    session.setAttribute("books",bookdao.queryAll());
    return "redirect:/home.jsp";
}
    @RequestMapping("queryById/{id}")
    private String queryById(@PathVariable int id) {
//        session.setAttribute("book",sqlSession.selectOne("book.queryById" ,id));
        session.setAttribute("book",bookdao.queryById(id));
        return "redirect:/edit.jsp";
    }
    @RequestMapping("modify")
    private String modify(Book book){
//    sqlSession.update("book.modify",book);
        bookdao.modify(book);
        return "redirect:/user/queryAll";
    }
    @RequestMapping("remove/{id}")
    private String remove(@PathVariable int id) {
//       sqlSession.delete("book.remove",id);
        bookdao.remove(id);
        return "redirect:/user/queryAll";
    }

}
