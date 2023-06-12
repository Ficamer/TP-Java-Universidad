package juego;

import entorno.*;
import java.awt.Image;

public class MetodosDelJuego {

	//GENERAR PANTALLA PERDISTE
	public static void mostrarPantallaPerdiste(Entorno entorno, boolean perdiste, Image gameOver) {
		if (perdiste) {
			entorno.dibujarImagen(gameOver, entorno.ancho() / 2, entorno.alto() / 2.5, 0);
		}
	}
	//GENERAR PANTALLA GANASTE
	public static void mostrarPantallaGanaste(Entorno entorno, int puntos, Image imgGanaste, Image textoGanaste) {
		entorno.dibujarImagen(imgGanaste, entorno.ancho() / 2, entorno.alto() / 2.5, 0);
		entorno.dibujarImagen(textoGanaste, entorno.ancho() / 2, entorno.alto() / 1.5, 0);
	}

}
