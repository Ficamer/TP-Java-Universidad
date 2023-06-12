package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class DisparoDestructor {
	public double x;
	public double y;
	private Image imgDispDestructor;

	public DisparoDestructor(double x, double y) {
		this.x = x;
		this.y = y;
		this.imgDispDestructor = Herramientas.cargarImagen("disparoDestructor.png");
	}
	public static void moverDisparosDestructor(DisparoDestructor[] disparoDestructor, Entorno entorno) {
		for (int balaDestructor = 0; balaDestructor < disparoDestructor.length; balaDestructor++) {
			if (disparoDestructor[balaDestructor] != null) {
				disparoDestructor[balaDestructor].dibujarDisparo(entorno);
				if (disparoDestructor[balaDestructor].y > entorno.alto()) {
					disparoDestructor[balaDestructor] = null;
				} else {
					disparoDestructor[balaDestructor].movDisparoDestructor();
				}
			}
		}
	}
	public void dibujarDisparo(Entorno e) {
		double angulo = 0;
		double escala = 0.05;
		e.dibujarImagen(imgDispDestructor, x, y, angulo, escala);
	}

	public void movDisparoDestructor() {
		this.y += 3;
	}

	public static void agregarDisparo(DisparoDestructor[] arregloDisparosDestructor, DisparoDestructor p) {
		for (int disparo = 0; disparo < arregloDisparosDestructor.length; disparo++) {
			if (arregloDisparosDestructor[disparo] == null) {
				arregloDisparosDestructor[disparo] = p;
				break;
			}
		}
	}
}
