import java.awt.Color;
import java.awt.Graphics;



public class Ladrillo extends Actor {
	public Color color;
	/**
	 * 
	 */
	public Ladrillo() {
		super();
	}
	
	/**
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.ancho , this.alto);
	};

	/**
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @param img
	 */
	public Ladrillo(int x, int y, int ancho, int alto, String img, Color color) {
		super(x, y, ancho, alto, img);
		this.color = color;
	}

	/**
	 * Se disparara cuando un actor colisione con el monstruo
	 */
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
//		System.out.println("Ladrillo colisiona con: " + a);
		// Si colisionamos con un player o un disparo, eliminamos al monstruo
		if (a instanceof Pelota) {
//			System.out.println("Elimino ladrillo: " + this);
		//	System.out.println("al ladrillo le llega la pelota");
			Arkanoid.getInstance().eliminaActor(this);
		}
	}
	
	
}
