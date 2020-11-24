import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class MissionImpossible extends SearchProblem {

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
	public String map2DGrid(String grid) { 

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
		MissionImpossible mi = new MissionImpossible() ; 
		
		String state = mi.map2DGrid(grid) ; 
		GridState firstState = new GridState(state);
		Node root = new Node(null, -1, 0, 0, firstState);
		Node goalNode = mi.search(root,strategy) ; 
		
		return mi.formulateAnswer(goalNode , visualize) ; 
	}
	
	
	
	public String formulateAnswer(Node goalNode,boolean visualize) { 
		
		String answer = this.backtrack(goalNode,visualize).substring(1)+";"  ;
		answer +=goalNode.state.getDeadSoldiersNumber()+";"  ; 
		answer += goalNode.state.getSoldiersDamages()+";" ; 
		answer += Node.createdNodes ; 
		return answer ; 
	}

	public String backtrack(Node a,boolean visualize) {
		if (a==null || a.action == -1) {
			if (visualize) { 
				a.state.visualize();
			}
			return "";
		}
		String sol = backtrack(a.parent,visualize) + "," + getTheAction(a.action) ;
		if (visualize) { 
			System.out.println("Action taken: " + getTheAction(a.action));
			a.state.visualize();
		}
		return sol ; 
	}

	private String getTheAction(int action) {
		// 0 -> Up
		// 1-> Down
		// 2 -> Right
		// 3-> Left
		// 4 ->Carry
		// 5 ->Drop
		switch (action) {
		case 0:
			return "up";
		case 1:
			return "down";
		case 2:
			return "right";
		case 3:
			return "left";
		case 4:
			return "carry";
		case 5:
			return "drop";
		}
		return "Invalid";
	}

	@Override
	public Node search(Node root , String strategy ) {
		container = new HashSet<>();

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
		return goalNode ; 
 
	}
	

}
