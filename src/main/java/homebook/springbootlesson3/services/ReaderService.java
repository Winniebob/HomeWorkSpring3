package homebook.springbootlesson3.services;

import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Reader;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@RestController
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/reader/{id}/issue")
    public List<Book> getReaderIssues(@PathVariable Long id) {
        Reader reader = readerRepository.findById(id);
        if (reader != null) {
            return reader.getIssuedBooks();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Читатель с id=" + id + " не найден");
        }
    }
    public ReaderService(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }


}