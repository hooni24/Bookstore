package bookstore.client;

import java.util.ArrayList;
import java.util.Scanner;

import bookstore.vo.Book;
import bookstore.vo.Magazine;
import bookstore.vo.Novel;

public class BookStoreUI {
	Scanner scan = new Scanner(System.in);
	private BookStoreClientManager clientMgr;
	
	public BookStoreUI() {
		clientMgr = new BookStoreClientManager();
		while (true) {
			int menuNo = showMainMenu();
			switch (menuNo) {
			case 1:
				input();
				break;
			case 2:
				searchByTitle();
				break;
			case 3:
				searchById();
				break;
			case 4:
				deleteBookByTitle();
				break;			
			case 5:
				deleteBookListById();
				break;
			case 6:
				printAll();
				break;
			case 99:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			default:
				System.out.println("�߸� �����߽��ϴ�.");
				break;
			}//switch
		}//while
	}

	public int showMainMenu() {
		System.out.println("=============");
		System.out.println(" ���� ���� ���� ");
		System.out.println("=============");
		System.out.println("1. ���� ���� �Է�");
		System.out.println("2. �������� �˻�");
		System.out.println("3. ID�� �˻�");
		System.out.println("4. �������� ����(�ߺ� �ѹ�)");	
		System.out.println("5. ID�� ���� ����");	
		System.out.println("6. ��ü ���");
		System.out.println("99. ����");
		System.out.print("����> ");
		String input = scan.nextLine();
		int num = Integer.parseInt(input);
		
		return num;
	}

	public void input() {				
		System.out.println("* ���� ���� �Է�");
		System.out.print("1. �Է��� �о� (1:�Ҽ�, 2:����) : ");
		String input = scan.nextLine();
		int type = Integer.parseInt(input);

		if (type == 1){
			System.out.print("2. ���� ���� ��ȣ : ");
			String id = scan.nextLine();
			System.out.print("3. ���� : ");
			String title = scan.nextLine();
			System.out.print("4. ���ǻ� : ");
			String publisher = scan.nextLine();
			System.out.print("5. ���� : ");
			String author = scan.nextLine();
			System.out.print("6. �帣 : ");
			String genre = scan.nextLine();

			Novel n = new Novel (id, title, publisher, author, genre);
			if (clientMgr.insertBook(n)){
				System.out.println("��ϿϷ�");
			}else {
				System.out.println("��Ͻ���");
			}

		}else if (type == 2){
			System.out.print("2. ���� ���� ��ȣ : ");
			String id = scan.nextLine();
			System.out.print("3. ���� : ");
			String title = scan.nextLine();
			System.out.print("4. ���ǻ� : ");
			String publisher = scan.nextLine();
			System.out.print("5. ����� : ");
			int year = scan.nextInt();
			System.out.print("6. ����� : ");
			int month = scan.nextInt();

			Magazine m = new Magazine (id, title, publisher, year, month);
			if (clientMgr.insertBook(m)){
				System.out.println("��ϿϷ�");
			}else{ 
				System.out.println("��Ͻ���");
			}
		}else {
			System.out.println("�о߸� ��Ȯ�� �Է��ϼ���.");
			input();
		}
	}
		
	void searchByTitle() {
		System.out.print("�˻��� ���� : ");
		String title = scan.nextLine();

		ArrayList<Book> searched = clientMgr.searchBookByTitle(title);
		if (searched.size() > 0){
			for (Book book : searched){
				book.printInfo();
			}
		}else {
			System.out.println("�˻� ����� �����ϴ�.");
		}
	}
	
	public void searchById() {
		System.out.println("*���� ���� �˻�");
		System.out.print("�˻��� ���� ���� ��ȣ : ");
		String id = scan.nextLine();
		Book searched = clientMgr.searchBookById(id);
		if (searched != null){
			searched.printInfo();
		}else {
			System.out.println("ã���ô� å�� �����ϴ�.");
		}
		
			
	}
	
	public void deleteBookListById(){
		System.out.println("*���� ����");
		System.out.print("������ ������ ���� ���� ��ȣ : ");
		String id = scan.nextLine();
		boolean deleteChk = clientMgr.deleteBookListById(id);
		if (deleteChk){
			System.out.println("������ �����Ǿ����ϴ�.");
		}else {
			System.out.println("������ ������ �����ϴ�.");
		}
	}
	
	public void printAll(){
		System.out.println("* ���� ���� ��ü ���");
		ArrayList<Book> bl = clientMgr.getAllBookList();
		if(bl.size() != 0){
			for (Book book : bl){
				book.printInfo();
			}
		}else {
			System.out.println("����� �����Ͱ� �����ϴ�.");
		}
	}
	
	
	public void deleteBookByTitle(){
		System.out.println("������ ������ �Է�");
		String title = scan.nextLine();
		boolean result = clientMgr.deleteBookByTitle(title);
		if (result){
			System.out.println("�����Ϸ�");
		}else {
			System.out.println("��������");
		}
	}
	
	
}