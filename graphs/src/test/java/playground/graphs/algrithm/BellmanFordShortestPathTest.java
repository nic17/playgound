package playground.graphs.algrithm;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import playground.graphs.graph.DirectedGraph;
import playground.graphs.graph.WeightedGraph;

import java.util.Random;

public class BellmanFordShortestPathTest {
  private final String v[] = new String[10];
  private final WeightedGraph<String, Integer> graph = new WeightedGraph<String, Integer>(new DirectedGraph<String>());

  @BeforeClass
  public void init() {
    for (int i = 0; i < 10; i++) {
      v[i] = "vertex" + i;
      graph.addVertex(v[i]);
    }
    /*
      fully connected graph, weight(i,j) > |i-j|
     */
    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (i != j) {
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

  }

  @Test
  public void testGetPath() throws Exception {
    Assert.assertEquals(new BellmanFordShortestPath(graph, v[0]).getPathTotalWeights(v[9]), 9);
  }
}