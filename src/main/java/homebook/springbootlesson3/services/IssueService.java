package homebook.springbootlesson3.services;

import homebook.springbootlesson3.controllers.IssueRequest;
import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Issue;
import homebook.springbootlesson3.entity.Reader;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.IssueRepository;
import homebook.springbootlesson3.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import java.util.NoSuchElementException;

@Slf4j
@Service
@RestController
@RequestMapping("service")
@RequiredArgsConstructor
public class IssueService {

    @Autowired

    private final BookRepository bookRepository;
    @Autowired
    private final IssueRepository issueRepository;
    @Autowired
    private final ReaderRepository readerRepository;

    @GetMapping("/bookss/{readerId}")
    public Issue createIssue( IssueRequest request){
        issueBook(1L,1L);
        if (bookRepository.findById(request.getBookId()) == null){
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerRepository.findById(request.getReaderId()) == null){
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId());
        issueRepository.createIssue(issue);
        return issue;
    }


    public void issueBook( Long readerId, Long bookId ) {
        Reader reader = readerRepository.findById(readerId);
        if (reader != null && reader.getIssuedBooks().isEmpty()) {
            Book book = bookRepository.findById(bookId);
            if (book != null) {
                // Выдача книги читателю
                reader.getIssuedBooks().add(book);
                readerRepository.save(reader);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "У пользователя уже есть на руках книги");
        }
    }

}
