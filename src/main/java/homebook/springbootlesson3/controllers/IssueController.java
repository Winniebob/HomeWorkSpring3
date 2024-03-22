package homebook.springbootlesson3.controllers;

import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Issue;
import homebook.springbootlesson3.services.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.IssueRepository;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("issue")
@RequiredArgsConstructor
public class IssueController {

    @Autowired
    private IssueService service;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IssueRepository issueRepository;
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
        Book book = bookRepository.findById(id);
        if (book != null) {
            return bookRepository.getDescription(id);
        } else {
            return "Книга с id=" + id + " не найдена";
        }
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id);
        if (book != null) {
            bookRepository.delete(id);
            return "Книга с id=" + id + " удалена";
        } else {
            return "Книга с id=" + id + " не найдена";
        }
    }

    @PostMapping("/book/{name}")
    public Book createBook(@PathVariable String name) {
        Book newBook = new Book(name);
        bookRepository.save(newBook);
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

        Issue issue = issueRepository.findById(id);
        if (issue != null) {
            return issue.getDescription();
        } else {
            return "Факт выдачи с id=" + id + " не найден";
        }
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
