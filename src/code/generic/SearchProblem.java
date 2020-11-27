package code.generic;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import code.mission.GridState;
import code.mission.Node;

public abstract class SearchProblem {
	public HashSet<State> container;
	
	abstract public Node search(Node root , String strategy)  ; 
	
	
	public Node BFS(Queue<Node> nodes) {
		while (true) {
			if (nodes.size() == 0) {

				return null;
			} else {
				Node front = nodes.poll();
				if (front.state.isGoal(front.action)  ) {
					return front;
				} else {
					for (int i = 0; i < 6; ++i) {
						Node child = expand(front, i);
						if (!container.contains(child.state)) {
							container.add(child.state);
							nodes.add(child);
						}

					}

				}
			}
		}
	}

	public Node DFS(Stack<Node> stackNodes) {
		return DFSHelper(stackNodes, true, 0);
	}

	public Node DFSHelper(Stack<Node> stackNodes, boolean dfs, int iter) {

		while (true) {
			if (stackNodes.isEmpty()) {
				return null;
			}
			
			Node front = stackNodes.pop();
			if (front.state.isGoal(front.action)) {
				return front;
			}
			if (!dfs && front.depth >= iter) {
				continue;
			}
			for (int i = 0; i <6; i++) { // the action order is reversed due to the stack.... start with the last
											// action.
				Node child = expand(front, i);
				if (!container.contains(child.state)) {
					container.add(child.state);
					stackNodes.push(child);
				}
			}
		}

	}

	public Node ID(Node root) {
		int iter = 0;
		Node goalNode;
		do {
			container = new HashSet<>(); ; 
			Stack<Node> nodesStack = new Stack<>();
			nodesStack.push(root);
			goalNode = DFSHelper(nodesStack, false, iter);
			++iter;
		} while (goalNode == null);
		return goalNode;

	}

	public Node UCS(PriorityQueue<Node> pq) {
		return rawPQFn(pq);
	}

	public Node greedy(PriorityQueue<Node> pq) {
		return rawPQFn(pq);
	}

	public Node AS(PriorityQueue<Node> pq) {
		return rawPQFn(pq);
	}

	public Node rawPQFn(PriorityQueue<Node> pq) {

		while (true) {
			if (pq.size() == 0) {

				return null;
			} else {
				Node front = pq.poll();
//				System.out.println( front.action+" "+front.state.getHeuristicValueOne());
				if (front.state.isGoal(front.action) ) {
					return front;
				} else {
					for (int i = 0; i < 6; ++i) {
						Node child = expand(front, i);
						if (!container.contains(child.state)) {							
							container.add(child.state);
							pq.add(child);
						}

					}

				}
			}
		}

	}

	public Node expand(Node parent, int action) {

		GridState state = parent.state.cloneState(); // don't forget here to modify the cost according to the action												// taken not juast +1;
		state.step(action);
		double cost = state.getCostFunction();
		return new Node(parent, action, parent.depth + 1, parent.cost + cost, state);

	}
	
	
}
