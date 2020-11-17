import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class MissionImpossible extends GenericSearchProblem {

	
	
	
	
	static TreeSet<Node>container; 
	private static int randomNumber(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	private static ArrayList<Position>  generateRandomUniquePositions(int m, int n ) {
		
		ArrayList<Position> allPositions = new ArrayList<>();
		
		for (int i = 0 ;i < m ; ++i ) { 
			for (int j = 0; j < n  ; ++j) { 
				allPositions.add(new Position(i, j)) ; 
			}
		}
		Collections.shuffle(allPositions);
		return allPositions; 
	}
	public static String genGrid() {
		 

		int m = randomNumber(5, 15); 
		int n = randomNumber(5, 15);
		ArrayList<Position> allPositions = generateRandomUniquePositions(m, n) ;  
		Position ethanP = allPositions.get(0) ; 
		Position submarineP = allPositions.get(1) ; 
		String grid =String.format("%d,%d;%d,%d;%d,%d;",m,n,ethanP.x,ethanP.y,submarineP.x,submarineP.y);
		int numberIMFmembers = randomNumber(5, 10); 
	

		for (int i = 0 ; i < numberIMFmembers; i++) { 
			Position temp = allPositions.get(i+2) ; 
			if (i ==numberIMFmembers-1)
				grid += String.format("%d,%d;", temp.x,temp.y) ;
			else 
				grid += String.format("%d,%d,", temp.x,temp.y) ;
			
		}
		
		for (int i = 0 ; i < numberIMFmembers; i++) { 
			int damage= randomNumber(1, 99) ; 
			if (i ==numberIMFmembers-1)
				grid += String.format("%d;",damage) ;
			else 
				grid += String.format("%d,", damage) ;
			
		}
		
		int c = randomNumber(1, numberIMFmembers);
		grid+=c;
		return grid; 
		

	}
	
	public static String solve (String grid, String strategy, boolean visualize) {
		EnvironmentState firstState = new EnvironmentState(grid); 
		Node root = new Node(null , null , 0, 0, firstState) ; 
		Queue<Node> nodes = new LinkedList<>();
		nodes.add(root) ; 
		container = new TreeSet<>() ; 
		Node goalNode = null ; 
		switch(strategy) {
		case "BF":goalNode = BFS(nodes); break; 
		}
		
		
		return backtrack(goalNode) ; 
		}
	
	static String backtrack (Node a) { 
		if (a == null ) {
			return ""; 
		}
		return backtrack(a.parent) + " " +a.action; 
	}
	
	static Node BFS(Queue<Node> nodes) { 
		while(true) { 
			if (nodes.size()==0 ) { 
			
				return null ; 
			}
			else { 
				System.out.println("hallo");
				Node front = nodes.poll() ;
				container.add(front) ; 
				if (front.state.isGoal()) {
					return front ; 
				}
				else {
					Node child = expand(front,1) ;
					System.out.println(front.state);
					System.out.println(child.state);
					for (int i = 0 ; i<6;++i  ) {
//						System.out.println(child.compareTo(front));
//						System.out.println(child.state);
						if (!container.contains(child)) {
							nodes.add(child); 
							System.out.println("Da5al");
						}
					}
					
				}
			}
		}
	}
	
	static Node expand(Node parent, int action){
		
		try {
			EnvironmentState state = (EnvironmentState)parent.state.clone().step(action) ; 
			return new Node(parent,getTheAction(action), parent.depth+1, parent.cost+1, state) ;
		} catch (UndefinedActionException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} 
		return null ; 
	}
	
	private static String getTheAction(int action ) throws UndefinedActionException { 
		// 0 -> Up
		// 1-> Down
		// 2 -> Right
		// 3-> Left
		// 4 ->Carry
		// 5 ->Drop
		switch(action) {
			case 0:return "Up"; 
			case 1:return "Down"; 
			case 2:return "Right";
			case 3:return "Left"; 
			case 4:return "Carry";
			case 5:return "Drop" ; 
		}
		throw new UndefinedActionException(); 
	}



}
