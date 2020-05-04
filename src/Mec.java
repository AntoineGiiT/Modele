
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
	
	// Clone
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Mec(mX , mY , mDX , mDY , mSize);
	}	
}
