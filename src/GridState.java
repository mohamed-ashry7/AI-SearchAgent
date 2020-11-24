import java.util.Arrays;

public class GridState extends State {

	private String state ; 
//	0   ;  1 ;   2   ;  3 
//  ePos;mPos;mDamage;Carried

	public GridState(String gridStr) {
		this.state = gridStr; 
//		System.out.println(state);
	}
	
	public void step(int action) {

		// the actions are mapped as numbers
		// 0 -> Up
		// 1-> Down
		// 2 -> Right
		// 3-> Left
		// 4 ->Carry
		// 5 ->Drop
		int [] ethanPos = this.getEthanPosition() ; 
		int x = ethanPos[0] ; 
		int y = ethanPos[1] ;
		if (action < 4) {
			switch (action) {
			case 0:
				x--;
				break;
			case 1:
				x++;
				break;
			case 2:
				y++;
				break;
			case 3:
				y--;
				break;
			}
			if (!(x < 0 || x >= MissionImpossible.Height || y < 0 || y >= MissionImpossible.Width)) {
				this.setEthanPosition(x, y);
			}
		} 
		else {

			if (action == 4 && this.getCarriedSoldiersNumber() < MissionImpossible.Capacity) {
				
				saveSoldierIfFound();
//				System.out.println(this.getCarriedSoldiersNumber() == MissionImpossible.Capacity);

			} else if (action == 5 && this.isEthanInSubmarine()) {
				this.updateCarriedSoldiers(0);
			}
		}
		
		this.updateDamages();
//		System.out.println(this.state);
		
//		System.out.println(this.state);
	}
	
	public String getSubState(int index ) { 
		return state.split(";")[index] ; 
	}
	public int [] getEthanPosition() {
		return MissionImpossible.convertStringIntArr(this.getSubState(0));
	}
	
	public int [] getMembersPositions() { 
		return MissionImpossible.convertStringIntArr(this.getSubState(1))  ; 
	}
	public int [] getDamages () { 
		return MissionImpossible.convertStringIntArr(this.getSubState(2)) ; 
	}
	
	public int  getCarriedSoldiersNumber() { 
		return MissionImpossible.convertStringIntArr(this.getSubState(3))[0]; 
	}
	
	
	public void setEthanPosition(int x ,int y) { 		
		String newPositions = this.joinIntegerArray(new int[]{x,y}) ; 
		this.updateState(newPositions, 0);
	}
	
	
	
	private int saveSoldierIfFound() {
		int [] p = this.getEthanPosition() ; 
		int [] membersPositions = this.getMembersPositions(); 
		for (int i = 0; i < membersPositions.length; i+=2) {
			
			if (p[0] == membersPositions[i] && p[1]==membersPositions[i+1]) { 
				membersPositions[i] = -1 ; 
				membersPositions[i+1] = -1 ; 
				String newPositions = this.joinIntegerArray(membersPositions) ; 
				this.updateState(newPositions, 1);
				int c = this.getCarriedSoldiersNumber()+1 ;
				this.updateCarriedSoldiers(c);
				return i ; 
			}
			
		}
		return -1;
	}
	public void updateCarriedSoldiers(int c ) {  
		String cc = c+"" ; 
		this.updateState(cc, 3);
	}
	public void updateState(String newSubState, int index) { 
		String []  states = this.state.split(";"); 
		states[index] = newSubState ; 
		String newState = "" ; 
		for (int i = 0 ; i < states.length; ++i ) { 
			newState +=states[i];
			if (i != states.length-1) 
				newState += ";";			
		}
		this.state= newState; 
	}
	
	public String joinIntegerArray(int [] a ) { 
		String s= "" ; 
		for (int i = 0 ; i < a.length; ++i ) { 
			s +=a[i];
			if (i != a.length-1) 
				s += ",";			
		}
		return s ; 
	}
	public void updateDamages() {
		int [] damages = this.getDamages(); 
		int [] positions = this.getMembersPositions() ; 
		for (int i = 0; i < damages.length; i++) {
			if(positions[2*i]!=-1) { 
				damages[i] = damages[i] < 99 ? damages[i] += 2 : 100;	
			}
		}
		String damageState = this.joinIntegerArray(damages) ; 
		this.updateState(damageState,2);
	}
	


