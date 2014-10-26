package playground.graphs.traverse;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playground.graphs.graph.DirectedGraphImpl;

import java.util.Iterator;

public class BreathFirstIteratorTest {
  private final String v[] = new String[10];
  private final DirectedGraphImpl<String> graph = new DirectedGraphImpl<String>();

  @BeforeClass
  public void init() {
    for (int i = 0; i < 10; i++) {
      v[i] = "vertex" + i;
      graph.addVertex(v[i]);
    }
    // root: 0
    // first level: 1, 2, 3, 4
    graph.addEdge(v[0], v[1]);
    graph.addEdge(v[0], v[2]);
    graph.addEdge(v[0], v[3]);
    graph.addEdge(v[0], v[4]);
    // second level: 5, 6, 7
    graph.addEdge(v[2], v[5]);
    graph.addEdge(v[2], v[6]);
    graph.addEdge(v[4], v[7]);
    // third level:8, 9
    graph.addEdge(v[5], v[8]);
    graph.addEdge(v[6], v[9]);
    // add sibling edges
    graph.addEdge(v[2], v[3]);
    graph.addEdge(v[2], v[4]);
    graph.addEdge(v[5], v[7]);
    // add back edges to 1 level higher
    graph.addEdge(v[6], v[3]);
    graph.addEdge(v[8], v[7]);
  }

  @Test
  public void testHasNext() throws Exception {
    Iterator<String> iter = new BreathFirstIterator<String>(graph, v[0]);
    Assert.assertTrue(iter.hasNext());
  }

  @Test
  public void testNext() throws Exception {
    Iterator<String> iter = new BreathFirstIterator<String>(graph, v[0]);
    for (int i = 0; i < 10; i++) {
      Assert.assertEquals(iter.next(), v[i]);
    }
    Assert.assertFalse(iter.hasNext());
  }
}