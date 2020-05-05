import javax.swing.JFrame;

public class Myfram extends JFrame{
	
	Demo a = new Demo();
	
	public Myfram() {
		this.setSize(800,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(a);
		this.setVisible(true);
	}
}
