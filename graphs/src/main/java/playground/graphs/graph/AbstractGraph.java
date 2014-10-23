package playground.graphs.graph;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by bo on 10/22/14.
 */
public abstract class AbstractGraph<V> implements Graph<V> {
    protected Set<V> vertices = new LinkedHashSet<V>();

    @Override
    public boolean addVertex(V vertex) {
        return vertices.add(vertex);
    }

    @Override
    public boolean containsVertex(V vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public boolean deleteVertex(V vertex) {
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
