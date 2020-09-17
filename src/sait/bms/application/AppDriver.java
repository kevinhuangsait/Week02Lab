package sait.bms.application;

import java.io.IOException;

/**
 * Program making a book management menu with various functions.
 * AppDriver class simply launches the program.
 * 
 * @author Kevin Huang 000831719
 * @version February 12, 2020
 */

public class AppDriver {
	
	/**
	 * Main method to launch menu.
	 * 
	 * @param args Command-line arguments
	 * @throws IOException When a file with the specified pathname does not exist.
	 */

	public static void main(String[] args) throws IOException {
		sait.bms.managers.BookManagementSystem.bookMenu(); //Runs bookMenu method.
	}

}
