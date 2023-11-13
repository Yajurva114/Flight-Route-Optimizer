import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tester class for Frontend developer
 * @author Yajurva
 *
 */
public class FrontendDeveloperTests {

	
	/**
	 * Tests the addition of airports in the backend and checks the UI Response to the same
	 */
	@Test
	public void testAddAirport() {
		
		InputOutput textUITester = new InputOutput("1\n   ORG\n   n\n 6\n");
		
		String testerOutput = textUITester.checkOutput();
		
		assertEquals(true, testerOutput.contains("Enter Name:")); // makes sure that the mainMenu() goes into case 1
		assertEquals(true, testerOutput.contains("Do you wish to enter connection details for this airport?(y/n)")); // checks for correct output 
																											   // after adding airport
		assertEquals(true, testerOutput.contains("Airport Saved!"));
	}
	
	/**
	 * Tests the removal of airports in the backend and checks the UI Response to the same
	 */
	@Test
	public void testRemoveAirport() {
		
		InputOutput textUITester = new InputOutput("2\n   LGA\n   6\n");// simulates removing a valid airport
		
		String testerOutput = textUITester.checkOutput();

		assertEquals(true, testerOutput.contains("Enter Name:"));
		assertEquals(true, testerOutput.contains("Airport Removed!")); // simuates successful removal
		
		 textUITester = new InputOutput("2\n   ORBIT\n   6\n"); // tests output when the airport does not exist
		 testerOutput = textUITester.checkOutput();
		 assertEquals(true, testerOutput.contains("There is no airport with that name"));
		 assertEquals(true, testerOutput.contains("Goodbye!"));
		
	}
	
	/**
	 * Tests if the UI response of the finding shortest distance is accurate
	 */
	@Test
	public void testShortestPathBetweenAirports() {
		
		
		
		InputOutput textUITester = new InputOutput("4\n   ORD\n    BOS\n   6\n");
		
		String testerOutput = textUITester.checkOutput();
		String expectedPath = "ORD LGA BOS";
		
		
		assertEquals(true, testerOutput.contains(expectedPath));
		assertEquals(true, testerOutput.contains("Enter point A:"));
		assertEquals(true, testerOutput.contains("Enter point B:"));
		

		
	}
	
	/**
	 * Tests the output if an invalid input has been passed
	 */
	@Test
	public void testInvalidInput() {
		InputOutput textUITester = new InputOutput("16\n Exit\n");

		String output = textUITester.checkOutput();
		
		assertEquals(true, output.contains("Please enter a valid choice")); 
		
		assertEquals(true, output.contains("Goodbye!"));
	}
	
	/**
	 * Tests the UI response of the method displayAllAirports.
	 */
	@Test
	public void testDisplayAirports() {
		
		Scanner scnr = new Scanner(System.in);
		FDPlaceholderBackend backend = new FDPlaceholderBackend();
		FlightPlanFrontend frontend = new FlightPlanFrontend(scnr, backend);
		
		backend.airports.add("LGA");
		backend.airports.add("MKE");
		backend.airports.add("BOS");
		backend.airports.add("LAX");
		backend.airports.add("JFK");
		
		PrintStream displayedTexts = System.out;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteStream);
		
		System.setOut(printStream);
		
		frontend.displayAirports(backend.airports);
		String output = "LGA MKE BOS LAX JFK \n"
				+ "";
		
		
		
		System.out.flush();
		System.setOut(displayedTexts);
		
		assertEquals(output, byteStream.toString());
		
		
	}
	
	/**
	 * To test if backend's add airport is working perfectly in sync with frontend's display all airports method.
	 */
	@Test
	public void integrationTest1() {
		
		FlightPlanBackend backend = new FlightPlanBackend(new BDFlightLoader());
		Scanner scnr = new Scanner(System.in);
		FlightPlanFrontend frontend = new FlightPlanFrontend(scnr, backend);
		
		backend.addAirport("AIS");
		backend.addAirport("MKE");
		backend.addAirport("DCB");
		
		PrintStream displayedTexts = System.out;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteStream);
		
		System.setOut(printStream);
		
		frontend.displayAirports(backend.listAllAirports());
		
		String output = "LA SFO ORD CHI OWC AIS MKE DCB \n"
				+ "";
		
		System.out.flush();
		System.setOut(displayedTexts);
		
		
		Assertions.assertEquals(output, byteStream.toString());
		
	}
	
	/**
	 * Testing removeAirport() function of the backend along with frontends code. 
	 */
	@Test
	public void integrationTest2() {
		
		FlightPlanBackend backend = new FlightPlanBackend(new BDFlightLoader());
		Scanner scnr = new Scanner(System.in);
		FlightPlanFrontend frontend = new FlightPlanFrontend(scnr, backend);
		
		backend.removeAirport("LA");
		backend.removeAirport("SFO");
		backend.removeAirport("ORD");
		
		PrintStream displayedTexts = System.out;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteStream);
		
		System.setOut(printStream);
		
		frontend.displayAirports(backend.listAllAirports());
		
		String output = "CHI OWC \n"
;
		
		System.out.flush();
		System.setOut(displayedTexts);
		
		System.out.println(byteStream.toString());
		
		Assertions.assertEquals(output, byteStream.toString());
		
		
		
	}
	
	/**
	 * To test findMinDistance() and findMinCost() functions of the backend
	 */
	@Test
	public void CodeReviewOfBackend() {
		
		FlightPlanBackend backend = new FlightPlanBackend(new BDFlightLoader());

        ArrayList<String> shortestRoute = new ArrayList<>();

        shortestRoute.add("LA");
        shortestRoute.add("SFO");
        shortestRoute.add("ORD");

        Assertions.assertEquals(shortestRoute,backend.findMinFlightCost("LA", "ORD"));
        Assertions.assertEquals(shortestRoute,backend.findMinFlightDistance("LA", "ORD"));
        
       

        Assertions.assertEquals(backend.findMinFlightCost("ORD", "LA"), null);

	}
	
	/**
	 * To test listAllAirports() function of the backend
	 */
	@Test
	public void CodeReviewOfBackend2() {
		
		
		FlightPlanBackend backend = new FlightPlanBackend(new BDFlightLoader());

        ArrayList<String> airports = new ArrayList<String>();

        airports.add("LA");
        airports.add("SFO");
        airports.add("ORD");
        airports.add("CHI");
        airports.add("OWC");

        Assertions.assertTrue(backend.listAllAirports().equals(airports));

	}
	
}
