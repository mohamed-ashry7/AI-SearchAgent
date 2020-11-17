import java.util.HashSet;
import java.util.TreeSet;

public class mainTest {

	public static void main(String[] args) throws CloneNotSupportedException {

		
		
		
		String grid = "3,3;0,0;2,2;0,2,1,0,2,0;22,54,33;2";
		
		System.out.println(MissionImpossible.solve(grid, "BF", false));
		
		Position x = new Position(5,5) ; 
		Position y = (Position)x.clone();
		y.setX(10);
		System.out.println(x);
		System.out.println(y);
//		Position z =(Position) x.clone(); 
//		System.out.println(x==z);
//		TreeSet<Position> h = new TreeSet<>(); 
//		h.add(x) ; 
//		System.out.println(h.contains(z));
//		TreeSet<IMFmember> ss = new TreeSet<>(); 
//		ss.add(new IMFmember(x, 5)) ; 
//		ss.add(new IMFmember(y, 5))  ;
		
//		System.out.println(ss.size());
//		System.out.println(ss.contains(new EnvGrid.IMFmember(new Position(5,5), 9)));
//		System.out.println(x.compareTo(y));
//		System.out.println(MissionImpossible.genGrid());
		
		}
	
	
	


}
