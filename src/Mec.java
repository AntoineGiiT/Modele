
class Mec {
	// Variables d'instance
	public double mX , mY , mDX , mDY;
	public int mSize;
	
	// Constructeur 
	public Mec(double x , double y , double dx , double dy , int size) {
		this.mX = x;
		this.mY = y;
		this.mDX = dx;
		this.mDY = dy;
		this.mSize = size;
	}
	
	// Getters
	public double getX() {
		return this.mX;
	}
	public double getY() {
		return this.mY;
	}
	public double getDX() {
		return this.mDX;
	}
	public double getDY() {
		return this.mDY;
	}
	public int getSize() {
		return this.mSize;
	}
	
	// Setters
	public void setX(double X) {
		this.mX = X;
	}
	public void setY(double Y) {
		this.mY  = Y;
	}
	public void setDX(double DX) {
		this.mDX = DX;
	}
	public void setDY(double DY) {
		this.mDY = DY;
	}
	public void setSize(int size) {
		this.mSize = size;
	}
	
	// Clone
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Mec(mX , mY , mDX , mDY , mSize);
	}	
}
