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
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("잘못 선택했습니다.");
				break;
			}//switch
		}//while
	}

	public int showMainMenu() {
		System.out.println("=============");
		System.out.println(" 도서 정보 관리 ");
		System.out.println("=============");
		System.out.println("1. 도서 정보 입력");
		System.out.println("2. 제목으로 검색");
		System.out.println("3. ID로 검색");
		System.out.println("4. 제목으로 삭제(중복 한방)");	
		System.out.println("5. ID로 도서 삭제");	
		System.out.println("6. 전체 출력");
		System.out.println("99. 종료");
		System.out.print("선택> ");
		String input = scan.nextLine();
		int num = Integer.parseInt(input);
		
		return num;
	}

	public void input() {				
		System.out.println("* 도서 정보 입력");
		System.out.print("1. 입력할 분야 (1:소설, 2:잡지) : ");
		String input = scan.nextLine();
		int type = Integer.parseInt(input);

		if (type == 1){
			System.out.print("2. 도서 구분 번호 : ");
			String id = scan.nextLine();
			System.out.print("3. 제목 : ");
			String title = scan.nextLine();
			System.out.print("4. 출판사 : ");
			String publisher = scan.nextLine();
			System.out.print("5. 저자 : ");
			String author = scan.nextLine();
			System.out.print("6. 장르 : ");
			String genre = scan.nextLine();

			Novel n = new Novel (id, title, publisher, author, genre);
			if (clientMgr.insertBook(n)){
				System.out.println("등록완료");
			}else {
				System.out.println("등록실패");
			}

		}else if (type == 2){
			System.out.print("2. 도서 구분 번호 : ");
			String id = scan.nextLine();
			System.out.print("3. 제목 : ");
			String title = scan.nextLine();
			System.out.print("4. 출판사 : ");
			String publisher = scan.nextLine();
			System.out.print("5. 발행년 : ");
			int year = scan.nextInt();
			System.out.print("6. 발행월 : ");
			int month = scan.nextInt();

			Magazine m = new Magazine (id, title, publisher, year, month);
			if (clientMgr.insertBook(m)){
				System.out.println("등록완료");
			}else{ 
				System.out.println("등록실패");
			}
		}else {
			System.out.println("분야를 정확히 입력하세요.");
			input();
		}
	}
		
	void searchByTitle() {
		System.out.print("검색할 제목 : ");
		String title = scan.nextLine();

		ArrayList<Book> searched = clientMgr.searchBookByTitle(title);
		if (searched.size() > 0){
			for (Book book : searched){
				book.printInfo();
			}
		}else {
			System.out.println("검색 결과가 없습니다.");
		}
	}
	
	public void searchById() {
		System.out.println("*도서 정보 검색");
		System.out.print("검색할 도서 구분 번호 : ");
		String id = scan.nextLine();
		Book searched = clientMgr.searchBookById(id);
		if (searched != null){
			searched.printInfo();
		}else {
			System.out.println("찾으시는 책이 없습니다.");
		}
		
			
	}
	
	public void deleteBookListById(){
		System.out.println("*도서 삭제");
		System.out.print("삭제할 도서의 도서 구분 번호 : ");
		String id = scan.nextLine();
		boolean deleteChk = clientMgr.deleteBookListById(id);
		if (deleteChk){
			System.out.println("도서가 삭제되었습니다.");
		}else {
			System.out.println("삭제할 도서가 없습니다.");
		}
	}
	
	public void printAll(){
		System.out.println("* 도서 정보 전체 출력");
		ArrayList<Book> bl = clientMgr.getAllBookList();
		if(bl.size() != 0){
			for (Book book : bl){
				book.printInfo();
			}
		}else {
			System.out.println("저장된 데이터가 없습니다.");
		}
	}
	
	
	public void deleteBookByTitle(){
		System.out.println("삭제할 제목을 입력");
		String title = scan.nextLine();
		boolean result = clientMgr.deleteBookByTitle(title);
		if (result){
			System.out.println("삭제완료");
		}else {
			System.out.println("삭제실패");
		}
	}
	
	
}