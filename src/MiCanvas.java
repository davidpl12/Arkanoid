import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;



public class MiCanvas extends Canvas {
	
	List<Actor> actores = null;
	
	
	/**
	 * @param actores
	 */
	public MiCanvas(List<Actor> actores) {
		super();
		this.actores = actores;
	}


	public void paint(Graphics g) {
		// Pinto el fondo
		this.setBackground(Color.BLACK);
		
		// Pinto cada uno de los actores
		for (Actor a : this.actores) {
			a.paint(g);
	}

	}
}
