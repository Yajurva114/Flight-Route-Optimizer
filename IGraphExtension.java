import java.util.ArrayList;

/**
 * This class extends the functionality of the graph data structure. The add/remove and
 * Dijkstra's algorithm will be modified from the role code implementation.
 */
public interface IGraphExtension<NodeType> {

  /**
   * Finds the total cost of a minimum spanning tree that goes through all airports.
   * @return Total cost of minimum spanning tree that goes through all airports.
   */
  public int findMinSpanTree(NodeType start);

}

