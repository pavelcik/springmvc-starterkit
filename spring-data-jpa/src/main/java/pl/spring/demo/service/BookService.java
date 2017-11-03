package pl.spring.demo.service;

import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.stereotype.Service;

public interface BookService {

    List<BookTo> findAllBooks();
    BookTo findBooksById(Long id);
    List<BookTo> findBooksByTitle(String title);
    List<BookTo> findBooksByAuthor(String author);

    BookTo saveBook(BookTo book);
    void deleteBook(Long id);
	List<BookTo> findBooksByAuthorOrByTitle(String author, String title);
	List<BookTo> findBooksByAuthorAndTitle(String author, String title);
}

