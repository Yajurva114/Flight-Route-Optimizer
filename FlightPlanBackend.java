import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FlightPlanBackend implements IFlightPlanBackend {
    
    ArrayList<String> allAirports = new ArrayList<>();
    BDGraphExtension<String, Integer> distanceMap = new BDGraphExtension<>();
    BDGraphExtension<String, Integer> costMap = new BDGraphExtension<>();

    public FlightPlanBackend(IFlightLoader loader) {
        try {

            List<String> airports = loader.loadAirport(null);
            List<String[]> distanceEdges = loader.loadTimeEdges(null);
            List<String[]> costEdges = loader.loadCostEdges(null);

            for (String airport : airports) {
                this.allAirports.add(airport);
                this.distanceMap.insertVertex(airport);
                this.costMap.insertVertex(airport);
            }

            for (String[] edge : distanceEdges) {
                this.distanceMap.insertEdge(edge[0], edge[1], Integer.parseInt(edge[2]));
            }

            for (String[] edge : costEdges) {
                this.costMap.insertEdge(edge[0], edge[1], Integer.parseInt(edge[2].substring(1)));
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isValidDistance(String distance) {
        
        if (distance == null) return false;

        try {
            Integer i = Integer.parseInt(distance.trim());
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidCost(String cost) {
        
        if (cost == null || cost.length() <= 1) return false;

        if (cost.trim().charAt(0) != '$') return false;

        try {
            Integer i = Integer.parseInt(cost.trim().substring(1));
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    @Override
    public int findMinAllFlightCost(String startingAirport) {

        try {
            return costMap.findMinSpanTree(startingAirport);
        } catch(NoSuchElementException e) {
            return 0;
        }

    }

    @Override
    public int findMinAllFlightDistance(String startingAirport) {

        try {
            return distanceMap.findMinSpanTree(startingAirport);
        } catch(NoSuchElementException e) {
            return 0;
        }

    }

    @Override
    public ArrayList findMinFlightCost(String startingAirport, String endAirport) {

        try { 
            return new ArrayList<>(costMap.shortestPath(startingAirport, endAirport));
        } catch(NoSuchElementException e) {
            return null;
        }

    }

    @Override
    public ArrayList findMinFlightDistance(String startingAirport, String endAirport) {

        try {
            return new ArrayList<>(distanceMap.shortestPath(startingAirport, endAirport));
        } catch(NoSuchElementException e) {
            return null;
        }

    }

    @Override
    public boolean addAirport(String newAirport) {

        try {
            if (costMap.insertVertex(newAirport) && distanceMap.insertVertex(newAirport)) {
                allAirports.add(newAirport);
                return true;
            } else
                return false;
        } catch(NullPointerException e) {
            return false;
        }
        
    }

    @Override
    public boolean addAirport(String newAirport, String otherAirport, String weight) {

        try {
            if (isValidDistance(weight)) {
                Integer distance = Integer.parseInt(weight.trim());
                addAirport(newAirport);
                if (distanceMap.insertEdge(newAirport, otherAirport, distance))
                    return true;
                else
                    return false;
            } else if (isValidCost(weight)) {
                Integer cost = Integer.parseInt(weight.trim().substring(1));
                addAirport(newAirport);
                if (costMap.insertEdge(newAirport, otherAirport, cost))
                    return true;
                else
                    return false;
            } else {
                return false;
            }
        } catch(NullPointerException | IllegalArgumentException e) {
            return false;
        }

    }

    @Override
    public boolean removeAirport(String removedAirport) {

        try {
            if (distanceMap.removeVertex(removedAirport) && costMap.removeVertex(removedAirport)) {
                allAirports.remove(removedAirport);
                return true;
            } else
                return false;
        } catch(NullPointerException e) {
            return false;
        }

    }

    @Override
    public boolean addEdge(String sourceAirport, String targetAirport, String weight) {

        try {
            if (isValidDistance(weight)) {
                Integer distance = Integer.parseInt(weight.trim());
                if (distanceMap.insertEdge(sourceAirport, targetAirport, distance))
                    return true;
                else
                    return false;
            } else if (isValidCost(weight)) {
                Integer cost = Integer.parseInt(weight.trim().substring(1));
                if (costMap.insertEdge(sourceAirport, targetAirport, cost))
                    return true;
                else
                    return false;
            } else {
                return false;
            }
        } catch(NullPointerException | IllegalArgumentException e) {
            return false;
        }

    }

    @Override
    public boolean removeEdge(String sourceAirport, String targetAirport) {

        try {
            boolean a = distanceMap.removeEdge(sourceAirport, targetAirport);
            boolean b = costMap.removeEdge(sourceAirport, targetAirport);
            if (a || b) return true;
        } catch(NullPointerException | IllegalArgumentException e) {
            return false;
        }

        return false;
        
    }

    @Override
    public ArrayList listAllAirports() {
        return allAirports;
    }
    
}
