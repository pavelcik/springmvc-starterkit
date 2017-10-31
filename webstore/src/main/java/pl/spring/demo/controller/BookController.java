package pl.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Book controller
 * 
 * @author mmotowid
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;


	@RequestMapping
	public String list(Model model) {
		List<BookTo> books = bookService.findAllBooks();
		model.addAttribute(ModelConstants.BOOK_LIST, books);
		return ViewNames.BOOKS;
	}




	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String findBookDetails(@RequestParam Long id, Model model) {
		BookTo book = bookService.findBooksById(id);
		model.addAttribute(ModelConstants.BOOK,book);
		return ViewNames.BOOK;
	}
	
	@RequestMapping(value = "/delete")
	public String deleteBook(@RequestParam Long id, Model model) {
		bookService.deleteBook(id);
		return ViewNames.DELETE;
	}
	
	

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}