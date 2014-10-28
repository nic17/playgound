package playground.graphs.graph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class AbstractGraph<V> implements Graph<V> {
  private final Set<V> vertices = new LinkedHashSet<>();

  @Override
  public final boolean addVertex(V vertex) {
    if (vertex == null) {
      throw VertexISNullException;
    }
    return vertices.add(vertex);
  }

  @Override
  public final boolean containsVertex(V vertex) {
    if (vertex == null)
      throw VertexISNullException;
    return vertices.contains(vertex);
  }

  abstract void deleteAssociatedEdges(V vertex);

  @Override
  public final boolean deleteVertex(V vertex) {
    if (containsVertex(vertex)) {
      deleteAssociatedEdges(vertex);
    }
    return vertices.remove(vertex);
  }

  @Override
  public final void clearVertex() {
    vertices.clear();
  }

  @Override
  public final void clearAll() {
    clearVertex();
    clearEdges();
  }

  @Override
  public final Iterable<V> getVertices() {
    return Collections.unmodifiableCollection(vertices);
  }

}
