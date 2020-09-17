package sait.bms.problemdomain;

/**
 * Subclass to the Book superclass. Class for periodicals.
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */
public class Periodical extends Book {
	
	private char frequency;

	/**
	 * Constructor for Paperback objects. Contains the superclass attributes as well as one more, frequency.
	 * @param isbn Holds value for ISBN.
	 * @param callnum Holds the value of the call number.
	 * @param available Holds the books available.
	 * @param total Holds the total amount of books.
	 * @param title Holds the title of the book.
	 * @param frequency Holds the frequency of the book.
	 */
	public Periodical(long isbn, String callnum, int available, int total, String title, char frequency) {
		super(isbn, callnum, available, total, title);
		this.frequency = frequency;
	}
	
	/**
	 * Gets the frequency.
	 * @return frequency Frequency as a char.
	 */
	public char getFrequency()
	{
		return frequency;
	}

	/**
	 * Prints the char format as a String depending on what it is.
	 * @return String Combines the toString with the subclass attributes.
	 */
	@Override
	public String toString() {
		String newFrequency = "";
		if(frequency == 'D')
		{
			newFrequency = "Daily";
		}
		else if(frequency == 'W')
		{
			newFrequency = "Weekly";
		}
		else if(frequency == 'M')
		{
			newFrequency = "Monthly";
		}
		else if(frequency == 'B')
		{
			newFrequency = "Bimonthly";
		}
		else if(frequency == 'Q')
		{
			newFrequency = "Quarterly";
		}
		return super.toString() + "\nFrequency:\t" + newFrequency + "\n";
	}
	
	/**
	 * Overrides Book class saveBook method and appends to it.
	 * @return String Combined saveBook with subclass attributes.
	 */
	@Override
	public String saveBook()
	{
		return super.saveBook() + frequency;
	}
}
