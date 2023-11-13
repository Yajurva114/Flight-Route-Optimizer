import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BDFlightLoader implements IFlightLoader {

    @Override
    public List<String> loadAirport(String filePathToDOT) throws FileNotFoundException {

        List<String> airports = new ArrayList<String>();

        airports.add("LA");
        airports.add("SFO");
        airports.add("ORD");
        airports.add("CHI");
        airports.add("OWC");
        
        

        return airports;
    }

    @Override
    public List<String[]> loadCostEdges(String filePathToDOT) throws FileNotFoundException {

        List<String[]> costEdges = new ArrayList<String[]>();

        costEdges.add(new String[] {"LA","SFO","$150"});
        costEdges.add(new String[] {"SFO","ORD","$100"});
        costEdges.add(new String[] {"SFO","CHI","$160"});
        costEdges.add(new String[] {"CHI","OWC","$120"});
        costEdges.add(new String[] {"OWC","ORD","$120"});

        return costEdges;
    }

    @Override
    public List<String[]> loadTimeEdges(String filePathToDOT) throws FileNotFoundException {

        List<String[]> distanceEdges = new ArrayList<String[]>();

        distanceEdges.add(new String[] {"LA","SFO","150"});
        distanceEdges.add(new String[] {"SFO","ORD","100"});
        distanceEdges.add(new String[] {"SFO","CHI","160"});
        distanceEdges.add(new String[] {"CHI","OWC","120"});
        distanceEdges.add(new String[] {"OWC","ORD","120"});

        return distanceEdges;

    }
    
}

