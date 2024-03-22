package homebook.springbootlesson3.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Issue {
    private static long genId;

    private final long id;
    private final long idReader;
    private final long idBook;
    private  LocalDateTime issuedAt;
    private LocalDateTime returnedAt;
    private final LocalDateTime time;

    public Issue(long idReader, long idBook){
        id = genId++;
        this.idBook = idBook;
        this.idReader = idReader;
        this.time = LocalDateTime.now();
    }

    public void returnBook() {
        this.returnedAt = LocalDateTime.now();
    }

    public String getDescription() {
        if (returnedAt != null) {
            return "Book with id " + idBook + " was issued to reader with id " + idReader + " at " + issuedAt + " and returned at " + returnedAt;
        } else {
            return "Book with id " + idBook + " was issued to reader with id " + idReader + " at " + issuedAt + " and has not been returned yet";
        }
    }
}