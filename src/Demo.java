
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;
import java.util.Deque;



public class Demo extends JPanel {
	
	public Demo() {
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// UPDATE GRAPHIQUE
		modele m = new modele()
		g.setColor(Color.BLUE);
		for(Obstacle o : obstacles) {
			g.fillRect(o.mX , o.mY , o.mL , o.mH);
		}
		g.setColor(Color.ORANGE);
		for(Out s : outs) {
			g.fillRect(s.mX , s.mY , s.mSize , s.mSize);	
		}
		if (coords_liste.size() > 0) {
			g.setColor(Color.YELLOW);
			Deque<Coord_t> coord_at_t = new ArrayDeque<>();
			coord_at_t = coords_liste.remove();
			for(Coord_t c : coord_at_t) {
			g.fillOval(c.mX , c.mY , 8 , 8);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		x = x + velocityX;
		y = y + velocityY;
		repaint();
		
		seconds = seconds + 0.1;
		//if(evacationDone) {
		if(seconds>100.1) {
			timer.stop();
			System.out.println("L'évacuation s'est faite en " + seconds*0.1 + "secondes");
		}
	}
}
