package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Meteoritos {
	public double x;
	public double y;
	private double anguloRotacion;
	private double angulo;
	private Image imaMeteorito;

	public Meteoritos(double x, double y) {
		double aleatorio = (int) (Math.random() * 10 + 1);
		double modAngulo;
		if (aleatorio <= 5) {
			modAngulo = Math.PI / 3;
		} else {
			modAngulo = (Math.PI * 3) / 4;
		}
		this.x = x;
		this.y = y * Math.random();
		this.angulo = modAngulo;
		this.anguloRotacion = 0;
		this.imaMeteorito = Herramientas.cargarImagen("Meteorito.png");
	}

	public void dibujar(Entorno e) {
		double escala = 0.5;
		e.dibujarImagen(imaMeteorito, x, y, anguloRotacion, escala);
	}

	public void movimientoMeteorito(Entorno e) {
		if (y < e.alto() + 100) {
			this.x = x + Math.cos(angulo);
			this.y = y + Math.sin(angulo);
		} else {
			this.y = -35;
			this.x = 30 + (e.ancho() / 5) * (1 + Math.random() * 4);
			this.y = y + Math.sin(angulo);
		}
	}

	public void rotacionMeteorito() {
		anguloRotacion += 0.015;
	}

	public static void colisionConMeteorito(Meteoritos[] meteorito, int detectorDestruidosMeteoritos) {
		double ancho = 55;
		double largo = 55;
		for (int meteorito1 = 0; meteorito1 < meteorito.length; meteorito1++) {
			if (meteorito != null) {
				for (int meteorito2 = 0; meteorito2 < meteorito.length; meteorito2++) {
					if (meteorito1 != meteorito2 && // Evitar comparar un objeto consigo mismo
							meteorito[meteorito1] != null && meteorito[meteorito2] != null
							&& meteorito[meteorito1].x + ancho >= meteorito[meteorito2].x
							&& meteorito[meteorito1].x <= meteorito[meteorito2].x + ancho
							&& meteorito[meteorito1].y + largo >= meteorito[meteorito2].y
							&& meteorito[meteorito1].y <= meteorito[meteorito2].y + largo) {
						Meteoritos.destruirMeteorito(meteorito, meteorito1);
						Meteoritos.destruirMeteorito(meteorito, meteorito2);
						detectorDestruidosMeteoritos++;
					}
				}
			}
		}
	}

	public static void colisionConDestructor(Meteoritos[] meteorito, DestructorEstelar[] destructor,
			int detectorDestruidosMeteoritos) {
		double anchoMeteorito = 55;
		double largoMeteorito = 55;
		double anchoDestructor = 35;
		double largoDestructor = 35;

		for (int meteorito1 = 0; meteorito1 < meteorito.length; meteorito1++) {
			if (meteorito[meteorito1] != null) {
				for (int destructorEstelar = 0; destructorEstelar < destructor.length; destructorEstelar++) {
					if (destructor[destructorEstelar] != null
							&& meteorito[meteorito1].x + anchoMeteorito >= destructor[destructorEstelar].x
							&& meteorito[meteorito1].x <= destructor[destructorEstelar].x + anchoDestructor
							&& meteorito[meteorito1].y + largoMeteorito >= destructor[destructorEstelar].y
							&& meteorito[meteorito1].y <= destructor[destructorEstelar].y + largoDestructor) {
						Meteoritos.destruirMeteorito(meteorito, meteorito1);
						detectorDestruidosMeteoritos++;
						break;
					}
				}
			}

		}
	}

	public boolean colisionaDisparo(Disparo disp) {
		int anchoLargoMeteorito = 35;
		int anchoLargoBala = 5;
		boolean a = y + anchoLargoMeteorito >= -anchoLargoBala + disp.y;
		boolean b = y - anchoLargoMeteorito <= anchoLargoBala + disp.y;
		boolean c = x + anchoLargoMeteorito >= disp.x - anchoLargoBala;
		boolean d = x - anchoLargoMeteorito <= disp.x + anchoLargoBala;
		return a && b && c && d;
	}

	public static void destruirMeteorito(Meteoritos[] meteoritos, int p) {
		meteoritos[p] = null;
	}
}