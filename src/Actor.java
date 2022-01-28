import java.awt.Graphics;

public abstract class Actor {

	protected int x, y; // Coordenadas x e y del actor
	protected int ancho = 30, alto = 30; // ancho y alto que ocupa el actor en pantalla
	protected String img; // Imagen del actor
	
	/**
	 * 
	 */
	public Actor() {
		super();
	}
	
	/**
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @param img
	 */
	public Actor(int x, int y, int ancho, int alto, String img) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.img = img;
	}
	


	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	
	/**
	 * 
	 */
	public void actua() {
		
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

