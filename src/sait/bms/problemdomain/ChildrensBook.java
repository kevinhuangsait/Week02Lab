package sait.bms.problemdomain;

/**
 * Subclass to the Book superclass. Class for children's books.
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */
public class ChildrensBook extends Book {
	
	private String authors;
	private char format;
	

	/**
	 * Constructor for ChildrensBook objects. Contains the superclass attributes as well as two more, Authors and Format.
	 * @param isbn Holds value for ISBN.
	 * @param callnum Holds the value of the call number.
	 * @param available Holds the books available.
	 * @param total Holds the total amount of books.
	 * @param title Holds the title of the book.
	 * @param authors Holds the name of the authors of the book.
	 * @param format Holds the format of the books.
	 */
	public ChildrensBook(long isbn, String callnum, int available, int total, String title, String authors, char format) {
		super(isbn, callnum, available, total, title);
		this.authors = authors;
		this.format = format;
	}
	
	/**
	 * Gets the format of the book.
	 * @return format The format of the book as a char.
	 */
	public char getFormat()
	{
		return format;
	}

	/**
	 * Prints the char format as a String depending on what it is.
	 * @return String Combines the toString with the subclass attributes.
	 */
	@Override
	public String toString() {
		String newFormat = "";
		if(format == 'P')
		{
			newFormat = "Picture Book";
		}
		else if(format == 'E')
		{
			newFormat = "Early Readers";
		}
		else if(format == 'C')
		{
			newFormat = "Chapter Book";
		}
		return super.toString() + "\nAuthors:\t" + authors + "\nFormat:\t\t" + newFormat + "\n";
	}
	
	/**
	 * Overrides Book class saveBook method and appends to it.
	 * @return String Combined saveBook with subclass attributes.
	 */
	@Override
	public String saveBook()
	{
		return super.saveBook() + authors + ";" + format;
	}

}
