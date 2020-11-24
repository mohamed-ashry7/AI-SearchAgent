import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestMissionImpossible {
	String grid5 = "5,5;2,1;1,0;1,3,4,2,4,1,3,1;54,31,39,98;2";
	String grid6 = "6,6;1,1;3,3;3,5,0,1,2,4,4,3,1,5;4,43,94,40,92;3";
	String grid7 = "7,7;1,6;5,4;2,2,1,4,0,3,2,3,0,1,4,5;6,44,82,49,24,54;4";
	String grid8 = "8,8;4,2;7,4;5,1,7,7,4,0,6,7;93,85,72,78;1";
	String grid9 = "9,9;8,7;5,0;0,8,2,6,5,6,1,7,5,5,8,3,2,2,2,5,0,7;11,13,75,50,56,44,26,77,18;2";
	String grid10 = "10,10;6,3;4,8;9,1,2,4,4,0,3,9,6,4,3,4,0,5,1,6,1,9;97,49,25,17,94,3,96,35,98;3";
	String grid11 = "11,11;7,7;8,8;9,7,7,4,7,6,9,6,9,5,9,1,4,5,3,10,5,10;14,3,96,89,61,22,17,70,83;5";
	String grid12 = "12,12;7,7;10,6;0,4,2,2,1,3,8,2,4,2,9,3;95,4,68,2,94,91;5";
	String grid13 = "13,13;7,4;4,0;9,3,3,9,12,7,7,9,3,12,11,8,4,2,12,6;22,62,74,56,43,70,17,14;4";
	String grid14 = "14,14;13,9;1,13;5,3,9,7,11,10,8,3,10,7,13,6,11,1,5,2;76,30,2,49,63,43,72,1;6";
	String grid15 = "15,15;5,10;14,14;0,0,0,1,0,2,0,3,0,4,0,5,0,6,0,7,0,8;81,13,40,38,52,63,66,36,13;1";
	
	@Test(timeout = 70000)
	public void testa1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void testa2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void testa3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	
	@Test(timeout = 70000)
	public void testa4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void testa5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void testa6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void testa7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void testa8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void testa9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void testa10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void testa11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	@Test(timeout = 70000)
	public void testb1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void testb2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void testb3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 70000)
	public void testb4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void testb5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void testb6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void testb7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void testb8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void testb9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void testb10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void testb11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	@Test(timeout = 70000)
	public void testc1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void testc2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void testc3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	
	@Test(timeout = 70000)
	public void testc4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void testc5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void testc6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void testc7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void testc8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void testc9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void testc10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void testc11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	@Test(timeout = 80000)
	public void testd1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 80000)
	public void testd2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 80000)
	public void testd3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 80000)
	public void testd4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test
	public void testd5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout =100000)
	public void testd6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 100000)
	public void testd7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 100000)
	public void testd8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	
	@Test(timeout = 70000)
	public void teste1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void teste2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void teste3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	
	@Test(timeout = 70000)
	public void teste4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void teste5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void teste6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void teste7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void teste8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void teste9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void teste10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void teste11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	@Test(timeout = 70000)
	public void testf1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void testf2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void testf3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 70000)
	public void testf4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void testf5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void testf6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void testf7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void testf8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void testf9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void testf10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void testf11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	@Test(timeout = 70000)
	public void testg1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void testg2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void testg3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	@Test(timeout = 70000)
	public void testg4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void testg5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void testg6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void testg7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void testg8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void testg9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void testg10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void testg11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	@Test(timeout = 70000)
	public void testh1() throws Exception {
		String solution = MissionImpossible.solve(grid5, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}
	
	@Test(timeout = 70000)
	public void testh2() throws Exception {
		String solution = MissionImpossible.solve(grid6, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}
	
	@Test(timeout = 70000)
	public void testh3() throws Exception {
		String solution = MissionImpossible.solve(grid7, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}
	
	
	@Test(timeout = 70000)
	public void testh4() throws Exception {
		String solution = MissionImpossible.solve(grid8, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}
	
	@Test(timeout = 70000)
	public void testh5() throws Exception {
		String solution = MissionImpossible.solve(grid9, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}
	
	@Test(timeout = 70000)
	public void testh6() throws Exception {
		String solution = MissionImpossible.solve(grid10, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}
	
	@Test(timeout = 70000)
	public void testh7() throws Exception {
		String solution = MissionImpossible.solve(grid11, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid11, solution));
	}
	
	@Test(timeout = 70000)
	public void testh8() throws Exception {
		String solution = MissionImpossible.solve(grid12, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid12, solution));
	}
	
	@Test(timeout = 70000)
	public void testh9() throws Exception {
		String solution = MissionImpossible.solve(grid13, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid13, solution));
	}
	
	@Test(timeout = 70000)
	public void testh10() throws Exception {
		String solution = MissionImpossible.solve(grid14, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid14, solution));
	}
	
	@Test(timeout = 70000)
	public void testh11() throws Exception {
		String solution = MissionImpossible.solve(grid15, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid15, solution));
	}
	
	



	private boolean applyPlan(String grid, String solution) {
		char[][] g = convertToGrid(grid);
		String plan = solution.split(";")[0];
		plan.replace(" ", "");
		plan.replace("\n", "");
		plan.replace("\r", "");
		plan.replace("\n\r", "");
		plan.replace("\t", "");
		String[] actions = plan.split(",");
		String[] gridArray=  grid.split(";");
		String[] ethan = gridArray[1].split(",");
		String submarine = gridArray[2];
		String capacity = gridArray[5];
		int c = Integer.parseInt(capacity);
		int membersNum  = (gridArray[3].split(",").length)/2;
		int[] result = new int[4];
		result[0] = Integer.parseInt(ethan[0]);
		result[1] = Integer.parseInt(ethan[1]);
		result[2] = c;
		result[3] = membersNum;
		for (int i = 0; i < actions.length; i++) {
			switch (actions[i]) {
			case "up":
				applyUp(g, result);
				break;
			case "down":
				applyDown(g, result);
				break;
			case "right":
				applyRight(g, result);
				break;
			case "left":
				applyLeft(g, result);
				break;
			case "carry":
				applyCarry(g, result);
				break;
			case "drop":
				applyDrop(g, result, c);
				break;
			}
		}
		return done(submarine,result);
	}
	
	private boolean done(String submarine, int[] result) {
		return (result[0] + "," + result[1]).equals(submarine) && result[3] == 0;
	}

	private void applyDrop(char[][] g, int[] result, int capacity) {
		if (g[result[0]][result[1]] == 'S') {
			result[2]=capacity;
		}
	}


	private void applyCarry(char[][] g, int[] result) {
		if (g[result[0]][result[1]] == 'M' && result[2]>0) {
			g[result[0]][result[1]] = '\u0000';
			result[2]--;
			result[3]--;
		}
	}

	private void applyLeft(char[][] g, int[] result) {
		if (result[1] - 1 >= 0)
			result[1]--;
	}

	private void applyRight(char[][] g, int[] result) {
		int n = g[0].length;
		if (result[1] + 1 < n)
			result[1]++;
	}

	private void applyDown(char[][] g, int[] result) {
		int m = g.length;
		if (result[0] + 1 < m)
			result[0]++;

	}

	private void applyUp(char[][] g, int[] result) {
		if (result[0] - 1 >= 0)
			result[0]--;
	}

	private char[][] convertToGrid(String input) {
		String[] s = input.split(";");

		String[] dimensions = s[0].split(",");
		String[] submarine = s[2].split(",");
		String[] members = s[3].split(",");

		char[][] grid = new char[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];
		grid[Integer.parseInt(submarine[0])][Integer.parseInt(submarine[1])] = 'S';

		for (int i = 0; i < members.length - 1; i += 2)
			grid[Integer.parseInt(members[i])][Integer.parseInt(members[i + 1])] = 'M';


		return grid;
	}

}