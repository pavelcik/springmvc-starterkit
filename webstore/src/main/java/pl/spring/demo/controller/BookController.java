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
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
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
	private BookDao bookdao;
	@Autowired
	private BookMapper bookmapper;

	@RequestMapping
	public String list(Model model) {
		
		
		// TODO: implement default method
		return ViewNames.BOOKS;
	}

	/**
	 * Method collects info about all books
	 */
	@RequestMapping("/all")
	public ModelAndView allBooks() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName(ViewNames.BOOKS);
		modelAndView.addObject(ModelConstants.BOOK_LIST,bookdao.findAll());
		// TODO: implement method gathering and displaying all books
		
		return modelAndView;
	}
	
	@RequestMapping("books-by-title/${title}")
	public List <BookTo> bookByTitle(@PathVariable("title") String title,Model model) {
		
		model.addAttribute(ModelConstants.BOOK, bookdao.findBookByTitle(title));
		
		return bookmapper.map2To(bookdao.findBookByTitle(title));
		
		
		
	
	// TODO: here implement methods which displays book info based on query
	// arguments

		
	}
	
	@RequestMapping(value="/newBook",method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") BindingResult result) {
		if(result.hasErrors()) {
			return ViewNames.ADD_BOOK;
		} else {
			return "redirect:/books";
		}

	// TODO: Implement GET / POST methods for "add book" functionality
	
		
	}
	@RequestMapping(value="/newBook",method=RequestMethod.GET)
	public String newBook() {
		return ViewNames.ADD_BOOK;
	}
	
	
	
	
		/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
