package controlleree;

import model.user;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class userdaoimpl implements userdao {
    @Autowired
    private SqlSession sqlSession;
    @Override
    public void create(user user) {
        sqlSession.insert("user.create",user);
    }

    @Override
    public List<user> login(user user) {
        return sqlSession.selectList("user.login",user);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void modify(user model) {

    }

    @Override
    public List<user> queryAll() {
        return null;
    }

    @Override
    public user queryById(int id) {
        return null;
    }
}
