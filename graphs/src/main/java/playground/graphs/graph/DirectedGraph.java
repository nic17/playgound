package playground.graphs.graph;

import javax.naming.OperationNotSupportedException;
import java.util.*;

@SuppressWarnings("unchecked")
public class DirectedGraph<V> extends AbstractGraph<V> {
  private final Map<V, Set<V>> adjacentMap = new LinkedHashMap<V, Set<V>>();

  @Override
  public boolean addEdge(V source, V target) throws OperationNotSupportedException {
    if (!this.containsVertex(source) || !this.containsVertex(target)) {
      throw new OperationNotSupportedException("vertex cannot be found in the graph");
    }
    Set<V> neighbours = adjacentMap.get(source);
    if (neighbours == null) {
      neighbours = new LinkedHashSet<V>();
      adjacentMap.put(source, neighbours);
    }
    return neighbours.add(target);
  }

  @Override
  public boolean containsEdge(V source, V target) throws OperationNotSupportedException {
    if (!this.containsVertex(source) || !this.containsVertex(target)) {
      throw new OperationNotSupportedException("vertex cannot be found in the graph");
    }
    Set<V> neighbours = adjacentMap.get(source);
    return neighbours != null && neighbours.contains(target);
  }

  @Override
  public boolean deleteEdge(V source, V target) throws OperationNotSupportedException {
    if (!this.containsVertex(source) || !this.containsVertex(target)) {
      throw new OperationNotSupportedException("vertex cannot be found in the graph");
    }
    Set<V> neighbours = adjacentMap.get(source);
    return neighbours != null && neighbours.remove(target);
  }

  @Override
  public void clearEdges() {
    adjacentMap.clear();
  }

  @Override
  public Iterable<V> getNeighbours(V vertex) {
    Set<V> neighbours = adjacentMap.get(vertex);
    return neighbours == null ? Collections.EMPTY_SET : Collections.unmodifiableCollection(neighbours);
  }
}
