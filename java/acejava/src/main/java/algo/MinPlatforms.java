package algo;

import java.util.ArrayList;
import java.util.List;

public class MinPlatforms {
	
	static class Node { //a train
		public Node(int arrive, int depart) {
			this.arrive = arrive; //arrival time
			this.depart = depart; //departure time
			this.nextTrain = null; //next train arriving at this platform
		}
		int arrive;
		int depart;
		Node nextTrain;
	}
	
	List<Node> platforms = new ArrayList<Node>(); //list of platforms, each platform a list of Nodes (trains)
	
	//given the head train of a platform, try inserting a scheduled train
	//respond true if insert was a success
	private boolean insertAtPlatform(Node headTrain, Node scheduledTrain) {
		//insert at head
		if (headTrain == null) {
			headTrain = scheduledTrain;
			return true;
		}
		
		//insert before head
		if (scheduledTrain.depart <= headTrain.arrive) {
			scheduledTrain.nextTrain = headTrain;
			headTrain = scheduledTrain;
			return true;
		}
		
		//insert after head, in the middle
		Node traverse = headTrain;
		while (traverse != null) {
			if (scheduledTrain.arrive >= traverse.depart) {
				if (traverse.nextTrain != null) {
					if (scheduledTrain.depart <= traverse.nextTrain.arrive) {
						//yes, the scheduled train can fit between traverse and travers.next
						scheduledTrain.nextTrain = traverse.nextTrain;
						traverse.nextTrain = scheduledTrain;
						return true; //fit in the middle
					} else {
						traverse = traverse.nextTrain; //check the fit between the next train and the one after that
					}
				} else { //scheduled train arrival is later than the last train departure
					//append scheduled train at end
					traverse.nextTrain = scheduledTrain;
					return true; //fit at end
				}
			} else {
				//no, the train arrives while the previous train has not left 
				return false;
			}
		}
		
		return false; //we have reached the end of the trains at this platform, and could not accommodate
	}

	public static int findPlatform(int[] arrival, int[] departure) {
		MinPlatforms objMP = new MinPlatforms(); //create an object of MinPlatforms

		//fill platforms with train schedule
		for (int i = 0; i < arrival.length; i++) { //traverse over the schedule
			Node scheduledTrain = new Node(arrival[i], departure[i]); //create a node for this train
			//check if a train can be accommodated on an existing platform.
			boolean accommodated = false; //was the train accommodated at a platform?
			for (Node headTrain: objMP.platforms) { //get the start train of a platform
				accommodated = objMP.insertAtPlatform(headTrain, scheduledTrain);
				if (accommodated)
					break; //stop checking other platforms
			}
			//if the scheduled train could not be accommodated in an existing platform, create a new platform
			if (!accommodated)
				objMP.platforms.add(scheduledTrain);
		}
		
		return objMP.platforms.size(); //return the number of platforms
	}
	
	public static void main(String[] args) {
		int[] arrival =   {900,  940,  950, 1100, 1500, 1800};
		int[] departure = {910, 1200, 1120, 1130, 1900, 2000};
		MinPlatforms objMP = new MinPlatforms();
		System.out.printf("Number of platforms needed: %d\n", MinPlatforms.findPlatform(arrival, departure));
	}

}
