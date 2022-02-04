import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;




public class Arkanoid {

	private static final int FPS = 60;
	private static MiCanvas canvas = null;
	private static Arkanoid instance = null;
	private static Nave n = null;

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Arkanoid");
		ventana.setBounds(0, 0, 800, 675);

		
		
		//Plantilla donde se colocan los objetos
		ventana.getContentPane().setLayout(new BorderLayout());
		
		// Creo una lista de actores que intervendrá en el juego.
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
		
		
		
		// Comienzo un bucle, que consistir� en el juego completo.
		int millisPorCadaFrame = 1000 / FPS;
		do {
			// Redibujo la escena tantas veces por segundo como indique la variable FPS
			// Tomo los millis actuales
			long millisAntesDeProcesarEscena = new Date().getTime();
			
			// Redibujo la escena
			canvas.pintaEscena();
			
			// Recorro todos los actores, consiguiendo que cada uno de ellos actúe
			for (Actor a : actores) {
				a.actua();
			}
			
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
		if (instance == null) { // Si no está inicializada, se inicializa
			instance = new Arkanoid();
		}
		return instance;
	}
	

		
	/**
	 * 
	 * @return
	 */
	private static List<Actor> creaActores () {
		List<Actor> actores = new ArrayList<Actor>();
		
		n = new Nave(350, 600, 150, 15, null, 20);
		
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
				l = new Ladrillo(y, x, 30, 60, null, color[i]);
				actores.add(l);
				x+=65;
			}
			y += 35;
			x =5;
			
		}
		
		
		// Creo los Monstruos del juego
		
			int xAleatoria = numAleatorio(10, 500);
			int yAleatoria = numAleatorio(10, 200);
			
			Pelota p = new Pelota(xAleatoria, yAleatoria, 20, 20, null, 10, 10);
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
	 * @return the canvas
	 */
	public MiCanvas getCanvas() {
		return canvas;
	}
}
