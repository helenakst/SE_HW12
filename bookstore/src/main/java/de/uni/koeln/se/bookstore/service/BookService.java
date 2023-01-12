package de.uni.koeln.se.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	/*
	 * Returns a list of all books available in database
	 */
	public List<Book> findBooks() {
		return bookRepo.findAll();
	}
	
	/*
	 * Searches for a book by its id.
	 * Returns book, if existing, or NoSuchElementException
	 */
	public Optional<Book> fetchBook(int id){
		return bookRepo.findById(id);
	}
	
	/*
	 * Adds a new book to database and returns it as confirmation
	 */
	public Book addBook(Book book) {
		return bookRepo.save(book);
	}
	
	/*
	 * Deletes book by id. Returns true, if successful, otherwise false.
	 */
	public boolean deleteBook(int id) {
		boolean status;
		try {
			bookRepo.deleteById(id);
			status = true;
		}catch (Exception e) {
			status = false;
		}
		return status;
	}
	
	/*
	 * Returns oldest book in database
	 */
	public Book fetchOldestBook(){
		List<Book> bookList = bookRepo.findAll(Sort.by("publishYear"));
		return bookList.get(0);
	}
	
	/*
	 * Returns latest book in database
	 */
	public Book fetchLatestBook(){
		List<Book> bookList = bookRepo.findAll(Sort.by("publishYear"));
		return bookList.get(bookList.size()-1);
	}
}
