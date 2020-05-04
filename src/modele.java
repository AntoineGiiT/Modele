
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class modele extends JPanel {
	// Varialbes d'instance
	private Deque<Mec> individus = new ArrayDeque<>();
	private Deque<Obstacle> obstacles = new ArrayDeque<>();
	private Deque<Out> outs = new ArrayDeque<>();

	private Deque<Coord_t> coord_t_liste = new ArrayDeque<>();
	private Deque<Deque<Coord_t>> coords_liste = new ArrayDeque<>();

	public int nb_people = 1;
	public int nb_obstacle = 0;
	public int nb_out = 1; // Change rien

	final static int WIDTH = 700;
	final static int HEIGHT = 500;

	// Constructeur
	public modele() {
		/*
		if(nb_out == 0) {
			System.out.println("Il n'y a aucunes issues");
		} else {
		*/
			System.out.println("Initialisation ...");
			// LES VARIABLES
			int fV = 50; //Facteur de multiplication des vecteurs vitesses
			int fV2 = 70; // Facteur de multiplication des vecteurs accélération
			float fR = (float)70; // Facteur de répulsion des objets entre eux.
			float fS = (float)1; // Facteur d'attirance des portes.
			float v_max = 1; // Vitesse maximale des individus entre eux.
		
			// CREATION DES INDIVIDUS
			for(int i = 1; i <= nb_people ; i++) {
				double x , y , dx , dy;
				int size;
				size = 10;   
				x = Math.random()*(WIDTH - size);
				y = Math.random()*(HEIGHT - 28 - size);
				dx = Math.random()*(1);
				dy = Math.random()*(1);
				individus.add(new Mec(x,y,dx,dy,size));
				}
		
			// CREATION DES OBSTACLES
			for(int i = 1; i <= nb_obstacle ; i++ ) {
				obstacles.add(new Obstacle((int)(Math.random()*WIDTH) , (int)(Math.random()*HEIGHT),(int)(Math.random()*150) , (int)(Math.random()*150)));
			}
		
			// CREATION DES SORTIES
			for(int i = 1 ; i <= nb_out ; i++) {
				int size = 30;
				//int x = (int)(Math.random()*(WIDTH-size));
				//int y = (int)(Math.random()*(HEIGHT-size));
				int x = WIDTH - size/2 -10;
				int y = HEIGHT/2;
				outs.add(new Out(x , y , size));
			}
			System.out.println("Calculs ...");
		
			// CALCULS
			while (individus.size() > 0) {
				//System.out.println(individus.size());
				for(Mec p : individus) {
					double ddx = 0.0;
					double ddy = 0.0;
				
					//REPULSION ENTRE EUX
					for(Mec q : individus) {
						if(p != q) {
							double dist_x = (q.mX + q.mSize/2) - (p.mX + p.mSize/2);
							double dist_y = (q.mY + q.mSize/2) - (p.mY + p.mSize/2);
							double D = Math.sqrt(Math.pow(dist_x , 2) + Math.pow(dist_y , 2));
							ddx += -fR/Math.pow(D,3)*dist_x;
							ddy += -fR/Math.pow(D,3)*dist_y;
						}	
					}
				
					// TO OUT
					for(Out s : outs) {
							double dist_x = (s.mX + s.mSize/2) - (p.mX + p.mSize/2);
							double dist_y = (s.mY + s.mSize/2) - (p.mY + p.mSize/2);	
							double D = Math.sqrt(Math.pow(dist_x , 2) + Math.pow(dist_y , 2));
							if( D <= s.mSize/2) {
								individus.remove(p);
							}
							ddx += fS*dist_x/D;
							ddy += fS*dist_y/D;
						}
				
					//COLLISION AVEC LES OBSTACLES
					double X = p.mX + p.mDX;
					double Y = p.mY + p.mDY;
					for(Obstacle o : obstacles) {
						if((X + p.mSize >= o.mX && p.mX + p.mSize < o.mX && Y + p.mSize >= o.mY && Y - p.mSize <= o.mY + o.mH) || (X - p.mSize <= o.mX + o.mL && p.mX - p.mSize > o.mX + o.mL && Y + p.mSize >= o.mY && Y - p.mSize <= o.mY + o.mH)){
							p.mDX = - p.mDX;
						}
						if((Y + p.mSize >= o.mY && p.mY + p.mSize < o.mY && X + p.mSize >= o.mX && X - p.mSize <= o.mX + o.mL) || (Y - p.mSize <= o.mY + o.mH && p.mY - p.mSize > o.mY + o.mH && X + p.mSize >= o.mX && X - p.mSize <= o.mX + o.mL)){
							p.mDY = - p.mDY;
						}
					}
				
					// VITESSE MAX
					double v = Math.sqrt(Math.pow(p.mDX + ddx,2) + Math.pow(p.mDY,2));
					if (v >= v_max) {
						p.mDX = (p.mDX + ddx)/v*v_max;
						p.mDY = (p.mDY + ddy)/v*v_max;
					} else {
						p.mDX += ddx;
						p.mDY += ddy;
					}
				
					// COLLISION AVEC LES BORDS
					if(X < 0 || X > WIDTH - p.mSize/2 - 7){
						p.mDX = -p.mDX;
					}
					if(Y < 0 || Y > HEIGHT - p.mSize - 30) {
						p.mDY = -p.mDY;
					}
					p.mX += p.mDX ;
					p.mY += p.mDY ;
				
					// ENREGISTREMENT DES DONNEES INDIVIDUELS POUR REPRESENTATION GRAPHIQUE
					coord_t_liste.add(new Coord_t((int)p.mX , (int)p.mY));
				}
			
				// ENREGISTREMENT COLLECTIF POUR UNE DATE T
				coords_liste.add(coord_t_liste);
				coord_t_liste = new ArrayDeque<>();
			}
			System.out.println("Rendu graphique ...");
		
			// RENDU GRAPHIQUE DES CALCULS
			setBackground(Color.BLACK);
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true) {
						repaint();
						try {
							Thread.sleep(1000/60l);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		
			// UPDATE GRAPHIQUE
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
	

	// Méthodes
	public static void creatFrame(int w, int h) {
		System.out.println("Création de la fenetre de modelisation... ");
		JFrame frame = new JFrame("MODELISATION D'UNE FOULE");
		modele panel = new modele();
		frame.setContentPane(panel);
		frame.setSize(w, h);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		System.out.println("Fenêtre créer.");
	}

	// Main
	public static void main(String[] arg) {
		System.out.println("Début de la modélisation\n");
		creatFrame(WIDTH, HEIGHT);
		System.out.println("Fin de la modélisation");
	}
}