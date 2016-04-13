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
		boolean exit = false;					//종료 스위치를 설정
		
		try {
			ServerSocket server = new ServerSocket(4321);
			System.out.println("서버가 열렸습니다.");

			while(true){
				System.out.println("사용자 접속을 대기중입니다.");
				Socket client =server.accept();
				System.out.println("사용자가 접속했습니다 IP : " + client.getInetAddress());
				ois = new ObjectInputStream(client.getInputStream());
				oos = new ObjectOutputStream(client.getOutputStream());

				while(!exit){
					try{
						Object[] request = (Object[]) ois.readObject();				//readObject가 exception을 발생 시키고 있다. 그래서 여기서 try-catch로 잡아줘야 한다.
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
						System.out.println("클라이언트 disconnected!");
						exit = true;
					}//try-catch
				}//inner while
				exit = false;
			}//while무한루프
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//try-catch
	}//main
}//class
