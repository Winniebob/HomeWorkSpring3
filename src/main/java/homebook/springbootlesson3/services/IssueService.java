package homebook.springbootlesson3.services;


import homebook.springbootlesson3.entity.Book;
import homebook.springbootlesson3.entity.Issue;
import homebook.springbootlesson3.repository.BookRepository;
import homebook.springbootlesson3.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class IssueService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final IssueRepository issueRepository;


    public IssueService(BookRepository bookRepository, IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.issueRepository = issueRepository;
    }
    public Issue findByIdIssue(long id) {
        return issueRepository.findById(id);
    }
    public void createIssue (Issue issue){
        issueRepository.createIssue(issue);
    }
    public Book findById(long id) {
        return bookRepository.findById(id);
    }

    public String getDescription(long id) {
        return bookRepository.getDescription(id);
    }

    public void deleteBook(long id) {
        bookRepository.delete(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

}
