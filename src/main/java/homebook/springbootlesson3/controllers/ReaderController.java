package homebook.springbootlesson3.controllers;

import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Reader;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.IssueRepository;
import homebook.springbootlesson3.repository.ReaderRepository;
import homebook.springbootlesson3.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping("{id}")
    public String getReaderInfo(@PathVariable Long id) {
        Reader reader = readerService.findById(id);
        if (reader != null) {
            return reader.getName();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Читатель с id=" + id + " не найден");
        }
    }

    @DeleteMapping("{id}")
    public String deleteReader(@PathVariable Long id) {
        Reader reader = readerService.findById(id);
        if (reader != null) {
            deleteReader(id);
            return "Читатель с id=" + id + " удален";
        } else {
            return "Читатель с id=" + id + " не найден";
        }
    }
    @GetMapping("/reader/{id}/issue")
    public List<Book> getReaderIssues(@PathVariable Long id) {
        Reader reader = readerService.findById(id);
        if (reader != null) {
            return reader.getIssuedBooks();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Читатель с id=" + id + " не найден");
        }
    }
    @PostMapping("/reader/{name}")
    public Reader createReader(@PathVariable String name) {
        Reader newReader = new Reader(name);
        readerService.saveReader(newReader);
        return newReader;
    }
}