package pl.spring.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
@RequestMapping("/books")
public class SearchController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping("/search")
	public String showSearchForm() {
		return ViewNames.SEARCH2;
	}
	@RequestMapping("/result")
	public String searchForm(@RequestParam String title,@RequestParam String author,Model model) {
		List<BookTo> foundBook = bookService.findBooksByAuthorOrByTitle(author, title);
		model.addAttribute(ModelConstants.BOOK_LIST,foundBook);
		return ViewNames.BOOKS;	
	}
	

}
