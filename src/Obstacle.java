
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
	
	// Clone
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Obstacle(mX , mY , mL , mH);
	}
}