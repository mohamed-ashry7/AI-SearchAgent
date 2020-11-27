package tests;
import code.mission.MissionImpossible;

public class mainTest {

	
	public static void main(String[] args) throws CloneNotSupportedException {

		String grid = "3,3;0,0;2,2;0,2,0,1,2,0;22,54,33;2";
		String tGrid= 
				  "1,5;"
				+ "0,0;"
				+ "0,4;"
				+ "0,1,"
				+ "0,3,"
				+ "0,2;"
				+ "10,11,12;"
				+ "1";

 		String grid1 = "5,5;1,2;4,0;0,3,2,1,3,0,3,2,3,4,4,3;20,30,90,80,70,60;3" ;
 		String grid3  ="14,14;13,9;1,13;5,3,9,7,11,10,8,3,10,7,13,6,11,1,5,2;76,30,2,49,63,43,72,1;6";
 		String grid15 = "15,15;5,10;14,14;0,0,0,1,0,2,0,3,0,4,0,5,0,6,0,7,0,8;81,13,40,38,52,63,66,36,13;1";
 		String one =MissionImpossible.genGrid();
		System.out.println(MissionImpossible.solve(grid1, "UC", false  ));


	}


}
