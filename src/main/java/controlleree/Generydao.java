package controlleree;

import model.Book;
import model.user;

import java.util.List;

public interface Generydao<T> {
    void create(T model);
    List<T> login(T model);
    void remove(int id);
    void modify(T model);
    List<T> queryAll();
    T queryById(int id);
}
