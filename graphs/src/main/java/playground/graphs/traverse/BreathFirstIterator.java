package playground.graphs.traverse;

import playground.graphs.graph.AbstractGraph;
import playground.graphs.graph.DirectedGraphImpl;

import java.util.*;

public class BreathFirstIterator<V> implements Iterator<V> {
  private static final UnsupportedOperationException RemoveNotSupportedException =
      new UnsupportedOperationException("remove is not supported in GraphIterator");

  private final DirectedGraphImpl<V> graph;
  private final Queue<V> que = new ArrayDeque<>();
  private final Set<V> inQueue = new HashSet<>();

  public BreathFirstIterator(DirectedGraphImpl<V> graph, V root) {
    this.graph = graph;
    if (graph.containsVertex(root)) {
      que.add(root);
      inQueue.add(root);
    } else {
      throw AbstractGraph.VertexNotFoundException;
    }
  }

  @Override
  public boolean hasNext() {
    return !que.isEmpty();
  }

  @Override
  public V next() {
    if (que.isEmpty()) {
      return null;
    }
    V cur = que.remove();
    // add neighbours to the queue
    for (V neighbour : graph.getNeighbours(cur)) {
      // new vertex
      if (!inQueue.contains(neighbour)) {
        que.add(neighbour);
        inQueue.add(neighbour);
      }
    }
    return cur;
  }

  @Override
  public void remove() {
    throw RemoveNotSupportedException;
  }
}
