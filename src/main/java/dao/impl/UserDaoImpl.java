package dao.impl;

import dao.UserDao;
import model.user;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/8/19.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession sqlSession;
    @Override
    public void create(user user) {
        sqlSession.insert("user.create", user);
    }

    @Override
    public user login(user user) {
        return sqlSession.selectOne("user.login",user);
    }
}
