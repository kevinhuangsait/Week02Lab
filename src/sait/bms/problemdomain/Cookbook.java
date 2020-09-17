package sait.bms.problemdomain;

/**
 * Subclass to the Book superclass. Class for cookbooks.
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */
public class Cookbook extends Book {
	
	private String publisher;
	private char diet;
	
	/**
	 * Constructor for Cookbook objects. Contains the superclass attributes as well as two more, publisher and diet.
	 * @param isbn Holds value for ISBN.
	 * @param callnum Holds the value of the call number.
	 * @param available Holds the books available.
	 * @param total Holds the total amount of books.
	 * @param title Holds the title of the book.
	 * @param publisher Holds the name of the publishers of the book.
	 * @param diet Holds the type of diet the books are for.
	 */
	public Cookbook(long isbn, String callnum, int available, int total, String title, String publisher, char diet) {
		super(isbn, callnum, available, total, title);
		this.publisher = publisher;
		this.diet = diet;
	}
	
	/**
	 * Gets the diet of the book.
	 * @return diet The diet type as a char.
	 */
	public char getDiet()
	{
		return diet;
	}

	/**
	 * Prints the char format as a String depending on what it is.
	 * @return String Combines the toString with the subclass attributes.
	 */
	@Override
	public String toString() {
		String newDiet = "";
		if(diet == 'D')
		{
			newDiet = "Diabetic";
		}
		else if(diet == 'V')
		{
			newDiet = "Vegetarian";
		}
		else if(diet == 'G')
		{
			newDiet = "Gluten-free";
		}
		else if(diet == 'I')
		{
			newDiet = "International";
		}
		else if(diet == 'N')
		{
			newDiet = "None";
		}
		return super.toString() + "\nPublisher:\t" + publisher + "\nDiet:\t\t" + newDiet + "\n";
	}
	
	/**
	 * Overrides Book class saveBook method and appends to it.
	 * @return String Combined saveBook with subclass attributes.
	 */
	@Override
	public String saveBook() 
	{
		return super.saveBook() + publisher + ";" + diet;
	}

}
