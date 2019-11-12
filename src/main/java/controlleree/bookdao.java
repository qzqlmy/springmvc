package controlleree;

import model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface bookdao {
    void create(Book book);
    void remove(int id);
    void modify(Book book);
    List<Book> queryAll();
    Book queryById(int id);
}
