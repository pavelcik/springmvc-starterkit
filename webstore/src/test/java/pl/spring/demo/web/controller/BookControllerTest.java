package pl.spring.demo.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.controller.BookController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = "mvc-test-configuration.xml")
@WebAppConfiguration

public class BookControllerTest {
	private MockMvc mockMvc;

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@Before
	public void setup() {
		Mockito.reset(bookService);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).setViewResolvers(viewResolver).build();
		ReflectionTestUtils.setField(bookController, "bookService", bookService);
	}

	@Test
	public void getAllBooks() throws Exception {

		mockMvc.perform(get("/books")).andExpect(view().name("books")).andExpect(status().isOk());
	}

	@Test
	public void shouldReturnDeleteABookView() throws Exception {
		BookTo book0 = new BookTo(0L, "Dzieci z Bullerbyn", "Astrid Lindrgen", BookStatus.FREE);
		BookTo book1 = new BookTo(1L, "Perfekcyjna Pani Domu", "Małgorzata Rozenek", BookStatus.FREE);
		BookTo book2 = new BookTo(2L, "Harry Potter i Kamień Filozoficzny", "Joanne K. Rowling", BookStatus.LOAN);
		Mockito.when(bookService.findBooksById(2L)).thenReturn(book2);
		mockMvc.perform(delete("/books/delete?id=2")).andExpect(view().name("delete")).andExpect(status().isOk());

	}

	@Test
	public void shouldReturnNoBookFoundView() throws Exception {
		BookTo book0 = new BookTo(0L, "Dzieci z Bullerbyn", "Astrid Lindrgen", BookStatus.FREE);
		BookTo book1 = new BookTo(1L, "Perfekcyjna Pani Domu", "Małgorzata Rozenek", BookStatus.FREE);
		BookTo book2 = new BookTo(2L, "Harry Potter i Kamień Filozoficzny", "Joanne K. Rowling", BookStatus.LOAN);
		Mockito.when(bookService.findBooksById(3L)).thenReturn(null);
		mockMvc.perform(delete("/books/delete?id=3")).andExpect(view().name("BookNotFound")).andExpect(status().isOk());

	}
	
	@Test
	public void testBookListPage() throws Exception {
		// given when
		BookTo book0 = new BookTo(0L, "Dzieci z Bullerbyn", "Astrid Lindrgen", BookStatus.FREE);
		BookTo book1 = new BookTo(1L, "Perfekcyjna Pani Domu", "Małgorzata Rozenek", BookStatus.FREE);
		BookTo book2 = new BookTo(2L, "Harry Potter i Kamień Filozoficzny", "Joanne K. Rowling", BookStatus.LOAN);
		Mockito.when(bookService.findBooksById(1L)).thenReturn(book1);
		mockMvc.perform(get("/books/book?id=1"))

		// then
		.andExpect(view().name("book"))
		.andExpect(model().attribute(ModelConstants.BOOK, new ArgumentMatcher<Object>() {

			@Override
			public boolean matches(Object argument) {
				BookTo book1 = (BookTo) argument;
				return book1.getAuthors() =="Małgorzata Rozenek" &&book1!=null;
			}
			
		}));
	}

	@Test
	public void testIfBooksAreSearchedWell() {
		List<BookTo> books = new ArrayList<>();
		books.add(new BookTo(0L, "Dzieci z Bullerbyn", "Astrid Lindrgen", BookStatus.FREE));
		books.add(new BookTo(1L, "Perfekcyjna Pani Domu", "Małgorzata Rozenek", BookStatus.FREE));
		books.add(new BookTo(2L, "Harry Potter i Kamień Filozoficzny", "Joanne K. Rowling", BookStatus.LOAN));

		Mockito.when(bookService.findBooksById(0L)).thenReturn(books.get(0));
		Mockito.verifyNoMoreInteractions(bookService);
	}

	@Test
	public void testIfAllBooksWillBeReturned() {
		List<BookTo> books = new ArrayList<>();
		books.add(new BookTo(0L, "Dzieci z Bullerbyn", "Astrid Lindrgen", BookStatus.FREE));
		books.add(new BookTo(1L, "Perfekcyjna Pani Domu", "Małgorzata Rozenek", BookStatus.FREE));
		books.add(new BookTo(2L, "Harry Potter i Kamień Filozoficzny", "Joanne K. Rowling", BookStatus.LOAN));

		Mockito.when(bookService.findAllBooks()).thenReturn(books);
		Mockito.verifyNoMoreInteractions(bookService);
	}

	@Test
	public void testBookList() throws Exception {
		// given when

		ResultActions resultActions = mockMvc.perform(get("/books"));

		// then
		resultActions.andExpect(view().name("books"))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(Object argument) {
						List<BookTo> book = (List<BookTo>) argument;
						return null != book;
					}
				}));

	}
}