package dao;

import model.user;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface UserDao {
    void create (user user);
    user login(user user);
}
