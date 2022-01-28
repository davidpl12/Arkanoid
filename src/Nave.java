import java.awt.Color;
import java.awt.Graphics;

public class Nave extends Actor {

	private int velocidadX = -5;

	/**
	 * 
	 */
	public Nave() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @param img
	 */
	public Nave(int x, int y, int ancho, int alto, String img, int velocidadX) {
		super(x, y, ancho, alto, img);
		this.velocidadX = velocidadX;
	}
	
	/**
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(this.y, this.x, this.ancho , this.alto);
	};
	
	/**
	 * 
	 */
	public void actua() {
		
	}

	/**
	 * @return the velocidadX
	 */
	public int getVelocidadX() {
		return velocidadX;
	}

	/**
	 * @param velocidadX the velocidadX to set
	 */
	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}
	
	
	
	
	
	
	
	
}
