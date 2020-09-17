package sait.bms.problemdomain;


/**
 * Book class. Superclass for all other book types. Constructs and interacts with Book objects.
 * 
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */
public class Book {
	private long isbn;
	private String callnum;
	private int available;
	private int total;
	private String title;
	
	
	/**
	 * Constructor for the Book class. Creates a new object with the ISBN, Call Number, Availability, Total Available, and Title.
	 * @param isbn Holds value for ISBN.
	 * @param callnum Holds the value of the call number.
	 * @param available Holds the books available.
	 * @param total Holds the total amount of books.
	 * @param title Holds the title of the book.
	 */
	public Book(long isbn, String callnum, int available, int total, String title) {
		super();
		this.isbn = isbn;
		this.callnum = callnum;
		this.available = available;
		this.total = total;
		this.title = title;
	}

	/**
	 * Default constructor.
	 */
	public Book() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *  Gets the ISBN.
	 * @return isbn ISBN as long.
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * Gets the call number.
	 * @return callnum Call Number as a string.
	 */
	public String getCallnum() {
		return callnum;
	}
	
	/**
	 * Gets the title.
	 * @return title Title as a string.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the available books.
	 * @return available Books available as an int.
	 */
	public int getAvailable() {
		return available;
	}
	
	/**
	 * Decreases the remaining books available by 1.
	 * @return available Books available as an int.
	 */
	public int decreaseAvailable() {
		available -= 1;
		return available;
	}
	
	/**
	 * toString method for Book class.
	 * @return String Book information in a certain format.
	 */
	@Override
	public String toString() {
		return "ISBN:\t\t" + isbn + "\nCall Number:\t" + callnum + "\nAvailable:\t" + available + "\nTotal:\t\t" + total
				+ "\nTitle:\t\t" + title;
	}
	
	/**
	 * Method for printing book details to a .txt file.
	 * @return String Book details in a certain format.
	 */
	public String saveBook()
	{
		return isbn + ";" + callnum + ";" + available + ";" + total + ";" + title + ";";
	}

}
