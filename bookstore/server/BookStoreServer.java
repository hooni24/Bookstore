package bookstore.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import bookstore.vo.Book;

public class BookStoreServer {
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;

	public static void main(String[] args) {
		BookStoreServerManager serverMgr = new BookStoreServerManager();
		boolean exit = false;					//���� ����ġ�� ����
		
		try {
			ServerSocket server = new ServerSocket(4321);
			System.out.println("������ ���Ƚ��ϴ�.");

			while(true){
				System.out.println("����� ������ ������Դϴ�.");
				Socket client =server.accept();
				System.out.println("����ڰ� �����߽��ϴ� IP : " + client.getInetAddress());
				ois = new ObjectInputStream(client.getInputStream());
				oos = new ObjectOutputStream(client.getOutputStream());

				while(!exit){
					try{
						Object[] request = (Object[]) ois.readObject();				//readObject�� exception�� �߻� ��Ű�� �ִ�. �׷��� ���⼭ try-catch�� ������ �Ѵ�.
						String cmdValue = (String) request[0];
						
						switch (cmdValue){
							case "insert" :
								boolean insertResult = serverMgr.insertBook((Book) request[1]);
								oos.writeObject(insertResult);
								break;
							case "searchT" :
								ArrayList<Book> searchTResult = serverMgr.searchBookByTitle((String) request[1]);
								oos.writeObject(searchTResult);
								
								break;
							case "searchI" :
								Book searchIResult = serverMgr.searchBookById((String) request[1]);
								oos.writeObject(searchIResult);
								break;
							case "deleteT" :
								boolean deleteTResult = serverMgr.deleteBookByTitle((String) request[1]);
								oos.writeObject(deleteTResult);
								break;
							case "deleteI" :
								boolean deleteIResult = serverMgr.deleteBookListById((String) request[1]);
								oos.writeObject(deleteIResult);
								break;
							case "getAll" :
								ArrayList<Book> result = serverMgr.getAllBookList();
								oos.writeObject(result);
								break;
							default:
						}//switch
					}catch(IOException ioe){
						System.out.println("Ŭ���̾�Ʈ disconnected!");
						exit = true;
					}//try-catch
				}//inner while
				exit = false;
			}//while���ѷ���
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//try-catch
	}//main
}//class
