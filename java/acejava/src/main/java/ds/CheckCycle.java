package ds;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckCycle {
	
	private static List<Integer> getAncestors(Map<Integer, Integer> mapParent, int v) {
		List<Integer> ancestors = new LinkedList<Integer> ();
		ancestors.add(v); //add this node as its own ancestor since there can be a node pointing to itself
		Integer parent = mapParent.get(v);
		while (parent != null && parent != -1) { //parent of first node is marked as -1
			ancestors.add(parent);
			parent = mapParent.get(parent); //traverse up the ancestors
		}
		return ancestors;
	}
	
	private static boolean dfsInner(Graph g, int v, boolean[] visited, int parent, Map<Integer, Integer> mapParent) {
		//check if node has been visited
		if (visited[v] == true) {
			List<Integer> ancestors = getAncestors(mapParent, parent); //get ancestors of the parent node
			//if this node is found in the ancestory of its parent, we have a cycle
			if (ancestors.contains(v)) {
				return true; //we have a cycle
			}
			return false; //we do not have a cycle
		} else {
			mapParent.put(v, parent);//map the parent
			visited[v] = true; //mark the node as visited
			String strSubtree = Integer.toString(v); //append node to the traversal path
			DoublyLinkedList<Integer>.Node node = g.adjacencyList[v].headNode; //get the first edge node
			while (node != null) {
				//a call from this level means parent = v
				boolean hasCycle = dfsInner(g, node.data, visited, v, mapParent); //append child node to the traversal path
				if (hasCycle == true) {
					return hasCycle;
				}
				node = node.nextNode;
			}
			return false; //we do not have a cycle
		}
	}
	
	public static boolean detectCycle(Graph g) {
		boolean[] visited = new boolean[g.vertices]; //vertices visited
		Map<Integer, Integer> mapParent = new HashMap(); //vertices mapped to their parent vertex
		boolean hasCycle = false;
		//iterate over all nodes
		for (int i = 0; i < g.vertices; i++) {
			hasCycle = dfsInner(g, i, visited, -1, mapParent); //a call from this level means no parent, hence -1
			if (hasCycle == true) {
				return hasCycle;
			}
		}
		
		return hasCycle;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(3, 6);
		g.addEdge(5, 1);
		
		System.out.printf("Graph has cycles?: %b", detectCycle(g));
	}

}
