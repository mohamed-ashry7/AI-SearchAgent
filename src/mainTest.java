import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class mainTest {

	
	public static void main(String[] args) throws CloneNotSupportedException {

		String grid = "3,3;0,0;2,2;0,2,0,1,2,0;22,54,33;2";
		String grid1 = "5,5;1,2;4,0;0,3,2,1,3,0,3,2,3,4,4,3;20,30,90,80,70,60;3" ;
		System.out.println(MissionImpossible.solve(grid, "GR1", false));


	}


}
