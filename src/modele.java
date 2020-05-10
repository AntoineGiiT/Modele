import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class modele extends JPanel {
	// Varialbes d'instance
	public int nb_people = 10;
	public int nb_obstacle = 0;
	public int nb_out = 1; // Nombre de portes mais change rien
	public double fV = 50;// Facteur de multiplication des vecteurs vitesses
	public double fV2 = 70;// Facteur de multiplication des vecteurs accélération
	public double fR = 70.0;// Facteur de répulsion des objets entre eux.
	public double fS = 1.0;// Facteur d'attirance des portes.
	public double Vmax = 1.0;// Vitesse maximale des individus entre eux.

	private Deque<Mec> individus = new ArrayDeque<>();
	private Deque<Obstacle> obstacles = new ArrayDeque<>();
	private Deque<Out> outs = new ArrayDeque<>();
	public Deque<Deque<Coord_t>> simu ;

	final static int WIDTH = 700;
	final static int HEIGHT = 500;

	// Constructeur
	public modele(int nb_people, int nb_obstacle, int nb_out, double fV, double fV2, double fR, double fS,
			double Vmax) {
		this.nb_out = nb_out;
		if (nb_out == 0) {
			System.out.println("Il n'y a aucunes issues dans ce modèle");
		} else {
			this.nb_people = nb_people;
			this.nb_obstacle = nb_obstacle;
			this.fV = fV;
			this.fV2 = fV2;
			this.fR = fR;
			this.fS = fS;
			this.Vmax = Vmax;
			this.individus = createIndividus(nb_people);
			this.obstacles = createObstacles(nb_obstacle);
			this.outs = createSorties(nb_out);
		}
	}

	

	// Getters
	public int getNbPeople() {
		return this.nb_people;
	}

	public int getNbObstacles() {
		return this.nb_obstacle;
	}

	public int getNbSorties() {
		return this.nb_out;
	}

	public double getFV() {
		return this.fV;
	}

	public double getFV2() {
		return this.fV2;
	}

	public double getFR() {
		return this.fR;
	}

	public double getFS() {
		return this.fS;
	}

	public double getVmax() {
		return this.Vmax;
	}

	public Deque<Mec> getIndividus() {
		return this.individus;
	}

	public Deque<Out> getSorties() {
		return this.outs;
	}

	public Deque<Obstacle> getObstacles() {
		return this.obstacles;
	}
	
	public Deque<Deque<Coord_t>> getSimu(){
		return this.simu;
	}

	// Setters
	public void setNbPeople(int nbPeople) {
		this.nb_people = nbPeople;
	}

	public void setNbObstacles(int nbObstacles) {
		this.nb_obstacle = nbObstacles;
	}

	public void setNbSorties(int nbSorties) {
		this.nb_out = nbSorties;
	}

	public void setFV(double FV) {
		this.fV = FV;
	}

	public void setFV2(double FV2) {
		this.fV2 = FV2;
	}

	public void setFR(double FR) {
		this.fR = FR;
	}

	public void setFS(double FS) {
		this.fS = FS;
	}

	public void setVmax(double Vmax) {
		this.Vmax = Vmax;
	}

	public void setIndividus(int nbPeople) {
		this.individus = createIndividus(nbPeople);
	}

	public void setSorties(int nbSorties) {
		this.outs = createSorties(nbSorties);
	}

	public void setObstacles(int nbObstacles) {
		this.obstacles = createObstacles(nbObstacles);
	}

	public void setIndividus(Deque<Mec> individus_liste) {
		this.individus = individus_liste;
	}
	
	public void setSimu(Deque<Deque<Coord_t>> simu) {
		this.simu = simu;
	}

	// Méthodes
	public static Deque<Deque<Integer>> toDequeDequeInt(Deque<Deque<Coord_t>> a){
		Deque<Deque<Integer>> result = new ArrayDeque<>();
		int size = a.size();
		for(int i=0; i< size; i++){
			Deque<Coord_t> b = a.remove();
			Coord_t c = b.remove();
			int x = c.getX();
			int y = c.getY();
			Deque<Integer> result1 = new ArrayDeque<>();
			result1.add(x);
			result1.add(y);
			result.add(result1);
		}
		return result;
	}
	
	public Deque<Mec> createIndividus() {
		int nbPeople = this.getNbPeople();
		for (int i = 1; i <= nbPeople; i++) {
			double x, y, dx, dy;
			int size = 10;
			x = Math.random() * (WIDTH - size);
			y = Math.random() * (HEIGHT - 28 - size);
			dx = Math.random() * (1);
			dy = Math.random() * (1);
			individus.add(new Mec(x, y, dx, dy, size));
		}
		return individus;
	}

	public Deque<Mec> createIndividus(int nbPeople) {
		for (int i = 1; i <= nbPeople; i++) {
			double x, y, dx, dy;
			int size = 10;
			x = Math.random() * (WIDTH - size);
			y = Math.random() * (HEIGHT - 28 - size);
			dx = Math.random() * (1);
			dy = Math.random() * (1);
			individus.add(new Mec(x, y, dx, dy, size));
		}
		return individus;
	}

	public Deque<Out> createSorties() {
		int nbOut = this.getNbSorties();
		for (int i = 1; i <= nbOut; i++) {
			int size = 30;
			// int x = (int)(Math.random()*(WIDTH-size));
			// int y = (int)(Math.random()*(HEIGHT-size));
			int x = WIDTH - size / 2 - 10;
			int y = HEIGHT / 2;
			outs.add(new Out(x, y, size));
		}
		return outs;
	}

	public Deque<Out> createSorties(int nbSorties) {
		for (int i = 1; i <= nbSorties; i++) {
			int size = 30;
			// int x = (int)(Math.random()*(WIDTH-size));
			// int y = (int)(Math.random()*(HEIGHT-size));
			int x = WIDTH - size / 2 - 10;
			int y = HEIGHT / 2;
			outs.add(new Out(x, y, size));
		}
		return outs;
	}

	public Deque<Obstacle> createObstacles() {
		int nbObstacles = this.getNbObstacles();
		for (int i = 1; i <= nbObstacles; i++) {
			obstacles.add(new Obstacle((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT),
					(int) (Math.random() * 150), (int) (Math.random() * 150)));
		}
		return obstacles;
	}

	public Deque<Obstacle> createObstacles(int nbObstacles) {
		for (int i = 1; i <= nbObstacles; i++) {
			obstacles.add(new Obstacle((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT),
					(int) (Math.random() * 150), (int) (Math.random() * 150)));
		}
		return obstacles;
	}

	public void repulsionIndividus() {
		Deque<Mec> individus = this.getIndividus();
		for (Mec p : individus) {
			double ddx = 0.0;
			double ddy = 0.0;
			for (Mec q : individus) {
				if (p != q) {
					double dist_x = (q.getX() + q.getSize() / 2) - (p.getX() + p.getSize() / 2);
					double dist_y = (q.getY() + q.getSize() / 2) - (p.getY() + p.getSize() / 2);
					double D = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
					ddx += -this.getFR() / Math.pow(D, 3) * dist_x;
					ddy += -this.getFR() / Math.pow(D, 3) * dist_y;
					p.setDX(p.getDX() + ddx);
					p.setDY(p.getDY() + ddy);
				}
			}
		}
	}

	public boolean attirancePorte() {
		Deque<Out> sorties = this.getSorties();
		Deque<Mec> individus = this.getIndividus();

		boolean rep = false;

		for (Mec p : individus) {
			double ddx = 0.0;
			double ddy = 0.0;
			for (Out s : sorties) {
				double dist_x = (s.getX() + s.getSize() / 2) - (p.getX() + p.getSize() / 2);
				double dist_y = (s.getY() + s.getSize() / 2) - (p.getY() + p.getSize() / 2);
				double D = Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
				ddx += fS * dist_x / D;
				ddy += fS * dist_y / D;
				p.setDX(p.getDX() + ddx);
				p.setDY(p.getDY() + ddy);

				if (D < s.getSize()) {
					rep = true;
				}
			}
		}
		return rep;
	}

	public void repulsionObstacles() {
		Deque<Mec> individus = this.getIndividus();
		Deque<Out> sorties = this.getSorties();
		for (Mec p : individus) {
			double xm = p.getX();
			double ym = p.getY();
			int msize = p.getSize();
			double dxm = p.getDX();
			double dym = p.getDY();
			for (Out o : sorties) {
				double xo = o.getX();
				double yo = o.getY();
				int osize = o.getSize();
				if (((xo < xm + msize + dxm) && (xm + msize + dxm < xo + osize))
						&& ((yo < ym + msize + dym) && (ym + msize + dym < yo + osize))) {
					if ((xo < xm + msize + dxm) && (xm + msize + dxm < xo + osize)) {
						p.setDX(0);// On annule la composante qui fait entrer dans l'obstacle
					} else if ((yo < ym + msize + dym) && (ym + msize + dym < yo + osize)) {
						p.setDY(0);// On annule la composante qui fait entrer dans l'obstacle
					}
				}
			}
		}
	}

	public void repulsionBords() {
		Deque<Mec> individus = this.getIndividus();
		for (Mec p : individus) {
			if (p.getX() < 0 || p.getX() > WIDTH - p.getSize() / 2 - 8) {
				p.setDX(-p.getDX());
			}
			if (p.getY() < 0 || p.getY() > HEIGHT - p.getSize() - 30) {
				p.setDY(-p.getDY());
			}
		}
	}

	public void vitesseMax() {
		Deque<Mec> individus = this.getIndividus();
		for (Mec p : individus) {
			double ddx = 0.0;
			double ddy = 0.0;
			double v = Math.sqrt(Math.pow(p.getDX() + ddx, 2) + Math.pow(p.getDY(), 2));
			if (v >= Vmax) {
				p.setDX((p.getDX() + ddx) / v * this.getVmax());
				p.setDY((p.getDY() + ddy) / v * this.getVmax());
			}
		}
	}

	public static Deque<Mec> clone(Deque<Mec> clone) {
		Deque<Mec> cloned = new ArrayDeque<>();
		for (Mec m : clone) {
			cloned.add(m);
		}
		return cloned;
	}

	public static void creatFrame(int w, int h) {
		System.out.println("Création de la fenetre de modelisation... ");
		JFrame frame = new JFrame("MODELISATION D'UNE FOULE");
		modele panel = new modele(100,0,1,1,1,70.0,1.0,1.0);
		frame.setContentPane(panel);
		frame.setSize(w, h);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		System.out.println("Fenêtre créée.");
		frame.setBackground(Color.BLACK);
	}
	
	/*
	public void afficheTest() {
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
	*/
	
	public void affichage(Deque<Deque<Coord_t>> simulation) {
		
	}

	
	public Deque<Deque<Coord_t>> Calculs() {
		int nb = this.getIndividus().size();

		Deque<Deque<Coord_t>> coords_liste = new ArrayDeque<>();
		int cpt = 0; //nb d'itérations dans la boucle while
		while (nb > 0 && cpt<50) {
			cpt++;
			if(cpt == 9999) {
				System.out.println("Les individus ne trouvent pas la sortie");
				Coord_t erreur = new Coord_t(100000,100000);
				Deque<Coord_t> erreur2 = new ArrayDeque<>();
				erreur2.add(erreur);
				Deque<Deque<Coord_t>> result = new ArrayDeque<>();
				result.add(erreur2);
				return result;
			}
			System.out.println(nb);
			Deque<Mec> individus = this.getIndividus();
			Deque<Coord_t> coord_t_liste = new ArrayDeque<>();

			Deque<Mec> aSuppr = new ArrayDeque<>();
			for (Mec p : individus) {

				boolean is_out = false;

				this.repulsionIndividus();
				is_out = this.attirancePorte();
				this.repulsionObstacles();
				this.repulsionBords();

				this.vitesseMax();

				if (!(is_out)) {
					int x = (int) (p.getX() + p.getDX());
					int y = (int) (p.getY() + p.getDY());
					coord_t_liste.add(new Coord_t(x, y));

					p.setX(p.getX() + p.getDX());
					p.setX(p.getX() + p.getDX());
				} else {
					aSuppr.add(p);
				}
			}
			for (Mec q : aSuppr) {
				individus.remove(q);
			}
			coords_liste.add(coord_t_liste);
			nb = individus.size();
		}
		return coords_liste;
	}

	// Main
	public static void main(String[] arg) {
		System.out.println("Début de la modélisation\n");
		// CREATION
		modele m = new modele(10, 0, 1, 1, 1, 70.0, 1.0, 1.0);

		// CALCULS
		Deque<Deque<Coord_t>> simulation = m.Calculs();
		m.setSimu(simulation);
		System.out.println(toDequeDequeInt(simulation).toString());
		// AFFICHAGE
		// Affichage(simulation,m.getObstacles(),m.getSorties());
		System.out.println("Fin de la modélisation");
	}
}
/*
//Partie affichage
 * @Override
		protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Deque<Obstacle> obstacles = this.getObstacles();
		Deque<Deque<Coord_t>> coords_liste = this.Calculs();
		Deque<Out> outs = this.getSorties();
		for (Obstacle o : obstacles) {
			g.setColor(Color.BLUE);
			g.fillRect(o.mX, o.mY, o.mL, o.mH);
		}
		Deque<Coord_t> coords_t_liste = coords_liste.remove();
		for (Coord_t c : coords_t_liste) {
			g.setColor(Color.YELLOW);			
			g.fillOval(c.mX, c.mY, 10, 10);
		}
		for (Out s : outs) {
			g.setColor(Color.ORANGE);
			g.fillRect(s.mX, s.mY, s.mSize, s.mSize);
		}
	}

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
*/