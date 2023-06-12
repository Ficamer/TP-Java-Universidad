package juego;

import entorno.Entorno;
import java.awt.Image;
import entorno.Herramientas;

public class DestructorEstelar {
	public double ancho = 35;
	public double largo = 35;
	public double x;
	public double y;
	private Image imaDestructor;
	private int alternador;
	
	public DestructorEstelar(double x, double y) {
		this.x = x;
		this.y = y;
		this.imaDestructor = Herramientas.cargarImagen("Destructor.png");
	}
	public void movDestructor(Entorno e) {
		if (y < e.alto() + 100) {
			if (x - ancho > 0 && x + ancho < e.ancho()) {
				if (alternador < 100) {
					this.x++;
					alternador++;
				}
				if (alternador >= 99 && alternador < 200) {
					this.x--;
					alternador++;
				}
				if (alternador == 200) {
					alternador = 0;
				}
			}
			if (x - ancho == 0) {
				this.x++;
			}
			if (x + ancho == e.ancho()) {
				this.x--;
			}
			this.y++;
		} else {
			this.y = -largo;
		}
	}

	public void dibujarDestructor(Entorno e) {
		double angulo = 0;
		double escala = .5;
		e.dibujarImagen(imaDestructor, x, y, angulo, escala);
	}

	public void dibujarDestructorRestantes(Entorno e, int posicion) {
		double angulo = 0;
		double escala = .25;
		double xAncho = e.ancho() - 20 - posicion;
		double yAlto = e.alto() - 20;
		e.dibujarImagen(imaDestructor, xAncho, yAlto, angulo, escala);
	}

	public DisparoDestructor disparar() {
		return new DisparoDestructor(x, y + 20);
	}

	public boolean colisionaDisparo(Disparo disp) {
		boolean a = y + largo >= -5 + disp.y;
		boolean b = y - largo <= 5 + disp.y;
		boolean c = x + ancho >= disp.x - 5;
		boolean d = x - ancho <= disp.x + 5;
		return a && b && c && d;
	}
	
	public void generarDisparosAleatorios(DestructorEstelar[] destructorEstelar,
			DisparoDestructor[] disparoDestructor) {
				int random = (int) (Math.random() * 5);
				if (destructorEstelar[random] != null && destructorEstelar[random].y > 0) { //Si ademas le pido que la y sea mayor a 10
					DisparoDestructor.agregarDisparo(disparoDestructor, destructorEstelar[random].disparar());	
				}
	}

	public static void destruirDestructor(DestructorEstelar[] destructoresEstelares, int destructorEstelar) {
		destructoresEstelares[destructorEstelar] = null;
	}
}
