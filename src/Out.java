
class Out {
	// Variables d'instance
	public int mX , mY , mSize;
		
	// Constructeur
	public Out (int x , int y, int size) {
		this.mX = x;
		this.mY = y;
		this.mSize = size;
		}
	
	// Clone			
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Out(mX , mY , mSize);
	}
}