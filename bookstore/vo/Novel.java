package bookstore.vo;

public class Novel extends Book{
	private String author;
	private String genre;

	public Novel(String id, String title, String publisher, String author, String genre){
		super(id, title, publisher);
		this.author = author;
		this.genre = genre;
	}

	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author = author;
	}

	public String getGenre(){
		return genre;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}

	public void printInfo(){
		super.printInfo();
		System.out.printf(", 저자: %s, 장르: %s %n", author, genre);
	}
}