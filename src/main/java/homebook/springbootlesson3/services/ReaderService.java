package homebook.springbootlesson3.services;

import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Reader;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;


@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;


    public ReaderService(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }
    public Reader findById(long id) {
        return readerRepository.findById(id);
    }


    public void deleteReader(long id) {
        readerRepository.delete(id);
    }

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

}