package sait.bms.managers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import sait.bms.problemdomain.*;

/**
 * BookManagementSystem - This class contains all methods to interact with the main menu.
 * 
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */

public class BookManagementSystem {

	public static ArrayList<Book> bookList = new ArrayList<Book>(); // Public ArrayList called bookList. Holds Book objects.
	public static Scanner in = new Scanner(System.in); // Public Scanner object in.
	public static String bookPath = ""; // Public String accessible by every method.
	
	/**
	 * Main menu method that begins by filling an arraylist with Book objects.
	 * Interactive and links to all other methods.
	 * 
	 * @throws IOException When a file with the specified pathname does not exist.
	 */
	public static void bookMenu() throws IOException
	{
		System.out.print("Please enter the path of the data file: ");
		bookPath = in.nextLine();
		File file = new File(bookPath); //File object with path.
		Scanner inputFile = new Scanner(file); //Create scanner object with path.
		while(inputFile.hasNext())
		{
			String bookOutput = inputFile.nextLine();
			String[] bookOutputList = bookOutput.split(";"); //Splitting each part of a line separated by a comma and putting it into an array.
			
			Book newBook = new Book();
			
			if( bookOutputList[0].charAt(12) == '0' || bookOutputList[0].charAt(12) == '1')
			{
				newBook = new ChildrensBook(Long.parseLong(bookOutputList[0]), bookOutputList[1], Integer.parseInt(bookOutputList[2]), Integer.parseInt(bookOutputList[3]), bookOutputList[4], bookOutputList[5], bookOutputList[6].charAt(0));
			}
			else if( bookOutputList[0].charAt(12) == '2' || bookOutputList[0].charAt(12) == '3')
			{
				newBook = new Cookbook(Long.parseLong(bookOutputList[0]), bookOutputList[1], Integer.parseInt(bookOutputList[2]), Integer.parseInt(bookOutputList[3]), bookOutputList[4], bookOutputList[5], bookOutputList[6].charAt(0));
			}
			else if( bookOutputList[0].charAt(12) >= '4' && bookOutputList[0].charAt(12) <= '7')
			{
				newBook = new Paperback(Long.parseLong(bookOutputList[0]), bookOutputList[1], Integer.parseInt(bookOutputList[2]), Integer.parseInt(bookOutputList[3]), bookOutputList[4], bookOutputList[5], Integer.parseInt(bookOutputList[6]), bookOutputList[7].charAt(0));
			}
			else if( bookOutputList[0].charAt(12) == '8' || bookOutputList[0].charAt(12) == '9')
			{
				newBook = new Periodical(Long.parseLong(bookOutputList[0]), bookOutputList[1], Integer.parseInt(bookOutputList[2]), Integer.parseInt(bookOutputList[3]), bookOutputList[4], bookOutputList[5].charAt(0));
			}
			
			bookList.add(newBook); //Adds new book to the ArrayList bookList.
			//Fills the ArrayList bookList with the output from the .txt file.
		}		
		inputFile.close();
		int option = 0; //Initialize option variable. Holds the option the user chooses.
		while(option!= 5) 
		{
			System.out.print("\nWelcome in ABC Book Company: How May We Assist You?" +
					"\n1\tCheckout Book" +
					"\n2\tFind Books by Title" +
					"\n3\tDisplay Books by Type" +
					"\n4\tProduce Random Book List" +
					"\n5\tSave & Exit" +
					"\n\nEnter option: ");
			option = in.nextInt();
			
			if(option == 1)
			{
				BookManagementSystem.bookCheckout();
			}
			else if(option == 2)
			{
				BookManagementSystem.findByTitle();	
			}
			else if(option == 3)
			{
				BookManagementSystem.findByType();	
			}
			else if(option == 4)
			{
				BookManagementSystem.findRandomBooks();
			}
			else if(option == 5)
			{
				BookManagementSystem.saveExit();
			}
			else
			{
				System.out.println("Invalid option!");
			}
		}
		
	}
	
	/**
	 * Method for checking out a book using ISBN. If the book is unavailable or there is no such book associated
	 * with the ISBN, then an error message occurs and the user is sent back to the main menu. When a book is
	 * checked out, the availability decreases,
	 */
	public static void bookCheckout()
	{
		System.out.print("Enter ISBN of book: ");
		long isbnPrompt = in.nextLong(); // Holds the value of the user input ISBN.
		boolean foundBook = false; // Setting up a boolean for error-checking.
		
		for(Book currentBook : bookList) // Loops through the arrayList and prints out the matching ISBN.
		{
			
			if(isbnPrompt == currentBook.getIsbn() && currentBook.getAvailable() > 0 && !(currentBook instanceof Periodical)) // If the ISBN matches, there are available books AND the book is not a periodical.
			{
				currentBook.decreaseAvailable();
				System.out.print("\nThe book \"" + currentBook.getTitle() + "\" has been checked out." +
								 "\nIt can be located using a call number: " + currentBook.getCallnum());
				foundBook = true;
			}
			else if(isbnPrompt == currentBook.getIsbn() && currentBook instanceof Periodical) // If the ISBN matches and the book is a periodical, it cannot be checked out.
			{
				System.out.println("A periodical cannot be checked out.");
				foundBook = true;
			}
			else if(isbnPrompt == currentBook.getIsbn() && currentBook.getAvailable() == 0) // If the IBSN matches and there are none available, error message.
			{
				System.out.println("That book is not available.");
				foundBook = true;
			}
		}
		
		if(!foundBook)
		{
			System.out.println("There is no such book with that ISBN."); // ISBN does not match.
		}
		
	}
	