	public GridState cloneState() {
			return new GridState(this.state) ; 
	}



	public int getSavedSoldiersNumber() {
		int [] soldiers = this.getMembersPositions() ; 
		int counter = 0 ; 
		for (int i = 0 ; i< soldiers.length ; i+=2 ) { 
			if (soldiers[i] == - 1 ) { 
				counter ++ ; 
			}
		}
		 return counter ; 
	}

	
	public boolean  areAllSaved() { 
		int [] pos = this.getMembersPositions() ; 
		for (int i = 0 ; i < pos.length ; i+=2 ) { 
			if (pos[i] != -1) { 
				return false ; 
			}
		}
		return true ; 
	}
	public boolean isEthanInSubmarine() { 
		int [] ethanPos = this.getEthanPosition() ; 
		int [] subPos = this.getSubmarinePosition()  ; 
		return ethanPos[0] == subPos[0] && ethanPos[1]==subPos[1] ;
	}
	public boolean isGoal(int action) {
		 
		return this.areAllSaved() && this.isEthanInSubmarine() && action == 5 ;
	}

	
	public int getDeadSoldiersNumber() { 
		int [] damages = this.getDamages() ; 
		int dead = 0 ; 
		for (int i = 0 ; i < damages.length ; ++i ) { 
			if (damages[i]>=100) { 
				++dead ; 
			}
		}
		return dead ; 
	}
	public int []  getSubmarinePosition() {
		return MissionImpossible.SubmarinePosition; 
	}
	public boolean areEqualPositions(int [] a , int [] b ) { 
		return a[0] == b[0] && a[1]==b[1] ; 
	}
//	@Override
//	public int compareTo(EnvironmentState o) {
//		
//		if (this.areEqualPositions(this.getEthanPosition(), o.getEthanPosition())
//				&& this.getSavedSoldiersNumber() == o.getSavedSoldiersNumber()
//				&& this.getCarriedSoldiersNumber() == o.getCarriedSoldiersNumber()) {
//			return 0;
//		}
//		return 1;
//	}
	public  double getEucDis(int[]  p1 , int [] p2) { 
		int dis1 = p1[0]-p2[0];  
		int dis2= p1[1]-p2[1] ; 
		return Math.sqrt(Math.pow(dis1, 2) + Math.pow(dis2, 2) ) ; 
	}
	
	
	
	public int getRemainingMemberSize() { 
		int allSoldiers = this.getMembersPositions().length/2 ;
		return allSoldiers-this.getSavedSoldiersNumber() ; 
		
		
	}
	public double getHeuristicValueOne() {
		
		if (this.getRemainingMemberSize()== 0) { 
			if (this.isEthanInSubmarine()) 
				return 0 ; 
			return this.getEucDis(this.getEthanPosition(),this.getSubmarinePosition());
		}
		double heuristicValue= Double.MAX_VALUE;
		int [] members = this.getMembersPositions() ; 
		for (int i = 0 ; i < members.length; i += 2 ) { 
			if (members[i]==-1)
				continue ; 
			int [] mem = {members[i] , members[i+1]}  ; 
  			double newCalc = this.getEucDis(this.getEthanPosition(),mem) ;
			heuristicValue=Math.min(newCalc, heuristicValue) ;  
		}
		
		return heuristicValue+this.getRemainingMemberSize()*2 ; 
	}
	
