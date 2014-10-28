package playground.graphs.algrithm;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playground.graphs.graph.DirectedGraphImpl;
import playground.graphs.graph.WeightedDirectedGraph;
import playground.graphs.graph.WeightedDirectedGraphImpl;

import java.util.*;

public class BellmanFordShortestPathTest {
  private final String v[] = new String[10];
  private final WeightedDirectedGraph<String> graph = new WeightedDirectedGraphImpl<>(new DirectedGraphImpl<String>());

  @BeforeClass
  public void init() {

    List<Integer> tenInt = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      tenInt.add(i);
    }
    List<Integer> anotherTenInt = new ArrayList<>(tenInt);
    Random random = new Random();
    Collections.shuffle(tenInt, random);
    Collections.shuffle(anotherTenInt, random);
    for (Integer i : tenInt) {
      v[i] = "vertex" + i;
      graph.addVertex(v[i]);
    }
    /*
      fully connected graph, weight(i,j) > |i-j|
     */
    Collections.shuffle(tenInt, random);
    for (Integer i : tenInt) {
      Collections.shuffle(anotherTenInt, random);
      for (Integer j : anotherTenInt) {
        if (!i.equals(j)) {
          graph.addEdge(v[i], v[j]);
          graph.setWeight(v[i], v[j], random.nextInt(20) + Math.abs(i - j) + 1);
        }
      }
    }

    // make 0,1,2,3,...,9 be the shortest path to 9
    for (int i = 1; i < 10; i++) {
      graph.setWeight(v[i - 1], v[i], 1);
    }
  }

  @Test
  public void testGetPathTotalWeights() throws Exception {
    Assert.assertEquals(new BellmanFordShortestPath<>(graph, v[0]).getPathTotalWeights(v[9]).intValue(), 9);
  }

  @Test
  public void testGetPath() throws Exception {
    Iterator<String> iter = new BellmanFordShortestPath<>(graph, v[0]).getPath(v[9]).iterator();
    for (int i = 0; i < 10; i++) {
      Assert.assertEquals(iter.next(), v[i]);
    }
    Assert.assertFalse(iter.hasNext());
  }

  @Test
  public void testNoPath() throws Exception {
    String unconnectedVertex = "no path";
    graph.addVertex(unconnectedVertex);
    BellmanFordShortestPath<String> shortestPath = new BellmanFordShortestPath<>(graph, v[0]);
    Assert.assertEquals(shortestPath.getPathTotalWeights(unconnectedVertex).intValue(), Integer.MAX_VALUE);
    Iterator<String> iter = shortestPath.getPath(unconnectedVertex).iterator();
    Assert.assertFalse(iter.hasNext());
  }
}