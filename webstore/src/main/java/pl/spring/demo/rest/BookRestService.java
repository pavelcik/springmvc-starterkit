package pl.spring.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@Controller
@ResponseBody
public class BookRestService {
	
	@Autowired
	private BookService bookservice;
	
	//@RequestMapping(value= "/books/book")
	//public List<BookTo> showBookDetails(@PathVariable("id") Long id) { 
	
		//
		
		// TODO: Inject properly book service

	// TODO: implement all necessary CRUD operations as a rest service

	// TODO: implement some search methods considering single request parameters
	// / multiple request parameters / array request parameters

	

}