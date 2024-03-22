package homebook.springbootlesson3.services;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String book_not_found, HttpStatus notFound) {
    }
}