	/**
	 * Finds a book by the title. Case is insensitive.
	 */
	public static void findByTitle()
	{
		System.out.print("Enter title to search for: ");
		String searchTitle = in.next(); // Holds user inputted search term.
		boolean foundBook = false; // Error-checking.
		
		for(Book currentBook : bookList)
		{
			if(currentBook.getTitle().toUpperCase().contains(searchTitle.toUpperCase())) // Case insensitive if statement. Print if contains the search term.
			{
				System.out.println(currentBook);
				foundBook = true;
			}
		}
		
		if(!foundBook)
		{
			System.out.println("No books found.");
		}
	}
	
	/**
	 * Find a book by type. The user inputs the type first then the user can further choose book attributes depending on the type of book.
	 * Uses downcasting.
	 * 
	 */
	public static void findByType()
	{
		System.out.print("#\tType" +
						 "\n1\tChildren's Books" +
						 "\n2\tCookbooks" +
						 "\n3\tPaperbacks" +
						 "\n4\tPeriodicals" +
						 "\n\nEnter type of book: ");
		int bookType = in.nextInt(); // Holds number for type of book.
		boolean errorCheck = false; // Holds error checking boolean.
		while(!errorCheck) // Loop if wrong option.
		{
			if(bookType == 1)
			{
					System.out.print("Enter a format (P for Picture Book, E for Early Readers, C for Chapter Book): ");
					char format = in.next().charAt(0); // Holds char of format.
					
					for(Book currentBook : bookList)
					{
						if(currentBook instanceof ChildrensBook) // If current object is the subclass ChildrensBook
						{
							ChildrensBook newChildBook = (ChildrensBook) currentBook; // Downcasting
							if(newChildBook.getFormat() == format) // If the user input char equals the current object's value.
							{
								System.out.println(currentBook); //Print all books of the type and format.
								errorCheck=true; // Validation for book type information.
							}
						}
					}
					
					if(!errorCheck)
					{
						System.out.println("Invalid format."); // Book type validation.
					}
			}
			else if(bookType == 2) // Same thing as above but for cookbooks.
			{
				System.out.print("Enter a diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, N for None): ");
				char diet = in.next().charAt(0);
				for(Book currentBook : bookList)
				{
					if(currentBook instanceof Cookbook)
					{
						Cookbook newCookbook = (Cookbook) currentBook;
						if(newCookbook.getDiet() == diet)
						{
							System.out.println(currentBook);
							errorCheck=true;
						}
					}
				}
				
				if(!errorCheck)
				{
					System.out.println("Invalid diet.");
				}
			}
			else if(bookType == 3) // Same thing as above but for paperbacks.
			{
				System.out.print("Enter a genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, S for Science Fiction): ");
				char genre = in.next().charAt(0);
				for(Book currentBook : bookList)
				{
					if(currentBook instanceof Paperback)
					{
						Paperback newPaperback = (Paperback) currentBook;
						if(newPaperback.getGenre() == genre)
						{
							System.out.println(currentBook);
							errorCheck=true;
						}
					}
				}
				
				if(!errorCheck)
				{
					System.out.println("Invalid genre.");
				}
			}
			else if(bookType == 4) // Same thing as above but for periodicals.
			{
				System.out.print("Enter a frequency (D for Daily, W for Weekly, M for Monthly, B for Bimonthly, Q for Quarterly): ");
				char frequency = in.next().charAt(0);
				for(Book currentBook : bookList)
				{
					if(currentBook instanceof Periodical)
					{
						Periodical newPeriodical = (Periodical) currentBook;
						if(newPeriodical.getFrequency() == frequency)
						{
							System.out.println(currentBook);
							errorCheck=true;
						}
					}
					
				}
				if(!errorCheck) // Book type validation.
				{
					System.out.println("Invalid frequency.");
				}
			}
			else // Loop if wrong type.
			{
				System.out.print("Invalid option. Enter type of book: ");
				bookType = in.nextInt();
			}
		}
	}
	
	/**
	 * Finds random books depending on the user-inputted number of books.
	 */
	public static void findRandomBooks()
	{
		Collections.shuffle(bookList); //Shuffles the ArrayList bookList.
		System.out.print("Enter number of books: ");
		int numberBooks = in.nextInt(); // Holds number of books
		while(numberBooks <= 0)
		{
			System.out.println("Invalid number. Please re-enter.");
			System.out.print("Enter number of books: ");
			numberBooks = in.nextInt();
			//Error checking.
		}
		System.out.println("\nRandom books:");
		Book currentBook; //Initialize object for while loop.

		while(numberBooks > 0)
		{
			currentBook = bookList.get(numberBooks-1); //Grabs book object on current index.

			System.out.println(currentBook.toString()); //Print book object.
			
			numberBooks -= 1;
			//Takes a book for each number as index and prints them all until numberMovies hits 0
		}
	}
	
	/**
	 * Prints to a new books.txt file, using the saveBook method.
	 * @throws IOException When a file with the specified pathname does not exist.
	 */
	public static void saveExit() throws IOException
	{
		PrintWriter outputFile = new PrintWriter(bookPath); // Create printwriter object.
		for (Book currentBook : bookList)
		{
			outputFile.println(currentBook.saveBook());
			//Creates an entirely new .txt file with data from the ArrayList bookList.
		}
		outputFile.close(); // Close PrintWriter.
	}

}
