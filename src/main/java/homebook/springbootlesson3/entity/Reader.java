package homebook.springbootlesson3.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Data
public class Reader {
    private static long genId;

    private final long id;
    private  String name;
    private List<Book> issuedBooks = new ArrayList<>();
    public Reader(String name) {
        id = genId++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void addIssuedBook(Book book) {
        issuedBooks.add(book);
    }

    public void removeIssuedBook(Book book) {
        issuedBooks.remove(book);
    }
}
