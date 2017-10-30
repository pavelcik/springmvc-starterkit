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
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;

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

	@RequestMapping("books-by-title/${title}")
	public List<BookTo> bookByTitle(@PathVariable("title") String title, Model model) {

		List bookByTitle = bookService.findBooksByTitle(title);
		model.addAttribute(ModelConstants.BOOK, bookService.findBooksByTitle(title));
		return bookByTitle;

		// TODO: here implement methods which displays book info based on query
		// arguments

	}

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String findBookDetails(@RequestParam("id") Long id, Model model) {
		model.addAttribute(bookService.findBooksById(id));
		return ViewNames.BOOK;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") BindingResult result) {
		if (result.hasErrors()) {
			return ViewNames.ADD_BOOK;
		} else {
			return "redirect:/books";
		}

		// TODO: Implement GET / POST methods for "add book" functionality

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String newBook() {
		return ViewNames.ADD_BOOK;
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchForBooks() {
		return ViewNames.SEARCH;
	}

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}