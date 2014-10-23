package playground.graphs.graph;

import javax.naming.OperationNotSupportedException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractGraph<V> implements Graph<V> {
  private final Set<V> vertices = new LinkedHashSet<V>();

  @Override
  public boolean addVertex(V vertex) throws OperationNotSupportedException {
    if (vertex == null)
      throw new OperationNotSupportedException("vertex can not be null");
    return vertices.add(vertex);
  }

  @Override
  public boolean containsVertex(V vertex) {
    return vertices.contains(vertex);
  }

  @Override
  public boolean deleteVertex(V vertex) {
    //TODO: delete edges from/into vertex
    return vertices.remove(vertex);
  }

  @Override
  public void clearVertex() {
    vertices.clear();
  }

  @Override
  public void clearAll() {
    clearVertex();
    clearEdges();
  }

  @Override
  public Iterable<V> getVertices() {
    return Collections.unmodifiableCollection(vertices);
  }

}
