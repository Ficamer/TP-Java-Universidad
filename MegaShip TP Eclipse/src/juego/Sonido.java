package juego;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sonido {
	void reproducirMusica(String directorio) {
		try {
			File ubicacion = new File(directorio); // Objeto que contiene la ubicacion del archivo de sonido

			if (ubicacion.exists()) // Comprobamos que el archivo exista
			{
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(ubicacion); // Objeto que trae el archivo
																							// de sonido a eclipse
				Clip clip = AudioSystem.getClip(); // Objeto que contiene el archivo de sonido
				clip.open(audioStream); // Abre la musica
				clip.start(); // Reproduce la musica
				clip.loop(Clip.LOOP_CONTINUOUSLY); // Hace que la musica vuevla a empezar
			} else {
				System.out.println("No se pudo encontrar el archivo");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}