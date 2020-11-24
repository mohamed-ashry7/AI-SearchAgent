import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class mainTest {

	
	public static void main(String[] args) throws CloneNotSupportedException {

		String grid = "3,3;0,0;2,2;0,2,0,1,2,0;22,54,33;2";
		String tGrid= "5,1;0,0;0,4;0,1,0,3,0,2;10,11,12;1";
		String s1 = "a" ; 
		String s2 = "a" ; 
		String s3 = "b" ;
		HashSet<GridState> h= new HashSet<>() ; 
//		
//		EnvironmentState e1 = new EnvironmentState(s1) ;
//		EnvironmentState e2 = new EnvironmentState(s2) ;
//		EnvironmentState e3 = new EnvironmentState(s3) ;
//		h.add(e1) ; 
//		h.add(e2) ; 
//		h.add(e3) ;
//		System.out.println(h.contains(new EnvironmentState("c")));
 		String grid1 = "5,5;1,2;4,0;0,3,2,1,3,0,3,2,3,4,4,3;20,30,90,80,70,60;3" ;
 		String grid3  ="14,14;13,9;1,13;5,3,9,7,11,10,8,3,10,7,13,6,11,1,5,2;76,30,2,49,63,43,72,1;6";
		System.out.println(MissionImpossible.solve(grid3, "BF", true));


	}


}
