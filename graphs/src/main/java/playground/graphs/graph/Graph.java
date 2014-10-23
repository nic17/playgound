package playground.graphs.graph;

import javax.naming.OperationNotSupportedException;

interface Graph<V> {
  public boolean addVertex(V vertex) throws OperationNotSupportedException;

  public boolean containsVertex(V vertex);

  public boolean deleteVertex(V vertex);

  public boolean addEdge(V source, V target) throws OperationNotSupportedException;

  public boolean containsEdge(V source, V target) throws OperationNotSupportedException;

  public boolean deleteEdge(V source, V target) throws OperationNotSupportedException;

  public void clearEdges();

  public void clearVertex();

  public void clearAll();

  public Iterable<V> getNeighbours(V vertex);

  public Iterable<V> getVertices();
}
