package playground.graphs.graph;

interface Graph<V> {
  static final UnsupportedOperationException VertexNotFoundException =
      new UnsupportedOperationException("vertex can not be found in the graph");
  static final UnsupportedOperationException VertexISNullException =
      new UnsupportedOperationException("vertex can not be null");
  static final UnsupportedOperationException CannotModifyThroughResidualGraph = new UnsupportedOperationException("Cannot modify through residual graph");
  static final UnsupportedOperationException UnsupportedThroughResidualGraph = new UnsupportedOperationException("Cannot perform this operation through residual graph");
  static final UnsupportedOperationException EdgeNotFoundException = new UnsupportedOperationException("Edge cannot be found");
  static final UnsupportedOperationException WeightNotFoundException = new UnsupportedOperationException("Weight cannot be found");


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
