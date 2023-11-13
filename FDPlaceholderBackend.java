import java.util.ArrayList;


/**
 * Placeholder class for backend by frontend
 * @author Yajurva
 *
 */
public class FDPlaceholderBackend implements IFlightPlanBackend
{

	ArrayList airports; 
	
	public FDPlaceholderBackend() {
		this.airports = new ArrayList<>();
	}
	
	public boolean addAirport(String airport) {
		
		if(isAirportValid(airport)) {
			this.airports.add(airport);
			return true;
		}else {
			return false;
		}
	}
	
	
	private boolean isAirportValid(String airport) {
		if(airport.length() == 3) {
			return true;
		}else {
			return false;
		}
	}
	
public void addConnection(String airport, String connect, int dist, int cost) {
		
	}
	
	public boolean contains(String airport) {
		
		if(this.airports.contains(airport)){
			return true;
		}
		return false;
	}
	
	
	public boolean removeAirport(String airport) {
		if(isAirportValid(airport)) {
			return true;
		}
		return false;
	}
	
	public ArrayList getAllAirports() {
		return this.airports;
	}
	
	public ArrayList findMinFlightDistance(String start, String end) {
		ArrayList tempAirports = new ArrayList();
		tempAirports.add(start);
		tempAirports.add("LGA");
		tempAirports.add(end);
			
		return tempAirports;
	}
	
	public ArrayList findMinFlightCost(String start, String end) {
		
		ArrayList tempAirports = new ArrayList();
		tempAirports.add(start);
		tempAirports.add("LGA");
		tempAirports.add(end);
			
		return tempAirports;
	}

	@Override
	public boolean isValidDistance(String distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValidCost(String cost) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int findMinAllFlightCost(String startingAirport) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findMinAllFlightDistance(String startingAirport) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addAirport(String newAirport, String otherAirport, String weight) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(String sourceAirport, String targetAirport, String weight) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(String sourceAirport, String targetAirport) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList listAllAirports() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
