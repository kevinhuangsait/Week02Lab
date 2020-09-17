package sait.bms.problemdomain;

/**
 * Subclass to the Book superclass. Class for paperbacks.
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */
public class Paperback extends Book {
	
	private String authors;
	private int year;
	private char genre;

	/**
	 * Constructor for Paperback objects. Contains the superclass attributes as well as three more, author, year and genre.
	 * @param isbn Holds value for ISBN.
	 * @param callnum Holds the value of the call number.
	 * @param available Holds the books available.
	 * @param total Holds the total amount of books.
	 * @param title Holds the title of the book.
	 * @param authors Holds the name of the book authors.
	 * @param year Holds the year the book was written.
	 * @param genre Holds the genre type of the book.
	 */
	public Paperback(long isbn, String callnum, int available, int total, String title, String authors, int year, char genre) {
		super(isbn, callnum, available, total, title);
		this.authors = authors;
		this.year = year;
		this.genre = genre;
	}
	
	/**
	 * Gets the genre of the book.
	 * @return genre Genre of the book as a char
	 */
	public char getGenre()
	{
		return genre;
	}

	/**
	 * Prints the char format as a String depending on what it is.
	 * @return String Combines the toString with the subclass attributes.
	 */
	@Override
	public String toString() {
		String newGenre = "";
		if(genre == 'A')
		{
			newGenre = "Adventure";
		}
		else if(genre == 'D')
		{
			newGenre = "Drama";
		}
		else if(genre == 'E')
		{
			newGenre = "Education";
		}
		else if(genre == 'C')
		{
			newGenre = "Classic";
		}
		else if(genre == 'F')
		{
			newGenre = "Fantasy";
		}
		else if(genre == 'S')
		{
			newGenre = "Science Fiction";
		}
		return super.toString() + "\nAuthors:\t" + authors + "\nYear:\t\t" + year + "\nGenre:\t\t" + newGenre + "\n";
	}
	
	/**
	 * Overrides Book class saveBook method and appends to it.
	 * @return String Combined saveBook with subclass attributes.
	 */
	@Override
	public String saveBook()
	{
		return super.saveBook() + authors + ";" + year + ";" + genre;
	}

}
