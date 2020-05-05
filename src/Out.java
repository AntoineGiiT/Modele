
class Out {
	// Variables d'instance
	public int mX , mY , mSize;
		
	// Constructeur
	public Out (int x , int y, int size) {
		this.mX = x;
		this.mY = y;
		this.mSize = size;
		}
	
	// Getters
	public int getX() {
		return this.mX;
	}
	public int getY() {
		return this.mY;
	}
	public int getSize() {
		return this.mSize;
	}
	
	// Setters
	public void setX(int X) {
		this.mX = X;
	}
	public void setY(int Y) {
		this.mY = Y;
	}
	public void setSize(int size) {
		this.mSize = size;
	}
		
	// Clone			
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Out(mX , mY , mSize);
	}
}