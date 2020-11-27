package code.generic;

public abstract class State {

	
	
	public abstract void step(int action) ; 
	
	
	public abstract boolean isGoal(int action) ;  
}
