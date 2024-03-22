package homebook.springbootlesson3.controllers;

import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Reader;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.IssueRepository;
import homebook.springbootlesson3.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderRepository readerRepository;
    @Autowired
     BookRepository bookRepository;
    @Autowired
    IssueRepository issueRepository;

    @GetMapping("{id}")
    public String getReaderInfo(@PathVariable Long id) {
        Reader reader = readerRepository.findById(id);
        if (reader != null) {
            return reader.getName();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Читатель с id=" + id + " не найден");
        }
    }

    @DeleteMapping("{id}")
    public String deleteReader(@PathVariable Long id) {
        Reader reader = readerRepository.findById(id);
        if (reader != null) {
            readerRepository.delete(id);
            return "Читатель с id=" + id + " удален";
        } else {
            return "Читатель с id=" + id + " не найден";
        }
    }

    @PostMapping("/reader/{name}")
    public Reader createReader(@PathVariable String name) {
        Reader newReader = new Reader(name);
        readerRepository.save(newReader);
        return newReader;
    }
}