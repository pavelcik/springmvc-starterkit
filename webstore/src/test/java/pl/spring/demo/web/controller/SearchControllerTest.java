package pl.spring.demo.web.controller;

import static org.junit.Assert.assertNotNull;
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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.controller.SearchController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = "mvc-test-configuration.xml")
@WebAppConfiguration

public class SearchControllerTest {
	private MockMvc mockMvc;

	private BookServiceImpl bookService = Mockito.mock(BookServiceImpl.class);

	@InjectMocks
	private SearchController searchController;

	@Before
	public void setup() {
		Mockito.reset(bookService);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		mockMvc = MockMvcBuilders.standaloneSetup(searchController).setViewResolvers(viewResolver).build();
		// ReflectionTestUtils.setField(searchController, "bookService",
		// bookService);

	}

	@Test
	public void shouldAutowireDependencies() {
		assertNotNull(bookService);
		assertNotNull(searchController);

	}

	@Test
	public void showSearchSite() throws Exception {

		mockMvc.perform(get("/books/search")).andExpect(view().name("Search2")).andExpect(status().isOk());

	}

	@Test
	public void showResultSite() throws Exception {

		mockMvc.perform(get("/books/result")).andExpect(view().name("books")).andExpect(status().isOk());

	}

	

	@Test
	public void showResultReturnsList() throws Exception {
		mockMvc.perform(get("/books/result")).andExpect(view().name("books")).andExpect(status().isOk())
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(Object argument) {
						List<BookTo> text = (List<BookTo>) argument;
						return null != text;
					}
				}));

	}

	@Test
	public void showResultCheckParameters() throws Exception {
		List<BookTo> books = new ArrayList<>();
		books.add(new BookTo(0L, "title", "author", BookStatus.FREE));
		books.add(new BookTo(1L, "title", "author", BookStatus.FREE));
		Mockito.when(bookService.findBooksByAuthorOrByTitle("author", "title")).thenReturn(books);

		mockMvc.perform(get("/books/result").param("author", "author").param("title", "title"))
				.andExpect(view().name("books")).andExpect(status().isOk())
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(Object argument) {
						List<BookTo> text = (List<BookTo>) argument;
						return text!=null && text.size() == books.size();

					}
				}));
		Mockito.verify(bookService, Mockito.times(1)).findBooksByAuthorOrByTitle("author", "title");
	}
	
	@Test
	public void showResultNoParameters() throws Exception {
		List<BookTo> books = new ArrayList<>();
		List<BookTo> booksEmpty = new ArrayList<>();
		books.add(new BookTo(0L, "title", "author", BookStatus.FREE));
		books.add(new BookTo(1L, "title", "author", BookStatus.FREE));
		Mockito.when(bookService.findBooksByAuthorOrByTitle("aaaaaa", "title")).thenReturn(booksEmpty);

		mockMvc.perform(get("/books/result").param("author", "aaaaaa").param("title", "title"))
				.andExpect(view().name("books")).andExpect(status().isOk())
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, new ArgumentMatcher<Object>() {
					@Override
					public boolean matches(Object argument) {
						List<BookTo> text = (List<BookTo>) argument;
						return text!=null && text.size() == booksEmpty.size();

					}
				}));
		Mockito.verify(bookService, Mockito.times(1)).findBooksByAuthorOrByTitle("aaaaaa", "title");
	}
	
	
}
