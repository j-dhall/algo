package ds;

import java.util.HashSet;
import java.util.Set;

//import ds.DoublyLinkedList.Node;

public class CheckBFS {
	
	public static String bfs(Graph g) {
		String result = ""; //traversal string to return
		Queue<Integer> explore = new Queue (g.vertices); //candidates of traversal
		Set<Integer> visited = new HashSet<Integer> (); //visited nodes

		for (int i = 0; i < g.vertices; i++) { //iterate over all the vertices (needed for disconnected graphs and vertices with no incoming edges)
			if (visited.contains(i)) { //check if it has been visited
				continue; //skip exploring this visited vertex
			} else {
				explore.enqueue(i); //start exploring at node i
				while (!explore.isEmpty()) { //while we have candidates for traversal
					int vertex = explore.dequeue(); //dequeue a vertex
					
					if (visited.contains(vertex)) { //check if it has been visited
						continue; //skip exploring this visited vertex
					}
					
					//append vertex to the traversal path, and mark the vertex as visited
					result = result.concat(Integer.toString(vertex));
					visited.add(vertex);
					
					//add neighboring vertices for further exploration
					DoublyLinkedList<Integer> edges = g.adjacencyList[vertex]; //get the list of edges
					DoublyLinkedList.Node node = edges.headNode; //get the head of the list of edges
					while (node != null) { //while the list still has edges
						explore.enqueue((Integer)node.data);//add the edge node for further exploration
						node = node.nextNode; //move to the next edge
					}
				}
			}
		}
		
		return result;
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
		System.out.println(bfs(g));
		
		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 3);
		g2.addEdge(2, 4);
		System.out.println(bfs(g2));
	}

}
