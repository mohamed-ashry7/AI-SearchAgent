
public class IMFmember implements Comparable<IMFmember> {
		static int counter = 0;
		Position pos;
		int damage;
		int ID;

		public IMFmember(Position p, int d) {
			this.ID = ++counter;
			this.pos = p;
			this.damage = d;
		}

		public int getDamage() {
			return this.damage;
		}

		public Position getPosition() {
			return pos;
		}

		public void updateDamage() {
			damage = damage < 99 ? damage += 2 : 100;
		}
		public boolean isDead() {
			return damage>=100;
		}
		@Override
		public int compareTo(IMFmember o) {
			return this.pos.compareTo(o.pos);
		}
		

	}
