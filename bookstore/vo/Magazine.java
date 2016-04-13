package bookstore.vo;

public class Magazine extends Book{
	private int year;
	private int month;

	public Magazine(String id, String title, String publisher, int year, int month){
		super(id, title, publisher);
		this.year = year;
		this.month = month;
	}

	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year = year;
	}

	public int getMonth(){
		return month;
	}
	public void setMonth(int month){
		this.month = month;
	}

	public void printInfo(){
		super.printInfo();
		System.out.println(" 발행년월:" + year + "/" + month);
	}
}
