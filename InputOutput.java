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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class to utilise while testing frontend code
 * @author Yajurva
 *
 */
public class InputOutput {
	private ByteArrayOutputStream byteStream;
	private PrintStream printStream;
	private FlightPlanFrontend frontend;
	
	/**
	 * Constructor to make a ByteOutputStream and PrintStream to save the console output to be checked
	 * 
	 * 
	 * @param input string that acts as the the user's input
	 */
	public InputOutput(String input) {
		
		this.byteStream = new ByteArrayOutputStream();
		this.printStream = new PrintStream(byteStream);
		PrintStream old = System.out;
		
		System.setOut(printStream);
		
		this.frontend = new FlightPlanFrontend(new Scanner(input), 
				new FDPlaceholderBackend());
		
		frontend.runCommandLoop();
		System.out.flush();
		System.setOut(old);
	}
	
	/**
	 * Takes output into the console and  puts it into an object that we can test
	 * 
	 * @return string representation of the console output
	 */
	public String checkOutput() {
		
		return byteStream.toString();
	}
	
	
}
