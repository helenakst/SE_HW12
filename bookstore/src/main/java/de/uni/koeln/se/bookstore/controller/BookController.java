package de.uni.koeln.se.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.service.BookService;

@RequestMapping("/bookStore")
@RestController
public class BookController {
	
	@Autowired
	BookService bookSer;
	
	/**
	 * HTTP GET request to fetch all the books
	 * @return  list of Book and HTTP.OK status
	 */
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		
		List<Book> books = new ArrayList<Book>();
		books = bookSer.findBooks();
		
		return new ResponseEntity<>(books, HttpStatus.OK);
		
	}
	
	/**
	 * HTTP GET request to fetch a particular book given its ID
	 * @param id
	 * @return Book and HTTP.OK status
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		
		return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
		
	}
	
	/**
	 * HTTP POST request to add a new book
	 * @param book
	 * @return Book and HTTP.CREATED status
	 */
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		
		bookSer.addBook(book);
		
		return new ResponseEntity<>(book, HttpStatus.CREATED);
		
	}
	
	/**
	 * HTTP DELETE request to remove book by id
	 * @param id
	 * @return Book and either HTTP.OK status if successful or HTTP.BAS_REQUEST status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Book> removeBookById(@PathVariable int id) {
		
		Book book = bookSer.fetchBook(id).get();
		
		if (bookSer.deleteBook(id)) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * HTTP GET request to fetch the oldest book
	 * @return  Book and HTTP.OK status
	 */
	@GetMapping("/oldest")
	public ResponseEntity<Book> getOldestBook() {
		
		Book book = bookSer.fetchOldestBook();
		
		return new ResponseEntity<>(book, HttpStatus.OK);
		
	}
	
	/**
	 * HTTP GET request to fetch the latest book
	 * @return  Book and HTTP.OK status
	 */
	@GetMapping("/latest")
	public ResponseEntity<Book> getLatestBook() {
		
		Book book = bookSer.fetchLatestBook();
		
		return new ResponseEntity<>(book, HttpStatus.OK);
		
	}
	
}
