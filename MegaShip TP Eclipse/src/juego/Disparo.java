package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	public double x;
	public double y;
	private Image imagenBala;

	public Disparo(double x, double y) {
		this.x = x;
		this.y = y;
		this.imagenBala = Herramientas.cargarImagen("disparoMegaShip.png");
	}

	public void moverArriba() {
		this.y -= 4;
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imagenBala, x, y, 0, 0.02);
	}

	public static void agregarDisparo(Disparo[] cargador, Disparo bala) {
		for (int i = 0; i < cargador.length; i++) {
			if (cargador[i] == null) {
				cargador[i] = bala;
			}
		}
	}

}