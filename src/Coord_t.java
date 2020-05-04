
public class Coord_t {
	// Variables d'instance
	public int mX , mY;
	
	// Constructeur
	public Coord_t (int x , int y) {
		this.mX = x;
		this.mY = y;
	}
	
	// Clone
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Coord_t(mX , mY);
	}
}

