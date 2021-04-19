package ds;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
 * UNCOMMENTED LINE#21 IN GRAPH.JAVA
 *  * UNCOMMENTED LINE#21 IN GRAPH.JAVA
 *   * UNCOMMENTED LINE#21 IN GRAPH.JAVA
 *    * UNCOMMENTED LINE#21 IN GRAPH.JAVA
 *     * UNCOMMENTED LINE#21 IN GRAPH.JAVA
 */

public class CheckNumEdges {

	private static int dfsInner(Graph g, int v, boolean[] visited, int parent, Map<Integer, LinkedList<Integer>> incident) {
		//store the incident node
		//check if there is already a list of incident nodes, if not, create one
		if (incident.containsKey(v)) {
			incident.get(v).add(parent);
		} else {
			LinkedList<Integer> incidentVertices = new LinkedList<Integer> (); //create a list of incident vertices
			incidentVertices.add(parent); //add v to the list
			incident.put(v, incidentVertices); //store the incident node
		}
		
		//count the edges in the subtree of v
		int edges = 0; 
		if (parent != -1) { //if we are not starting from this node
			edges = 1; //edge from parent to v
		}
		
		//check if node has been visited
		if (visited[v] == true) {
			return edges; //return an empty string
		} else {
			visited[v] = true; //mark the node as visited
			DoublyLinkedList<Integer>.Node node = g.adjacencyList[v].headNode; //get the first edge node
			while (node != null) {
				//if this node is not already an incident node, traverse deeper
				if (!incident.get(v).contains(node.data)) {
					edges += dfsInner(g, node.data, visited, v, incident); //append child node to the traversal path
				}
				node = node.nextNode;
			}
			return edges; //return the traversal path of the subtree at v 
		}
	}
	
	public static int numEdges(Graph g) {
		boolean[] visited = new boolean[g.vertices]; //vertices visited
		Map<Integer, LinkedList<Integer>> incident = new HashMap<Integer, LinkedList<Integer>> ();
		int edges = 0;
		//iterate over all nodes
		for (int i = 0; i < g.vertices; i++) {
			edges += dfsInner(g, i, visited, -1, incident);
		}
		
		return edges;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(2, 5);
		g.addEdge(5, 0);
		System.out.println(numEdges(g));
		
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 3);
		g2.addEdge(2, 4);
		System.out.println(numEdges(g2));
		
		Graph g3 = new Graph(5);
		g3.addEdge(0, 1);
		g3.addEdge(0, 2);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		System.out.println(numEdges(g3));
	}

}
