package bookstore.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import bookstore.manager.BookStoreManager;
import bookstore.vo.Book;

public class BookStoreServerManager implements Serializable, BookStoreManager{
	private ArrayList<Book> bookList = new ArrayList<Book>();	//책 정보들을 저장
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private File file = new File("d:/Bookstore savefile.dat");

	public BookStoreServerManager() {
		if(file.exists()){
			bookList = getFile();
		}else {
			setFile();
		}
		
	}//생성자
	
	public boolean insertBook(Book book) {					
		Book chkBook = searchBookById(book.getId());
		boolean result = false;
		if(chkBook == null){
			bookList.add(book);
			result = true;
			setFile();
		}
		return result;
	}//insertBook()
	
	public ArrayList<Book> searchBookByTitle(String title){
		ArrayList<Book> result = new ArrayList<Book>();
		bookList = getFile();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)){
			result.add(book);
			}
		}
		return result;
	}//searchBookByTitle()

	public Book searchBookById(String id){				
		Book result = null;
		bookList = getFile();
			for (Book book : bookList) {
				if (book.getId().equals(id)){
					result = book;
					break;
				}//inner if
			}//for
		return result;
	}//searchBookById()
	
	public boolean deleteBookByTitle(String title){
		boolean result = false;
		bookList = getFile();
		for (int i =0; i < bookList.size(); i++){
			if (bookList.get(i).getTitle().equals(title)){
				bookList.remove(i);
				i--;
				setFile();
				result = true;
			}
		}
		return result;
	}//deleteBookByTitle()
	
	public boolean deleteBookListById(String id){
		boolean result = false;
		bookList = getFile();
		
		for(int i = bookList.size()-1; i >= 0; i--){
			if(bookList.get(i).getId().equals(id)){
				bookList.remove(i);
				result = true;
			}
		}
		setFile();
		return result;
	}//deleteBookListById()

	public ArrayList<Book> getAllBookList(){
		return getFile();
	}//getAllBookList()
	
	public void setFile() {
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(bookList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//setFile()
	
	public ArrayList<Book> getFile(){
		ArrayList<Book> got = new ArrayList<Book>();
		
		if (file.exists()){
			try {
				ois = new ObjectInputStream(new FileInputStream(file));
				got = (ArrayList<Book>) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}//if
		return got;
	}//getFile()
	
}//class
