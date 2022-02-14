import java.awt.Color;
import java.awt.Graphics;


public class Pelota extends Actor {

	private int velocidadX = -5;
	private int velocidadY = -5;
		
	
	/**
	 * 
	 */
	public Pelota() {
		super();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @param img
	 * @param velocidadX
	 * @param velocidadY
	 */
	public Pelota(int x, int y, int ancho, int alto, String img, int velocidadX, int velocidadY) {
		super(x, y, ancho, alto, img);
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
	}
	


	/**
	 * 
	 * @param g
	 */
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, ancho, alto);
	};
	
	/**
	 * 
	 */
	@Override
	public void actua() {
		// La pelota se mueve de manera horizontal, en cada FPS
		this.x += this.velocidadX;
		// Si la pelota abandona la escena por la izquierda o la derecha, rebota
		if (this.x < 0 || this.x > 770) {
			this.velocidadX = -this.velocidadX;
		}
		
		// Copiamos el esquema anterior para el movimiento vertical
		this.y += this.velocidadY;
		// Si la pelota abandona la escena por la izquierda o la derecha, rebota
		if (this.y < 0 || this.y > 615) {
			this.velocidadY = -this.velocidadY;
		}
		
	}
	
	/**
	 * Este metodo se dispara cuando un ladrillo colisione con la pelota
	 */
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);

		if (a instanceof Ladrillo) {
			this.velocidadY *= -1;
		}
		if (a instanceof Nave) {
			this.velocidadY *= -1;
		}
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

	/**
	 * @return the velocidadY
	 */
	public int getVelocidadY() {
		return velocidadY;
	}

	/**
	 * @param velocidadY the velocidadY to set
	 */
	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	
	
	
	
}


