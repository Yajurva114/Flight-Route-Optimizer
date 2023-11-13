import java.util.ArrayList;
import java.util.Arrays;

/**
 * This interface describes the functionality of the backend for the simulated flight plan. It
 * will have the capabilities of finding the MST of flights, finding the shortest distance/cost
 * between two airports, add/remove an airport or connection between airports.
 */
public interface IFlightPlanBackend {
	
	ArrayList<String> allAirports = new ArrayList<>(Arrays.asList("LA"));
	 
   /**
    * Checks the validity of the distance as a string can turn into a double
    *
    * @param distance string form of distance
    * @return true if it is a valid distance. Otherwise, return false
    */
   public boolean isValidDistance(String distance);

   /**
    * Checks the validity of the cost as a string can turn into a double
    *
    * @param cost string form of cost
    * @return true if it is a valid cost. Otherwise, return false
    */
   public boolean isValidCost(String cost);

   /**
    * Given a starting airport, finds the minimum spanning tree based on the cost as the weight.
    *
    * @param startingAirport airport to start traversal
    * @return an arraylist of airports in the order of traversal
    */
   public int findMinAllFlightCost(String startingAirport);

   /**
    * Given a starting airport, finds the minimum spanning tree based on the distance as the weight.
    *
    * @param startingAirport airport to start traversal
    * @return an arraylist of airports in the order of traversal
    */
   public int findMinAllFlightDistance(String startingAirport);

   /**
    * Using a starting airport and an ending airport, finds the cheapest cost between the two
    * airports.
    *
    * @param startingAirport airport to start the path
    * @param endAirport airport to end the path
    * @return an arraylist of airports that indicate the cheapest path between the starting airport
    *         and ending airport
    */
   public ArrayList findMinFlightCost(String startingAirport, String endAirport);

   /**
    * Using a starting airport and an ending airport, finds the shortest distance between the two
    * airports.
    *
    * @param startingAirport airport to start the path
    * @param endAirport airport to end the path
    * @return an arraylist of airports that indicate the shortest path between the starting airport
    *         and ending airport
    */
   public ArrayList findMinFlightDistance(String startingAirport, String endAirport);

   /**
    * Adds a new airport to the graph without any connections. No duplicates allowed.
    *
    * @param newAirport the airport to be added
    * @return true if the airport was successfully added. Otherwise, returns false
    */
   public boolean addAirport(String newAirport);

   /**
    * Adds a new airport to the graph with one connection to other airport. No duplicates
    * allowed.
    *
    * @param newAirport the airport to be added
    * @param otherAirport the other airport to connect an edge from the new airport
    * @param weight the weight of the edge
    * @return true if the airport was successfully added. Otherwise, returns false
    */
   public boolean addAirport(String newAirport, String otherAirport, String weight);

   /**
    * Removes an airport in the graph.
    *
    * @param removedAirport the airport to be removed
    * @return true if the airport was successfully removed. Otherwise, returns false
    */
   public boolean removeAirport(String removedAirport);

   /**
    * Add an edge from the graph.
    *
    * @param sourceAirport the source airport contained in the source vertex for the edge
    * @param targetAirport the target airport contained in the target vertex for the edge
    * @param weight the weight of the edge
    * @return true if the edge could be added, false if it was already in the graph
    */
   public boolean addEdge(String sourceAirport, String targetAirport, String weight);

   /**
    * Remove an edge from the graph.
    *
    * @param sourceAirport the source airport contained in the source vertex for the edge
    * @param targetAirport the target airport contained in the target vertex for the edge
    * @return true if the edge could be removed, false if it was not in the graph
    */
   public boolean removeEdge(String sourceAirport, String targetAirport);

   /**
    * List all the airports in the graph.
    *
    * @return an arraylist containing all the airports in the graph
    */
   public ArrayList listAllAirports();
}

