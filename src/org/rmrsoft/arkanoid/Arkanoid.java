package org.rmrsoft.arkanoid;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;




public class Arkanoid {
	
	private static List<Actor> actores = new ArrayList<Actor>();
	private static final int FPS = 60;
	private static MiCanvas canvas = null;
	private static Arkanoid instance = null;
	private static Nave n = null;
	private static Pelota p = null;
	
	private static List<Actor> actoresParaEliminar = new ArrayList<Actor>();
	private static List<Actor> actoresParaIncorporar = new ArrayList<Actor>();

	public static void main(String[] args) {
		
		ResourcesCache.getInstance().cargarRecursosEnMemoria();
		
		JFrame ventana = new JFrame("Arkanoid");
		ventana.setBounds(0, 0, 800, 675);

		//Plantilla donde se colocan los objetos
		ventana.getContentPane().setLayout(new BorderLayout());
		
		// Creo una lista de actores que intervendrán en el juego.
		List<Actor> actores = creaActores();
		
		//Creo nuevos canvas sobre los que dibujar
		MiCanvas canvas = new MiCanvas(actores);
		ventana.getContentPane().add(canvas, BorderLayout.CENTER);
		
		
		canvas.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				n.mover(e.getX());
			}			
		});
		
		// Desvío los eventos de teclado hasta el jugador
		canvas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				n.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				n.keyReleased(e);
			}
		});
		
		
		canvas.requestFocus();
		// Consigo que la ventana no se redibuje por los eventos de Windows
		ventana.setIgnoreRepaint(true);
		// Hago que la ventana sea visible
		ventana.setVisible(true);
		
		
		
		// Comienzo un bucle, que consistirá en el juego completo.
		int millisPorCadaFrame = 1000 / FPS;
		do {
			
			// Por tanto, en este bucle compruebo constantemente si el canvas tiene el foco y, si no lo tiene, se lo doy
			if (ventana.getFocusOwner() != null && !ventana.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			// Redibujo la escena
			canvas.pintaEscena();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos actue
			for (Actor a : actores) {
				a.actua();
			}
			
			detectaColisiones();
			actualizaActores();
			actoresParaEliminar.clear();
			
			// Calculo los millis que debemos parar el proceso, generando 60 FPS.
			long millisDespuesDeProcesarEscena = new Date().getTime();
			int millisDeProcesamientoDeEscena = (int) (millisDespuesDeProcesarEscena - millisAntesDeProcesarEscena);
			int millisPausa = millisPorCadaFrame - millisDeProcesamientoDeEscena;
			millisPausa = (millisPausa < 0)? 0 : millisPausa;
			// "Duermo" el proceso principal durante los milllis calculados.
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
		
	}
	
	public static Arkanoid getInstance () {
		if (instance == null) { 
			instance = new Arkanoid();
		}
		return instance;
	}
	
		
	/**
	 * 
	 * @return
	 */
	private static List<Actor> creaActores () {
		actores = new ArrayList<Actor>();
		
		n = new Nave(350, 600, 150, 15, Nave.IMAGEN_PLAYER, 20);
		
		actores.add(n);
		
		Color[] color = new Color[6];
		color[0]= Color.RED;
		color[1]= Color.YELLOW;
		color[2]= Color.PINK;
		color[3]= Color.CYAN;
		color[4]= Color.GREEN;
		color[5]= Color.yellow;
		
		
		Ladrillo l = null;
		
		int y = 5;
		int x = 5;
		int contador=6;
		for (int i = 0; i < contador ; i++) {
			for (int j = 0; j < 12; j++) {
				l = new Ladrillo(x, y, 60, 30, null, color[i]);
				actores.add(l);
				x+=65;
			}
			y += 35;
			x =5;
			
		}
		
		// Creo la pelota del juego
		
			int xAleatoria = numAleatorio(10, 500);
			int yAleatoria = numAleatorio(10, 200);
			
			p = new Pelota(xAleatoria, yAleatoria, 20, 20, null, 10, 10);
			actores.add(p);
		
		
		// Devuelvo la lista con todos los actores del juego
		return actores;
	}
	
	/**
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	private static int numAleatorio (int minimo, int maximo) {
		return (int) Math.round(Math.random() * (maximo - minimo) + minimo);
	}

	/**
	 * MÃ©todo llamado para incorporar nuevos actores
	 * @param a
	 */
	public void incorporaNuevoActor (Actor a) {
		this.actoresParaIncorporar.add(a);
	}

	
	/**
	 * Eliminar actores del juego
	 * @param a
	 */
	public void eliminaActor (Actor a) {
		Arkanoid.actoresParaEliminar.add(a);
	}
		
	
	/**
	 * Incorpora los actores nuevos al juego y elimina los que corresponden
	 */
	private static void actualizaActores () {
		// Incorporo los nuevos actores
		for (Actor a : actoresParaIncorporar) {
			actores.add(a);
		}
		actoresParaIncorporar.clear(); // Limpio la lista de actores a incorporar, ya estÃ¡n incorporados
		
		// Elimino los actores que se deben eliminar
		for (Actor a : actoresParaEliminar) {
			actores.remove(a);
		}
		actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
	}
	
	
	/**
	 * Detecta colisiones entre actores e informa a los dos
	 */
	private static void detectaColisiones() {
		// Crea un rectángulo con los datos de la pelota
		Rectangle rect1 = new Rectangle(p.getX(), p.getY(), p.getAncho(), p.getAlto());
	
		// pasa por todos los actores de la lista
		for (Actor actor1 : actores) {
			
			// evita la propia pelota
			if(!p.equals(actor1)) {
			
				// crea un rectángulo del actor
				Rectangle rect2 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getAlto());
				
				// Si intersecta envía notificación a los dos objetos
				if(rect1.intersects(rect2)) {
					p.colisionaCon(actor1);
					actor1.colisionaCon(p);
				}
			}
		}
		
		
		
	}
	
	/**
	 * @return the canvas
	 */
	public MiCanvas getCanvas() {
		return canvas;
	}
}
