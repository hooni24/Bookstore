package bookstore.vo;

import java.io.Serializable;

public class Book implements Serializable{
	private String id;
	private String title;
	private String publisher;
	
	public Book(String id, String title, String publisher){
		this.id = id;
		this.title = title;
		this.publisher = publisher;
	}

	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title;
	}

	public String getPublisher(){
		return publisher;
	}
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public void printInfo(){
		System.out.print("번호:" + id + " 제목:" + title + " 출판사:" + publisher);
	}

}