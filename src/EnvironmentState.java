import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class EnvironmentState implements Comparable<EnvironmentState> {

	private int width;
	private int height;
	private Position ethanPos;
	private Position submarinePos;
	private ArrayList<IMFmember> members;
	private ArrayList<IMFmember> savedMembers;
	private int deadSoldiersNumber;
	private int capacity;
	private int carriedSoldiers;
	private int actionTaken;

	public EnvironmentState(int w, int h, Position ethan, Position sub, ArrayList<IMFmember> mem,
			ArrayList<IMFmember> sm, int dead, int cap, int carried) {
		this.width = w;
		this.height = h;
		this.ethanPos = ethan;
		this.submarinePos = sub;
		this.members = mem;
		this.deadSoldiersNumber = dead;
		this.capacity = cap;
		this.carriedSoldiers = carried;
		this.savedMembers = sm;
	}

	public EnvironmentState(String gridStr) { // for the beginning
		members = new ArrayList<>();
		savedMembers = new ArrayList<>();
		this.mapGrid2D(gridStr);
		this.carriedSoldiers = 0;
		this.deadSoldiersNumber = 0;
	}

	public void step(int action) {

		// the actions are mapped as numbers
		// 0 -> Up
		// 1-> Down
		// 2 -> Right
		// 3-> Left
		// 4 ->Carry
		// 5 ->Drop
		this.actionTaken = action;
		int x = this.ethanPos.getX();
		int y = this.ethanPos.getY();
		if (action < 4) {
			switch (action) {
			case 0:
				y--;
				break;
			case 1:
				y++;
				break;
			case 2:
				x++;
				break;
			case 3:
				x--;
				break;
			}
			if (!(x < 0 || x >= width || y < 0 || y >= height)) {
				this.ethanPos.setX(x);
				this.ethanPos.setY(y);
			}
		} else {

			if (action == 4 && this.carriedSoldiers < this.capacity) {

				IMFmember mem = getTheIMFmemberByPosition(this.ethanPos);
				if (mem != null) {

					savedMembers.add(mem);
					members.remove(mem);
					this.carriedSoldiers++;
				}
			} else if (action == 5 && ethanPos.compareTo(submarinePos) == 0) {
				carriedSoldiers = 0;
			}
		}

		this.updateDamages();
	}

	public void updateDamages() {
		for (int i = 0; i < this.members.size(); i++) {
			IMFmember mem = members.get(i);
			mem.updateDamage();
			if (mem.isDead()) {
				this.deadSoldiersNumber++;
			}
		}
	}
	
	private void mapGrid2D(String grid) {
		String[] info = grid.split(";");
		int[] mn = convertStringIntArr(info[0]);
		this.width = mn[0];
		this.height = mn[1];
		int[] ethanPosArr = convertStringIntArr(info[1]);
		int[] submarinePosArr = convertStringIntArr(info[2]);
		this.ethanPos = new Position(ethanPosArr[1], ethanPosArr[0]);
		this.submarinePos = new Position(submarinePosArr[1], submarinePosArr[0]);
		int[] soldiersPos = convertStringIntArr(info[3]);
		
		int[] damages = convertStringIntArr(info[4]);
		this.capacity = convertStringIntArr(info[5])[0];
		for (int i = 0; i < soldiersPos.length; i += 2) {
			members.add(new IMFmember(new Position(soldiersPos[i + 1], soldiersPos[i]), // see this error please.
					damages[i / 2]));
		}

		Comparator<IMFmember> com = Comparator.comparingDouble(mem -> this.getEucDis(mem.getPosition(), this.getEthanPosition())); 
		Collections.sort(members,com);

	}

	private IMFmember getTheIMFmemberByPosition(Position p) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getPosition().compareTo(p) == 0) {
				return members.get(i);
			}
		}
		return null;
	}

	private int[] convertStringIntArr(String str) {
		return Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
	}

	public EnvironmentState clone() {

		return new EnvironmentState(width, height, new Position(ethanPos.x, ethanPos.y),
				new Position(submarinePos.x, submarinePos.y), this.cloneArrayMembers(members),
				this.cloneArrayMembers(savedMembers), this.deadSoldiersNumber, this.capacity, this.carriedSoldiers);
	}

	private ArrayList<IMFmember> cloneArrayMembers(ArrayList<IMFmember> arr) {
		ArrayList<IMFmember> temp = new ArrayList<>();

		for (int i = 0; i < arr.size(); ++i) {
			IMFmember mem = arr.get(i);
			IMFmember newMem = new IMFmember(new Position(mem.getPosition().x, mem.getPosition().y), mem.getDamage());
			newMem.ID = mem.ID;
			temp.add(newMem);
		}
		return temp;
	}

	public int getNumberOfSavedSoldiers() {
		return this.savedMembers.size();
	}

	public Position getEthanPosition() {
		return this.ethanPos;
	}

	public boolean isGoal() {
		return this.members.size() == 0 && this.ethanPos.compareTo(this.submarinePos) == 0 && this.actionTaken == 5;
	}

	public int getCarriedSoldiers() {
		return this.carriedSoldiers;
	}
	
	public int getDeadSoldiers() { 
		return this.deadSoldiersNumber ; 
	}
	public Position getSubmarinePosition() {
		return this.submarinePos ; 
	}

	@Override
	public int compareTo(EnvironmentState o) {
		if (this.getEthanPosition().compareTo(o.getEthanPosition()) == 0
				&& this.getNumberOfSavedSoldiers() == o.getNumberOfSavedSoldiers()
				&& this.getCarriedSoldiers() == o.getCarriedSoldiers()) {
			return 0;
		}
		return 1;
	}
	public  double getEucDis(Position p1 , Position p2) { 
		int dis1 = p1.getX()-p2.getX();  
		int dis2= p1.getY()-p2.getY() ; 
		return Math.sqrt(Math.pow(dis1, 2) + Math.pow(dis2, 2) ) ; 
	}
	
	public double getHeuristicValueOne() {
		if (members.size()== 0) { 
			return this.getEucDis(this.getEthanPosition(), this.getSubmarinePosition());
		}
		double heuristicValue= this.getEucDis(this.getEthanPosition(), members.get(0).getPosition());
		for (int i = 1 ; i < members.size(); ++ i ) { 
			IMFmember mem = members.get(i) ; 
			double newCalc = this.getEucDis(this.getEthanPosition(),mem.getPosition()) ;
			heuristicValue=Math.min(newCalc, heuristicValue) ;  
		}
		return heuristicValue ; 
	}
	
	public double getHeuristicValueTwo() { 
		if (members.size()== 0) { 
			return this.getEucDis(this.getEthanPosition(), this.getSubmarinePosition());
		}
		IMFmember mem = members.get(0) ;
		double heuristicValue = mem.getHealth()/100 ; 
		if (heuristicValue==0) { 
			heuristicValue =1000; 
		}
		for (int i = 1 ; i < members.size(); ++ i ) { 
			mem = members.get(i) ;
			double newCalc = mem.getHealth()/100 ; 
			if (newCalc==0) { 
				newCalc =1000; 
			}
			heuristicValue=Math.min(newCalc, heuristicValue) ;  
		}
		return heuristicValue+this.getDeadSoldiers() ;  
	}
 	
	
	public double manhattenDistance(Position p1 , Position p2) { 
		return Math.abs(p1.getX()-p2.getX()) + Math.abs(p1.getY()-p2.getY()) ; 
	}
	
	public String getSoldiersHealths () { 
		String finalHealth ="" ;
		Comparator<IMFmember> soldiersIDs = Comparator.comparingInt(m -> m.getID()) ; 
		Collections.sort(this.savedMembers,soldiersIDs);
		for (int i = 0 ; i < savedMembers.size() ; ++i ) { 
			IMFmember m = savedMembers.get(i) ;
			if (i == savedMembers.size()-1) 
				finalHealth += m.getDamage();
			else 
				finalHealth += m.getDamage()+",";			
		}
		return finalHealth ; 
		
	}
	public double getCostFunction() { 
		double actualDis = 0 ;
		double deadNumber = this.getDeadSoldiers() ; 
		double damages = 0 ; 
		
		for (int i = 0 ; i < members.size() ; ++i ) { 
			IMFmember mem = members.get(i)  ;
			actualDis += manhattenDistance(this.getEthanPosition(), mem.getPosition()) ;
			damages += mem.getDamage() ; 
		}
		return deadNumber+ 0.2*actualDis + 0.1*damages ; 
	}
	
	public String toString() {
		return "Position: " + this.getEthanPosition() + ", Number of Saved Soldiers: " + this.getNumberOfSavedSoldiers()
				+ ", Carried Soldiers: " + this.getCarriedSoldiers();
	}
	
	

}
