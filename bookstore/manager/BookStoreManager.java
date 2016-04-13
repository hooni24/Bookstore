package bookstore.manager;

import java.util.ArrayList;
import bookstore.vo.Book;

public interface BookStoreManager {
	public boolean insertBook(Book book) ;
	public ArrayList<Book> searchBookByTitle(String title);
	public Book searchBookById(String id);
	public boolean deleteBookByTitle(String title);
	public boolean deleteBookListById(String id);
	public ArrayList<Book> getAllBookList();
}
