
public class Coord_t {
	// Variables d'instance
	public int mX, mY;

	// Constructeur
	public Coord_t(int x, int y) {
		this.mX = x;
		this.mY = y;
	}

	// Getters
	public int getX() {
		return this.mX;
	}
	public int getY() {
		return this.mY;
	}

	// Setters
	public void setX(int X) {
		this.mX = X;
	}
	public void setY(int Y) {
		this.mY = Y;
	}

	// Clone
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Coord_t(mX, mY);
	}
}
