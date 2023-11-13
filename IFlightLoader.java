import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Instances of this interface can be used to read airport vertexes and edges of a
 * graph from a DOT file
 */
public interface IFlightLoader {

  /**
   * This method find all airport(vertexes) of a graph from the given dot file and return a
   * list that contains all airports' name as String
   *
   * @param filePathToDOT path to the DOT file relative to the executable
   * @return a list of String contains all airports' name
   * @throws FileNotFoundException
   */
  List<String> loadAirport(String filePathToDOT) throws FileNotFoundException;

  /**
   * This method find all edges of a graph from the given dot file and return a list that contains
   * String arrays that each element represent an edge. The weight string of each edge should
   * start with $
   *
   * @param filePathToDOT path to the DOT file relative to the executable
   * @return a list of String array. the first element will be the starting node, the second element
   * will be the destination node, and the last element will be the weight
   * @throws FileNotFoundException
   */
  List<String[]> loadCostEdges(String filePathToDOT) throws FileNotFoundException;

  /**
   * This method find all edges of a graph from the given dot file and return a list that contains
   * String arrays that each element represent a edge. The weight string of each edge should be an
   * integer
   *
   * @param filePathToDOT path to the DOT file relative to the executable
   * @return a list of String array. the first element will be the starting node, the second element
   * will be the destination node, and the last element will be the weight
   * @throws FileNotFoundException
   */
  List<String[]> loadTimeEdges(String filePathToDOT) throws FileNotFoundException;

}

