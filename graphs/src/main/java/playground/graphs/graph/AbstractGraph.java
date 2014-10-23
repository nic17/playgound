package playground.graphs.graph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractGraph<V> implements Graph<V> {
  public static final UnsupportedOperationException VertexNotFoundException =
      new UnsupportedOperationException("vertex can not be found in the graph");
  private static final UnsupportedOperationException VertexISNullException =
      new UnsupportedOperationException("vertex can not be null");
  private final Set<V> vertices = new LinkedHashSet<V>();

  @Override
  public boolean addVertex(V vertex) {
    if (vertex == null) {
      throw VertexISNullException;
    }
    return vertices.add(vertex);
  }

  @Override
  public boolean containsVertex(V vertex) {
    if (vertex == null)
      throw VertexISNullException;
    return vertices.contains(vertex);
  }

  abstract void deleteAssociatedEdges(V vertex);

  @Override
  public boolean deleteVertex(V vertex) {
    if (containsVertex(vertex)) {
      deleteAssociatedEdges(vertex);
    }
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
