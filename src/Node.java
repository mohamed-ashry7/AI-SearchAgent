
public class Node {
	static long createdNodes = 0;
	Node parent;
	int action;
	int depth;
	double cost;
	GridState state;

	public Node(Node parent, int action, int depth, double cost, GridState state) {
		this.parent = parent;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.state = state;
		createdNodes++;
	}

	

}
