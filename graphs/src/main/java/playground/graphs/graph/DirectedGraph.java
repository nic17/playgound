package playground.graphs.graph;

import javax.naming.OperationNotSupportedException;
import java.util.*;

/**
 * Created by bo on 10/22/14.
 */
public class DirectedGraph<V> extends AbstractGraph<V> {
    protected Map<V, Set<V>> adjacentMap = new LinkedHashMap<V, Set<V>>();

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
        return false;
    }

    @Override
    public boolean deleteEdge(V source, V target) throws OperationNotSupportedException {
        if (!this.containsVertex(source) || !this.containsVertex(target)) {
            throw new OperationNotSupportedException("vertex cannot be found in the graph");
        }
        return false;
    }

    @Override
    public void clearEdges() {
        adjacentMap.clear();
    }

    @Override
    public Iterable<V> getNeighbours(V vertex) {
        return Collections.unmodifiableCollection(adjacentMap.get(vertex));
    }
}
