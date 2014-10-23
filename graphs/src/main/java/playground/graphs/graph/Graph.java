package playground.graphs.graph;

interface Graph<V> {
  public boolean addVertex(V vertex);

  public boolean containsVertex(V vertex);

  public boolean deleteVertex(V vertex);

  public boolean addEdge(V source, V target);

  public boolean containsEdge(V source, V target);

  public boolean deleteEdge(V source, V target);

  public void clearEdges();

  public void clearVertex();

  public void clearAll();

  public Iterable<V> getNeighbours(V vertex);

  public Iterable<V> getVertices();
}
