package bookstore.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import bookstore.manager.BookStoreManager;
import bookstore.vo.Book;

public class BookStoreClientManager implements BookStoreManager {
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public BookStoreClientManager(){
		try {
			Socket client = new Socket("127.0.0.1", 4321);
			oos = new ObjectOutputStream(client.getOutputStream());
			ois = new ObjectInputStream(client.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//������
	

	@Override
	public boolean insertBook(Book book) {											//����
		Object[] request = {"insert", book};
		boolean result  = (boolean) sendReturn(request);
		return result;
	}

	@Override
	public ArrayList<Book> searchBookByTitle(String title) {						//����
		Object[] request = {"searchT", title};
		ArrayList<Book> result = (ArrayList<Book>) sendReturn(request);
		return result;
	}

	@Override
	public Book searchBookById(String id) {											//����
		Object[] request = {"searchI", id};
		Book result = (Book) sendReturn(request);
		return result;
	}

	@Override
	public boolean deleteBookByTitle(String title) {								//����
		Object[] request = {"deleteT", title};
		boolean result = (boolean) sendReturn(request);
		return result;
	}

	@Override
	public boolean deleteBookListById(String id) {									//����
		Object[] request = {"deleteI", id};
		boolean result = (boolean) sendReturn(request);
		return result;
	}

	@Override
	public ArrayList<Book> getAllBookList() {										//����
		Object[] request = {"getAll"};
		ArrayList<Book> result = (ArrayList<Book>) sendReturn(request);
		return result;
	}
	
	
	public Object sendReturn(Object[] input){
		Object result = null;
		try {
			oos.writeObject(input);
			result = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//try-catch
		
		return result;
	}//sendReturn()
	
	

}
