package code.mission;

public class Position implements Comparable<Position>, Cloneable {
	int x;
	int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int compareTo(Position other) {
		if (this.x == other.x && this.y == other.y) {
			return 0;
		} else {
			if (this.x - other.x == 0) {
				return this.y - other.y;
			} else {
				return this.x - other.x;
			}
		}
	}

	public String toString() {
		return String.format("( %d, %d )", this.x, this.y);
	}

	public Position clone() throws CloneNotSupportedException {
		return (Position) super.clone();
	}
}