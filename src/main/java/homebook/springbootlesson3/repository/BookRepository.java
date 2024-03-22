package homebook.springbootlesson3.repository;


import homebook.springbootlesson3.entity.Book;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private List<Book> list = new ArrayList<>();

    public BookRepository() {
        list.add(new Book("Война и мир"));
        list.add(new Book("Мастер и Маргарита"));
        list.add(new Book("Приключения Буратино"));
    }

    public Book findById(long id){
        for (Book book : list) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
    public String getDescription(long id) {
        Book book = findById(id);
        if (book != null) {
            return "Название книги : " + book.getName() ;
        } else {
            return "Книга с id " + id + " не найдена.";
        }
    }
    public void delete(long id) {
        list.removeIf(book -> book.getId() == id);
    }
    public Book save(Book book) {
        list.add(book);
        return book;
    }


}
