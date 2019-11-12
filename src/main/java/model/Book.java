package model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/18.
 */
public class Book implements Serializable {
    private Integer id;
    private String title;
    private String author;
    private String publish;

    public Book(Integer id, String title, String author, String publish) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publish = publish;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getpublish() {
        return publish;
    }

    public void setpublish(String publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pubilish='" + publish + '\'' +
                '}';
    }
}
