
class Obstacle {
	// Varibales d'instance
	public int mX , mY , mL , mH;
	
	// Constructeur
	public Obstacle(int x , int y , int l ,int  h) {
		this.mX = x;
		this.mY = y;
		this.mL = l;
		this.mH = h;
	}
	
	// Getters
	public int getX() {
		return this.mX;
	}
	public int getY() {
		return this.mY;
	}
	public int getL() {
		return this.mL;
	}
	public int getH() {
		return this.mH;
	}
	
	// Setters
	public void setX(int X) {
		this.mX = X;
	}
	public void setY(int Y) {
		this.mY = Y;
	}
	public void setL(int L) {
		this.mL = L;
	}
	public void setH(int H) {
		this.mH = H;
	}
	
	// Clone
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Obstacle(mX , mY , mL , mH);
	}
}