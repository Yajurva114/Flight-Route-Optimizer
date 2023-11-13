//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P3 Flight Plan Frontend Interface
// Course:   CS 400 Fall 2022
//
// Author:   Yajurva
// Email:    shrotriya@wisc.edu
// Lecturer: Florian Heimerl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interface that provides methods to construct a flight plan
 *
 */
public interface IFlightPlanFrontend {
	
	
	 /** The constructor of this class that the interface provides. Takes a scanner as input
	  * to track user inputs as well as a FlightPlanBackend object.
	  * 
	 * @param userInput a Scanner object that will track user input
	 * @param backend a FlightPlanBackend object for access to the backend implementation
	 */
	//public FlightPlanFrontend(Scanner userInput, FlightPlanBackend backend);
	
	/** This method starts the command loop for the frontend and will continue until the user
	 * exits the program at which point it will terminate.
	 * 
	 */
	public void runCommandLoop();
	
	/** Prints the contents of the main menu to System.out
	 * 
	 */
	public void displayMainMenu();
	
	/** Prints a string representation of airports to System.out
	 * 
	 */
	void displayAirports(ArrayList airports);

}

