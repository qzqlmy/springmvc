package controlleree;

import model.Book;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class bookdaoimpl implements bookdao {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void create(Book book) {
        sqlSession.insert("book.create",book);
    }

    @Override
    public void remove(int id) {
        sqlSession.delete("book.remove",id);
    }

    @Override
    public void modify(Book book) {
       sqlSession.update("book.modify",book);
    }

    @Override
    public List<Book> queryAll() {
        return sqlSession.selectList("book.queryAll");
    }

    @Override
    public Book queryById(int id) {
        return sqlSession.selectOne("book.queryById",id);
    }
}
