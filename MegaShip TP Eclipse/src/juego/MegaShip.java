package juego;

import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class MegaShip {
	private double x;
	private double y;
	private Image imagen;

	public MegaShip(double x, double y) {
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("MegaShip.gif");
	}

	public void dibujar(Entorno e) {
		e.dibujarImagen(imagen, x, y, 0, 0.2);
	}

	public void moverDerecha(Entorno e) {
		if (x + 20 < e.ancho()) { // LA NAVE MIDE 40
			x += 2.5;
		}
	}

	public void moverIzquierda(Entorno e) {
		if (x - 20 > 0) {
			x -= 2.5;
		}
	}

	public boolean colisionaMeteorito(Meteoritos meteo) {
		int anchoMegaShip = 20;
		int largoMegaship = 40;
		boolean a = y + largoMegaship >= -largoMegaship + meteo.y;
		boolean b = y - largoMegaship <= largoMegaship + meteo.y;
		boolean c = x + anchoMegaShip >= meteo.x - anchoMegaShip;
		boolean d = x - anchoMegaShip <= meteo.x + anchoMegaShip;
		return a && b && c && d;
	}

	public boolean colisionaDestructor(DestructorEstelar destruc) {
		int anchoMegaShip = 20;
		int largoMegaship = 40;
		boolean a = y + largoMegaship >= -largoMegaship + destruc.y;
		boolean b = y - largoMegaship <= largoMegaship + destruc.y;
		boolean c = x + anchoMegaShip >= destruc.x - anchoMegaShip;
		boolean d = x - anchoMegaShip <= destruc.x + anchoMegaShip;
		return a && b && c && d;
	}

	public boolean colisionaDispDestructor(DisparoDestructor dispDestr) {
		int anchoMegaShip = 13;
		int largoMegaship = 38;
		int anchoYlargoDisparo = 5;
		boolean a = y + largoMegaship >= -anchoYlargoDisparo + dispDestr.y;
		boolean b = y - largoMegaship <= anchoYlargoDisparo + dispDestr.y;
		boolean c = x + anchoMegaShip >= dispDestr.x - anchoYlargoDisparo;
		boolean d = x - anchoMegaShip <= dispDestr.x + anchoYlargoDisparo;
		return a && b && c && d;
	}
	

	public Disparo disparar() {
		return new Disparo(x, y - 35);
	}

}
