package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
@Transactional(readOnly=false)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookTo> findAllBooks() {
        return BookMapper.map2To(bookRepository.findAll());
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return BookMapper.map2To(bookRepository.findBookByTitle(title));
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return BookMapper.map2To(bookRepository.findBookByAuthor(author));
    }
    
    @Override
    public List<BookTo> findBooksByAuthorAndTitle(String author,String title) {
        List<BookTo> result = findBooksByAuthor(author);
        result = findBooksByTitle(title);
    	return result;
    }
    
    @Override
    public List<BookTo> findBooksByAuthorOrByTitle(String author,String title) {
        List<BookTo> books = null;
        if(author.isEmpty()) {
        	books = findBooksByTitle(title);
        } if(title.isEmpty()) {
        	books = findBooksByAuthor(author);
        } if(title.isEmpty()&&author.isEmpty()) {
        	 books = findAllBooks();
        } if(!title.isEmpty()&&!author.isEmpty()) 
        	return findBooksByAuthorAndTitle(author, title);
		return books;
    }
    
    
    @Override
	public BookTo findBooksById(Long id) {
		return BookMapper.map(bookRepository.findOne(id));
	}

    @Override
    @Transactional(readOnly = false)
    public BookTo saveBook(BookTo book) {
        BookEntity entity = BookMapper.map(book);
        entity = bookRepository.save(entity);
        return BookMapper.map(entity);
    }

	@Override
	@Transactional(readOnly = false)
	public void deleteBook(Long id) {
		bookRepository.delete(id);
		
	}

	
}
