package homebook.springbootlesson3.controllers;

import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Issue;
import homebook.springbootlesson3.entity.Reader;
import homebook.springbootlesson3.repository.ReaderRepository;
import homebook.springbootlesson3.services.IssueService;
import homebook.springbootlesson3.services.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.IssueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private ReaderService readerService;
    @Autowired
    private IssueService issueService;

    private final Map<Long, Issue> issueMap = new HashMap<>();
    private int maxAllowedBooks = 1; // default value


    /*
        GET - получение записей
        POST - создание записей
        PUT - изменение записей
        DELETE - запрос на удаление ресурса
     */


    public IssueController(int maxAllowedBooks) {
        this.maxAllowedBooks = maxAllowedBooks;
    }

    @GetMapping("/book/{id}")
    public String getBookDescription(@PathVariable Long id) {
        Book book = issueService.findById(id);
        if (book != null) {
            return issueService.getDescription(id);
        } else {
            return "Книга с id=" + id + " не найдена";
        }
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = issueService.findById(id);
        if (book != null) {
            issueService.deleteBook(id);
            return "Книга с id=" + id + " удалена";
        } else {
            return "Книга с id=" + id + " не найдена";
        }
    }

    @PostMapping("/book/{name}")
    public Book createBook(@PathVariable String name) {
        Book newBook = new Book(name);
        issueService.saveBook(newBook);
        return newBook;
    }

    @PostMapping("/books/{id}")
    public void issueBook(long idReader, long idBook) {
        long countBooksIssued = issueMap.values().stream()
                .filter(issue -> issue.getReturnedAt() == null && issue.getIdReader() == idReader)
                .count();

        if (countBooksIssued >= maxAllowedBooks) {
            System.out.println("Пользователь уже достиг максимально разрешенного лимита книг!");
            return;
        }

        Issue issue = new Issue(idReader, idBook);
        issueMap.put(issue.getId(), issue);
    }

    public void closeIssue(long issueId) {
        if (issueMap.containsKey(issueId)) {
            Issue issue = issueMap.get(issueId);
            issue.returnBook();
        }
    }

    @GetMapping("{id}")
    public String getIssueDescription(@PathVariable Long id) {

        Issue issue = issueService.findByIdIssue(id);
        if (issue != null) {
            return issue.getDescription();
        } else {
            return "Факт выдачи с id=" + id + " не найден";
        }
    }

    public Book saveBook(Book book) {
        return issueService.saveBook(book);
    }
    @GetMapping("/bookss/{readerId}")
    public Issue createIssue( IssueRequest request){
        issueBook(1L,1L);
        if (issueService.findById(request.getBookId()) == null){
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (issueService.findById(request.getReaderId()) == null){
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueService.createIssue(issue);
        return issue;
    }

    public void issueBook( Long readerId, Long bookId ) {
        Reader reader = readerService.findById(readerId);
        if (reader != null && reader.getIssuedBooks().isEmpty()) {
            Book book = issueService.findById(bookId);
            if (book != null) {
                // Выдача книги читателю
                reader.getIssuedBooks().add(book);
                readerService.saveReader(reader);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "У пользователя уже есть на руках книги");
        }
    }

}
