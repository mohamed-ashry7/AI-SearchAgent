import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

public class MissionImpossible extends GenericSearchProblem {

	static TreeSet<Node> container;
	static int Width; 
	static int Height ; 
	static int []  SubmarinePosition ; 
	static int Capacity ;
	private static int randomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	private static ArrayList<Position> generateRandomUniquePositions(int m, int n) { // change to real random method ..

		ArrayList<Position> allPositions = new ArrayList<>();

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				allPositions.add(new Position(i, j));
			}
		}
		Collections.shuffle(allPositions);
		return allPositions;
	}

	public static String genGrid() {

		int m = randomNumber(5, 15);
		int n = randomNumber(5, 15);
		ArrayList<Position> allPositions = generateRandomUniquePositions(m, n);
		Position ethanP = allPositions.get(0);
		Position submarineP = allPositions.get(1);
		String grid = String.format("%d,%d;%d,%d;%d,%d;", m, n, ethanP.x, ethanP.y, submarineP.x, submarineP.y);
		int numberIMFmembers = randomNumber(5, 10);

		for (int i = 0; i < numberIMFmembers; i++) {
			Position temp = allPositions.get(i + 2);
			if (i == numberIMFmembers - 1)
				grid += String.format("%d,%d;", temp.x, temp.y);
			else
				grid += String.format("%d,%d,", temp.x, temp.y);

		}

		for (int i = 0; i < numberIMFmembers; i++) {
			int damage = randomNumber(1, 99);
			if (i == numberIMFmembers - 1)
				grid += String.format("%d;", damage);
			else
				grid += String.format("%d,", damage);

		}

		int c = randomNumber(1, numberIMFmembers);
		grid += c; // capacity.
		return grid;

	}
	static int[] convertStringIntArr(String str) {
		return Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
	}
	static String map2DGrid(String grid) { 

		String[] info = grid.split(";");
		int[] mn = convertStringIntArr(info[0]);
		Width = mn[0];
		Height = mn[1];
		SubmarinePosition = convertStringIntArr(info[2]);
		Capacity = convertStringIntArr(info[5])[0];
		String state = "" ; 
		state += info[1]+";" ; // EthanPosition ; 
		state += info[3]+";" ; // 
		state += info[4] + ";" ; 
		state += "0" ; 
		
		return state ; 
//		Comparator<IMFmember> com = Comparator.comparingDouble(mem -> this.getEucDis(mem.getPosition(), this.getEthanPosition())); 
//		Collections.sort(members,com);
	}
	public static String solve(String grid, String strategy, boolean visualize) {
		String state = map2DGrid(grid) ; 
		container = new TreeSet<>();

		EnvironmentState firstState = new EnvironmentState(state);
		Node root = new Node(null, null, 0, 0, firstState);

		Node goalNode = null;

		if (strategy.equals("BF")) {
			Queue<Node> nodes = new LinkedList<>();
			nodes.add(root);
			goalNode = BFS(nodes);
		} else if (strategy.equals("DF")) {
			Stack<Node> nodesStack = new Stack<>();
			nodesStack.push(root);
			goalNode = DFS(nodesStack);
		} else if (strategy.equals("ID")) {

			goalNode = ID(root);

		} else if (strategy.equals("UC")) {

			Comparator<Node> ucsComparator = Comparator.comparingDouble(n -> n.state.getCostFunction());
			PriorityQueue<Node> ucsPQ = new PriorityQueue<>(ucsComparator);
			ucsPQ.add(root);
			goalNode = UCS(ucsPQ);
		} else if (strategy.charAt(0) == 'G') {
			Comparator<Node> greedyCom;

			if (strategy.equals("GR1")) {
				greedyCom = Comparator.comparingDouble(n -> n.state.getHeuristicValueOne());

			} else {
				greedyCom = Comparator.comparingDouble(n -> n.state.getHeuristicValueTwo());
			}
			PriorityQueue<Node> greedyPQ = new PriorityQueue<>(greedyCom);
			greedyPQ.add(root);
			goalNode = greedy(greedyPQ);
		} else if (strategy.charAt(0) == 'A') {
			Comparator<Node> asCom;

			if (strategy.equals("AS1")) {
				asCom = Comparator.comparingDouble(n -> (n.state.getHeuristicValueOne() + n.state.getCostFunction()));

			} else {
				asCom = Comparator.comparingDouble(n -> (n.state.getHeuristicValueTwo() + n.state.getCostFunction()));
			}
			PriorityQueue<Node> asPQ = new PriorityQueue<>(asCom);
			asPQ.add(root);
			goalNode = AS(asPQ);
		}
		
		

		return formulateAnswer(goalNode) ; 
	}
	
	static String formulateAnswer(Node goalNode) { 
		
		String answer = backtrack(goalNode).substring(1)+";"  ;
		answer +=goalNode.state.getDeadSoldiersNumber()+";"  ; 
		answer += goalNode.state.getDamages()+";" ; 
		answer += Node.createdNodes ; 
		return answer ; 
	}

	static String backtrack(Node a) {
		if (a==null || a.action == null) {
			return "";
		}
		return backtrack(a.parent) + "," + a.action;
	}

	static Node BFS(Queue<Node> nodes) {
		while (true) {
			if (nodes.size() == 0) {

				return null;
			} else {
				Node front = nodes.poll();
				container.add(front);
				if (front.state.isGoal() && front.action.equals("Drop") ) {
					return front;
				} else {
					for (int i = 0; i < 6; ++i) {
						Node child = expand(front, i);
						if (!container.contains(child)) {
							nodes.add(child);
						}

					}

				}
			}
		}
	}

	static Node DFS(Stack<Node> stackNodes) {
		return DFSHelper(stackNodes, true, 0);
	}

	static Node DFSHelper(Stack<Node> stackNodes, boolean dfs, int iter) {

		while (true) {
			if (stackNodes.isEmpty()) {
				return null;
			}
			Node front = stackNodes.pop();
			container.add(front);
			if (front.state.isGoal() && front.action.equals("Drop") ) {
				return front;
			}
			if (!dfs && front.depth >= iter) {
				continue;
			}
			for (int i = 5; i > -1; i--) { // the action order is reversed due to the stack.... start with the last
											// action.
				Node child = expand(front, i);
				if (!container.contains(child)) {
					stackNodes.push(child);
				}
			}
		}

	}

	static Node ID(Node root) {
		int iter = 0;
		Node goalNode;
		do {
			container = new TreeSet<>(); ; 
			Stack<Node> nodesStack = new Stack<>();
			nodesStack.push(root);
			goalNode = DFSHelper(nodesStack, false, iter);
			++iter;
		} while (goalNode == null);
		return goalNode;

	}

	static Node UCS(PriorityQueue<Node> pq) {
		return rawPQFn(pq);
	}

	static Node greedy(PriorityQueue<Node> pq) {
		return rawPQFn(pq);
	}

	static Node AS(PriorityQueue<Node> pq) {
		return rawPQFn(pq);
	}

	static Node rawPQFn(PriorityQueue<Node> pq) {

		while (true) {
			if (pq.size() == 0) {

				return null;
			} else {
				Node front = pq.poll();
				container.add(front);
				if (front.state.isGoal() && front.action.equals("Drop") ) {
					return front;
				} else {
					for (int i = 0; i < 6; ++i) {
						Node child = expand(front, i);
						if (!container.contains(child)) {
							pq.add(child);
						}

					}

				}
			}
		}

	}

	static Node expand(Node parent, int action) {

		EnvironmentState state = parent.state.cloneState(); // don't forget here to modify the cost according to the action												// taken not juast +1;
		state.step(action);
		double cost = state.getCostFunction();
		return new Node(parent, getTheAction(action), parent.depth + 1, parent.cost + cost, state);

	}

	private static String getTheAction(int action) {
		// 0 -> Up
		// 1-> Down
		// 2 -> Right
		// 3-> Left
		// 4 ->Carry
		// 5 ->Drop
		switch (action) {
		case 0:
			return "Up";
		case 1:
			return "Down";
		case 2:
			return "Right";
		case 3:
			return "Left";
		case 4:
			return "Carry";
		case 5:
			return "Drop";
		}
		return "Invalid";
	}

}
