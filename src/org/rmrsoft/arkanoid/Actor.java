package org.rmrsoft.arkanoid;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public abstract class Actor {

	protected int x, y; // Coordenadas x e y del actor
	protected int ancho = 30, alto = 30; // ancho y alto que ocupa el actor en pantalla
	protected String img; // Imagen del actor
	protected BufferedImage spriteActual;
	protected boolean marcadoParaEliminacion = false;
	// Posibilidad de que el actor sea animado, a travï¿½s del siguiente array de sprites y las variables
	// velocidadDeCambioDeSprite y unidadDeTiempo
	protected List<BufferedImage> spritesDeAnimacion = new ArrayList<BufferedImage>();
	protected int velocidadDeCambioDeSprite = 0;
	private int unidadDeTiempo = 0;

	
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
	 * Método que se podrá sobrescribir en los subtipos para decidir la acción a realizar al colisionar
	 * con otro actor
	 * @param a
	 */
	public void colisionaCon(Actor a) {
	}

	
	public void paint(Graphics g) {
		g.drawImage(this.spriteActual, this.x, this.y, null);		
	}
	
	/**
	 * 
	 */
	public void actua() {
		// En el caso de que exista un array de sprites el actor actual se tratarï¿½ de una animaciï¿½n, para eso llevaremos a cabo los siguientes pasos
		if (this.spritesDeAnimacion != null && this.spritesDeAnimacion.size() > 0) {
			unidadDeTiempo++;
			if (unidadDeTiempo % velocidadDeCambioDeSprite == 0){
				unidadDeTiempo = 0;
				int indiceSpriteActual = spritesDeAnimacion.indexOf(this.spriteActual);
				int indiceSiguienteSprite = (indiceSpriteActual + 1) % spritesDeAnimacion.size();
				this.spriteActual = spritesDeAnimacion.get(indiceSiguienteSprite);
			}
		}
	}
	
	/**
	 * @return the img
	 */
	public BufferedImage getSpriteActual() {
		return this.spriteActual;
	}

	/**
	 * @param img the img to set
	 */
	public void setSpriteActual(BufferedImage spriteActual) {
		this.spriteActual = spriteActual;
		this.ancho = this.spriteActual.getWidth();
		this.alto = this.spriteActual.getHeight();
	}
	
	/**
	 * @return the spritesDeAnimacion
	 */
	public List<BufferedImage> getSpritesDeAnimacion() {
		return spritesDeAnimacion;
	}

	/**
	 * @param spritesDeAnimacion the spritesDeAnimacion to set
	 */
	public void setSpritesDeAnimacion(List<BufferedImage> spritesDeAnimacion) {
		this.spritesDeAnimacion = spritesDeAnimacion;
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
	
}

