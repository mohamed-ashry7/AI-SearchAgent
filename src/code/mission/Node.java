package code.mission;

public class Node {
	static long createdNodes = 0;
	public Node parent;
	public int action;
	public int depth;
	public double cost;
	public GridState state;

	public Node(Node parent, int action, int depth, double cost, GridState state) {
		this.parent = parent;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.state = state;
		createdNodes++;
	}

	

}
