package dao;

import model.Book;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface BookDao {
    void create (Book book);
    void remove(int id);
    void modify (Book book);
    List<Book> queryAll();
    Book queryById(int id);
}