	public double getHeuristicValueTwo() {
		
		
		
		if (this.getRemainingMemberSize()== 0) { 
			if (this.isEthanInSubmarine()) 
				return 0 ; 
			return this.getEucDis(this.getEthanPosition(),this.getSubmarinePosition());
		}
		
		double heuristicValue = Double.MAX_VALUE ; 
		int [] members = this.getMembersPositions() ; 
		int [] damages = this.getDamages() ; 
		for (int i = 0 ; i < members.length; i+=2  ) { 
			if (members[i] == -1) 
				continue ; 
			
			double  health = (100 - damages[i/2])*0.01 ; 
			if (health==0.0) { 
				health =1000; 
			}
			
			
			heuristicValue=Math.min(health, heuristicValue) ;  
		}
		return heuristicValue+this.getRemainingMemberSize()*2 ;  
	}
 	
	
	public double manhattenDistance(int [] p1, int[] p2) { 
		return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]) ; 
	}
	
	public String getSoldiersDamages() { 
		return this.getSubState(2) ; 
		
	}
	public double getCostFunction() { 
		if (this.getRemainingMemberSize()== 0) { 
			if (this.isEthanInSubmarine()) 
				return 0 ; 
			return this.manhattenDistance(this.getEthanPosition(),this.getSubmarinePosition());
		}
		double actualDis = Double.MAX_VALUE ;
		double deadNumber = this.getDeadSoldiersNumber() ;  
		double damage = 0 ;
		
		
		int [] members = this.getMembersPositions() ; 
		int [] damages= this.getDamages() ; 
		for (int i = 0 ; i < members.length; i += 2 ) { 
			if (members[i]==-1)
				continue ; 
			int [] mem = {members[i] , members[i+1]}  ; 
  			double newCalc = this.manhattenDistance(this.getEthanPosition(),mem) ;
  			if (newCalc<actualDis) {
  				actualDis=Math.min(newCalc, actualDis) ;  
  				damage =damages[i/2] ;  
  			}
		}
		
		return deadNumber+ actualDis + 0.1*damage ; 
	}
	
	public String toString() {
		return "Position: " + this.getEthanPosition() + ", Number of Saved Soldiers: " + this.getSavedSoldiersNumber()
				+ ", Carried Soldiers: " + this.getCarriedSoldiersNumber();
	}
	public int hashCode() { 
		String x=  this.getSubState(0)+";"+ this.getSavedSoldiersNumber()+";"+ this.getCarriedSoldiersNumber();	
		return x.hashCode(); 
	}
	public boolean equals(Object e) {
		GridState o = (GridState) e ; 
		return this.areEqualPositions(this.getEthanPosition(), o.getEthanPosition())
				&& this.getSavedSoldiersNumber() == o.getSavedSoldiersNumber()
				&& this.getCarriedSoldiersNumber() == o.getCarriedSoldiersNumber(); 
	}
	public void visualize() { 
		String [][] grid = new String [MissionImpossible.Height][MissionImpossible.Width] ;
		for (int i = 0 ; i < grid.length ; ++i ) { 
			for (int j = 0 ; j<grid[0].length;++j) { 
				grid[i][j]= "." ; 
			}
		}
		int [] members = this.getMembersPositions() ; 
		int [] ethan = this.getEthanPosition() ; 
		int [] sub = this.getSubmarinePosition() ; 
		boolean ethanPrinted = false ; 
		for (int i = 0 ; i <members.length ; i+= 2  ) { 
			if (members[i]==-1)
				continue; 
			int [] mem = new int [] {members[i],members[i+1] } ; 
			if (areEqualPositions(ethan, mem)) { 
				grid[mem[0]][mem[1]] = "E+M" ;
				ethanPrinted = true ; 
			}
			else  {
				grid[mem[0]][mem[1]] = "M" ;
			}
		}
		if (this.isEthanInSubmarine())
			grid[sub[0]][sub[1]] = "E+S" ;
		else {
			if (!ethanPrinted)
				grid[ethan[0]][ethan[1]] = "E" ;
			grid[sub[0]][sub[1]] = "S" ;
		}
		String dash = "-".repeat(MissionImpossible.Width*5); 
		for (int i = 0 ; i <grid.length ; ++i  ) { 
			System.out.println(Arrays.toString(grid[i]));
		}		
		System.out.println(dash);

		
	}
	

}
