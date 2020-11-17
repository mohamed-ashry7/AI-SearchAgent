import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class EnvironmentState implements Cloneable, Comparable<EnvironmentState> {

	private int width;
	private int height;
	private Position ethanPos;
	private Position submarinePos;
	private ArrayList<IMFmember> members;
	private PriorityQueue<IMFmember> savedMembers;
	private int deadSoldiersNumber;
	private int capacity;
	private int carriedSoldiers;	
	private int actionTaken; 
	
	public EnvironmentState(String gridStr) {
		members = new ArrayList<>() ; 
		savedMembers = new PriorityQueue<>(new Comparator<IMFmember>() {

			@Override
			public int compare(IMFmember o1,IMFmember o2) {

				return o1.ID - o2.ID;
			}

		});
		this.mapGrid2D(gridStr);
		this.carriedSoldiers = 0;
		this.deadSoldiersNumber = 0 ; 
	}

	public EnvironmentState step(int action) throws CloneNotSupportedException {

		// the actions are mapped as numbers
		// 0 -> Up
		// 1-> Down
		// 2 -> Right
		// 3-> Left
		// 4 ->Carry
		// 5 ->Drop
		this.actionTaken = action ; 
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
			} else if (action == 5 && ethanPos.equals(submarinePos)) {
				carriedSoldiers = 0;
			}
		}
		
		this.updateDamages();
		return this; 
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
		this.ethanPos = new Position(ethanPosArr[0], ethanPosArr[1]);
		this.submarinePos = new Position(submarinePosArr[0], submarinePosArr[1]);
		int[] soldiersPos = convertStringIntArr(info[3]);
		int[] damages = convertStringIntArr(info[4]);
		this.capacity = convertStringIntArr(info[5])[0];
		for (int i = 0; i < soldiersPos.length; i += 2) {
			members.add(
					new IMFmember(new Position(soldiersPos[i], soldiersPos[i + 1]),
					damages[i / 2]));
		}

	}

	private IMFmember getTheIMFmemberByPosition(Position p) {
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getPosition().equals(p)) {
				return members.get(i);
			}
		}
		return null;
	}

	private int[] convertStringIntArr(String str) {
		return Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray();
	}
	
	public EnvironmentState clone()throws CloneNotSupportedException{  
		return (EnvironmentState)super.clone();  
	}
	
	public int getNumberOfSavedSoldiers() { 
		return this.savedMembers.size(); 
	}
	public Position getEthanPosition() { 
		return this.ethanPos ; 
	}
	
	public boolean isGoal() { 
		return  this.members.size()==0 &&
				this.ethanPos.equals(this.submarinePos)&&
				this.actionTaken==5; 	
	}
	
	public int getCarriedSoldiers() { 
		return this.carriedSoldiers ; 
	}

	@Override
	public int compareTo(EnvironmentState o) {
//		System.out.println(this.getEthanPosition().compareTo(o.getEthanPosition()));
		if( this.getEthanPosition().compareTo(o.getEthanPosition())== 0 &&
			this.getNumberOfSavedSoldiers() == o.getNumberOfSavedSoldiers() &&
			this.getCarriedSoldiers() == o.getCarriedSoldiers()) { 
			return 0 ; 
		}
		return 1 ; 
	}
	
	public String toString() { 
		return "Position: "+this.getEthanPosition() + ", Number of Saved Soldiers: "+ this.getNumberOfSavedSoldiers() + ", Carried Soldiers: " + this.getCarriedSoldiers() ; 
	}
	
	
}
