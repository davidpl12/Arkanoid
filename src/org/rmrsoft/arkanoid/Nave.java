package org.rmrsoft.arkanoid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Nave extends Actor {
	
	//Propiedades estáticas de esta clase
		public static String IMAGEN_PLAYER = "naveArkanoid.gif";

	private int velocidadX = -5;
	private boolean izquierda =false;
	private boolean derecha =false;

	/**
	 * 
	 */
	public Nave() {
		super();
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
		this.setSpriteActual(ResourcesCache.getInstance().getImagen(ResourcesCache.IMAGEN_PLAYER));
	}
	
	/**
	 * 
	 * @param g
	 */
//	public void paint(Graphics g) {
//		g.setColor(Color.ORANGE);
//		g.fillRect(this.x, this.y, this.ancho , this.alto);
//	};
//	
	/**
	 * 
	 */
	public void actua() {
		if(izquierda) this.x -=velocidadX;
		if(derecha) this.x +=velocidadX;
		
		mover(this.x);
		}
	
	
	public void mover(int x) {
		this.x = x;

		// Controlo los casos en los que el jugador pueda salir del Canvas
		MiCanvas canvas = Arkanoid.getInstance().getCanvas(); // Referencia al objeto Canvas usado
		
		// Compruebo si el jugador sale por la derecha
		if (this.x > 635) {
			this.x = 635;
		}

		// Compruebo si el jugador sale por la izquierda
		if (this.x < 0) {
			this.x = 0;
		}
		
	}
	
	/**
	 * Se ejecuta al recibir un evento del teclado: tecla presionada
	 * @param e
	 */
	public void keyPressed (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = true; break;
		case KeyEvent.VK_RIGHT:
			derecha = true; break;
		}
		
	}
	
	/**
	 * Se ejecuta al recibir un evento del teclado: tecla liberada
	 * @param e
	 */
	public void keyReleased (KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = false; break;
		case KeyEvent.VK_RIGHT:
			derecha = false; break;
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
	
		
	
	
	
	
}
