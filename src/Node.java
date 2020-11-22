
public class Node implements Comparable<Node> {
	static long createdNodes = 0;
	Node parent;
	String action;
	int depth;
	double cost;
	EnvironmentState state;

	public Node(Node parent, String action, int depth, double cost, EnvironmentState state) {
		this.parent = parent;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.state = state;
		createdNodes++;
	}

	@Override
	public int compareTo(Node o) {
		return o.state.compareTo(this.state);
	}

}
