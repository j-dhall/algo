package ds;

public class CheckDFS {
	
	private static String dfsInner(Graph g, int v, boolean[] visited) {
		//check if node has been visited
		if (visited[v] == true) {
			return ""; //return an empty string
		} else {
			visited[v] = true; //mark the node as visited
			String strSubtree = Integer.toString(v); //append node to the traversal path
			DoublyLinkedList<Integer>.Node node = g.adjacencyList[v].headNode; //get the first edge node
			while (node != null) {
				strSubtree = strSubtree.concat(dfsInner(g, node.data, visited)); //append child node to the traversal path
				node = node.nextNode;
			}
			return strSubtree; //return the traversal path of the subtree at v 
		}
	}
	
	public static String dfs(Graph g) {
		boolean[] visited = new boolean[g.vertices]; //vertices visited
		String traversal = "";
		//iterate over all nodes
		for (int i = 0; i < g.vertices; i++) {
			traversal = traversal.concat(dfsInner(g, i, visited));
		}
		
		return traversal;
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
		System.out.println(dfs(g));
		
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 3);
		g2.addEdge(2, 4);
		System.out.println(dfs(g2));
		
		Graph g3 = new Graph(5);
		g3.addEdge(0, 1);
		g3.addEdge(0, 2);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		System.out.println(dfs(g3));
	}

}
