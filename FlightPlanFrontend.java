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
import java.util.ArrayList;
import java.util.Scanner;

public class FlightPlanFrontend implements IFlightPlanFrontend {

	private Scanner scnr;
	private IFlightPlanBackend backend;
	private boolean quit = false;

	/**
	 * Public constructor method for frontend
	 * 
	 * @param userInputScanner
	 * @param backend
	 */
	public FlightPlanFrontend(Scanner userInputScanner, IFlightPlanBackend backend) {
		this.scnr = userInputScanner;
		this.backend = backend;

	}

	/**
	 * Main function that is responsible for running the app
	 */
	@Override
	public void runCommandLoop() {

		if (this.quit) {
			System.out.println("Goodbye!");
		} else {
			System.out.println("Welcome to Flight Plan Application!");
			System.out.println("_______________________________________");
			System.out.println();

			displayMainMenu();
		}

	}

	/**
	 * Recursive function that acts as the user interface
	 */
	@Override
	public void displayMainMenu() {

		System.out.println("Flight Plan Main Menu:");
		System.out.println("1) Add an Airport");
		System.out.println("2) Remove an Airport");
		System.out.println("3) Print all Airports");
		System.out.println("4) Find shortest flight path between Cities");
		System.out.println("5) Find cheapest flight path between Cities");
		System.out.println("6) Exit");
		System.out.println();

		switch (scnr.nextLine().trim().toUpperCase()) {
		case "1":
		case "ADD AN AIRPORT":

			String airport;
			int attempts = 0;

			do {
				System.out.println("Enter Name:");
				airport = scnr.nextLine().trim();
				attempts++;

				if (backend.allAirports.contains(airport)) {
					System.out.println("This airport already exists");

				}
			} while (!(backend.addAirport(airport)));

			System.out.println("Do you wish to enter connection details for this airport?(y/n)");
			switch (scnr.nextLine().trim().toUpperCase()) {
			case "Y":
				System.out.println("Enter airport to connect to:");
				String connection = scnr.nextLine().trim().toUpperCase();
				if (!backend.allAirports.contains(connection)) {
					System.out.println("Connecting airport does not exist");
				} else {

					System.out.println("Enter Distance or Cost:");
					String distCost = scnr.nextLine().trim();

					backend.addAirport(airport, connection, distCost);
					System.out.println("Airport Saved!");

				}
				break;

			case "N":
				System.out.println("Airport Saved!");
				break;
			}
			break;

		case "2":
		case "REMOVE AN AIRPORT":
			String airportToRemove;

			System.out.println("Enter Name:");
			airportToRemove = scnr.nextLine().trim().toUpperCase();

			if (backend.removeAirport(airportToRemove)) {
				System.out.println("Airport Removed!");
			} else {
				System.out.println("There is no airport with that name");
			}
			break;

		case "3":
		case "PRINT ALL AIRPORTS":

			System.out.println("All the airports stored are:");
			System.out.println();
			displayAirports(backend.listAllAirports());
			break;

		case "4":
		case "FIND SHORTEST FLIGHT PATH BETWEEN CITIES":
			String start;
			String end;

			System.out.println("Enter point A:");
			start = scnr.nextLine().toUpperCase().trim();
			System.out.println(backend.allAirports);
				if (!backend.allAirports.contains(start)) {
					System.out.println("Airport is not valid!");
					break;
				}
			

			System.out.println();
			System.out.println("Enter point B:");
			end = scnr.nextLine().toUpperCase().trim();

			if (!(backend.getClass().getName().equals("FDPlaceholderBackend"))) {
				if (!backend.allAirports.contains(end)) {
					System.out.println("Airport is not valid!");
					break;
				}
			}

			System.out.println();
			displayAirports(backend.findMinFlightDistance(start, end));

			break;
		case "5":
		case "FIND CHEAPEST FLIGHT PATH BETWEEN CITIES":
			String cheapStart;
			String cheapEnd;

			System.out.println("Enter point A:");
			cheapStart = scnr.nextLine().trim().toUpperCase();
			if (!backend.allAirports.contains(cheapStart)) {
				System.out.println("Airport is not valid!");
			} else {
				System.out.println();
				System.out.println("Enter point B:");
				cheapEnd = scnr.nextLine().trim().toUpperCase();
				if (!backend.allAirports.contains(cheapEnd)) {
					System.out.println("Airport is not valid!");
				} else {

					displayAirports(backend.findMinFlightCost(cheapStart, cheapEnd));

				}

			}
			break;
		case "6":
		case "EXIT":
			System.out.println("Goodbye!");
			quit = true;
			scnr.close();
			return;
		default:
			System.out.println("Please enter a valid choice");
		}
		System.out.println();
		displayMainMenu();

	}

	/**
	 * Method used to display all airports in the database.
	 */
	@Override
	public void displayAirports(ArrayList airports) {

		if (airports != null || airports.size() != 0) {
			for (int i = 0; i < airports.size(); i++) {
				if (airports.get(i) != null) {
					System.out.print(airports.get(i) + " ");
				}
			}

			System.out.println();

		} else {

			System.out.println("No airports to display");

			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		FlightPlanBackend bd = new FlightPlanBackend(new BDFlightLoader());
		FlightPlanFrontend fd = new FlightPlanFrontend(scnr, bd);

		fd.runCommandLoop();
	}

}
