package playground.graphs.graph;

import javax.naming.OperationNotSupportedException;

/**
 * Created by bo on 10/22/14.
 */
public interface Graph<V> {
    public boolean addVertex(V vertex);

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
