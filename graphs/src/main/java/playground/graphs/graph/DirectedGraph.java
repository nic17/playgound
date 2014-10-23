package playground.graphs.graph;

import java.util.*;

@SuppressWarnings("unchecked")
public class DirectedGraph<V> extends AbstractGraph<V> {
  private final Map<V, Set<V>> outgoing = new LinkedHashMap<V, Set<V>>();
  private final Map<V, Set<V>> incoming = new LinkedHashMap<V, Set<V>>();

  @Override
  public boolean addEdge(V source, V target) {
    if (!this.containsVertex(source) || !this.containsVertex(target)) {
      throw VertexNotFoundException;
    }
    Set<V> outgoingNeighbours = outgoing.get(source);
    if (outgoingNeighbours == null) {
      outgoingNeighbours = new LinkedHashSet<V>();
      outgoing.put(source, outgoingNeighbours);
    }
    Set<V> incomingNeighbours = incoming.get(target);
    if (incomingNeighbours == null) {
      incomingNeighbours = new LinkedHashSet<V>();
      incoming.put(target, incomingNeighbours);
    }
    return outgoingNeighbours.add(target) && incomingNeighbours.add(source);
  }

  @Override
  public boolean containsEdge(V source, V target) {
    if (!this.containsVertex(source) || !this.containsVertex(target)) {
      throw VertexNotFoundException;
    }
    Set<V> neighbours = outgoing.get(source);
    return neighbours != null && neighbours.contains(target);
  }

  @Override
  public boolean deleteEdge(V source, V target) {
    if (containsEdge(source, target)) {
      outgoing.get(source).remove(target);
      incoming.get(target).remove(source);
      return true;
    }
    return false;
  }

  @Override
  public void clearEdges() {
    outgoing.clear();
    incoming.clear();
  }

  @Override
  public Iterable<V> getNeighbours(V vertex) {
    if (!this.containsVertex(vertex)) {
      throw VertexNotFoundException;
    }
    Set<V> neighbours = outgoing.get(vertex);
    return neighbours == null ? Collections.EMPTY_SET : Collections.unmodifiableCollection(neighbours);
  }

  @Override
  void deleteAssociatedEdges(V vertex) {
    if (incoming.containsKey(vertex)) {
      for (V inNeighbour : incoming.get(vertex)) {
        outgoing.get(inNeighbour).remove(vertex);
      }
      incoming.get(vertex).clear();
    }
    if (outgoing.containsKey(vertex)) {
      for (V outNeighbour : outgoing.get(vertex)) {
        incoming.get(outNeighbour).remove(vertex);
      }
      outgoing.get(vertex).clear();
    }
  }
}
