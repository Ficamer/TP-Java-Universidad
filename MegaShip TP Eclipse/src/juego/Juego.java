package juego;

//LIBRERIAS
import entorno.Entorno;
import java.awt.Image;
import entorno.InterfaceJuego;
import entorno.Herramientas;

public class Juego extends InterfaceJuego {
	// DECLARACION DE VARIABLES
	private Entorno entorno;
	private Image img;
	private Image gameOver = Herramientas.cargarImagen("gameover.gif");
	private Image imgGanaste = Herramientas.cargarImagen("imagenganadora.gif");
	private Image textGanaste = Herramientas.cargarImagen("ganasteTexto.gif");
	private MegaShip megaShip;
	private Meteoritos[] meteorito;
	private Disparo[] disparo;
	private DestructorEstelar[] destructorEstelar;
	private DisparoDestructor[] disparoDestructor;
	private int segundos;
	private int segundosDestructores;
	private int segundosMeteoritos;
	private boolean perdiste = false;
	private int puntos = 0;
	private boolean detectorDestruidosDestructores = false;
	private int detectorDestruidosMeteoritos = 0;
	private int posicionDestruidoDestructor;
	private int posicionDestruidoMeteorito;

	public Juego() {
//Cargamos el entorno, Instanciamos objetos y les asignamos posiciones
		this.entorno = new Entorno(this, "AstroMegaShip - Grupo N 4 - Lopez - Quintana - Gonzalez - V0.01", 800, 600);
		this.img = Herramientas.cargarImagen("FondoEspacial.gif");
		this.megaShip = new MegaShip(entorno.ancho() / 2, entorno.alto() - 50);
		this.disparo = new Disparo[1];
		this.meteorito = new Meteoritos[5];
		for (int unMeteorito = 0; unMeteorito < 5; unMeteorito++) {
			this.meteorito[unMeteorito] = new Meteoritos(30 + (entorno.ancho() / 5) * unMeteorito,
					-100 - (entorno.alto()) / 5);
		}
		this.destructorEstelar = new DestructorEstelar[5];
		for (int unDestructor = 0; unDestructor < 5; unDestructor++) {
			this.destructorEstelar[unDestructor] = new DestructorEstelar(60 + (entorno.ancho() / 5) * unDestructor,
					-100 - (entorno.alto() / 5) * (Math.random() * 4 + 1));
		}
		this.disparoDestructor = new DisparoDestructor[100];
//INICIAMOS EL ENTORNO		
		this.entorno.iniciar();
	}

	public void tick() {
		if (megaShip != null) {
			
			//CONTADORES DE TIEMPO
			segundos++;
			segundosDestructores++;
			segundosMeteoritos++;
			
			// DIBUJAR PANTALLA DEL JUEGO
			entorno.dibujarImagen(img, entorno.ancho() / 2, entorno.alto() / 2, 2.2, 1.5);
			
			// DETECCION DE TECLAS y MOVIMIENTOS DE LA NAVE
			if (entorno.estaPresionada(entorno.TECLA_DERECHA))
				megaShip.moverDerecha(entorno);
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA))
				megaShip.moverIzquierda(entorno);
			if (entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
				Disparo.agregarDisparo(disparo, megaShip.disparar());
			}

			//DIBUJAR MEGASHIP
			if (megaShip != null) {
				megaShip.dibujar(entorno);
			}
			
			//DIBUJAR METEORITOS
			for (int unMeteorito = 0; unMeteorito < meteorito.length; unMeteorito++) {
				if (meteorito[unMeteorito] != null) {
					meteorito[unMeteorito].dibujar(entorno);
					meteorito[unMeteorito].movimientoMeteorito(entorno);
					meteorito[unMeteorito].rotacionMeteorito();
				}
			}
			
			//DIBUJAR DESTRUCTORES ESTELARES
			for (int unDestructor = 0; unDestructor < destructorEstelar.length; unDestructor++) {
				if (destructorEstelar[unDestructor] != null) {
					destructorEstelar[unDestructor].dibujarDestructor(entorno);
					destructorEstelar[unDestructor].movDestructor(entorno);
					int posicion = (unDestructor) * 40;
					destructorEstelar[unDestructor].dibujarDestructorRestantes(entorno, posicion);
				}
			}
			
			//DIBUJAR DISPAROS MEGASHIP
			for (int bala = 0; bala < disparo.length; bala++) {
				if (disparo[bala] != null) {
					disparo[bala].dibujar(entorno);
					disparo[bala].moverArriba();
				}
			}
			
			
			//MOVIMIENTO DE DISPARO MEGASHIP
			for (int bala = 0; bala < 1; bala++) {
				if (disparo[bala] != null) {
					if (disparo[bala].y < 0) {
						disparo[bala] = null;
					} else {
						disparo[bala].moverArriba(); // Actualizar posición del disparo
					}
				}
			}
			

			//MOVIMIENTO DISPARO DESTRUCTOR
			DisparoDestructor.moverDisparosDestructor(disparoDestructor, entorno);

			// AGREGAR DISPAROS DE DESTRUCTORES
			for (int i = 0;i<destructorEstelar.length;i++) {
				if(destructorEstelar[i]!=null) {
					if (segundos == 100 || segundos == 200 ) {
						destructorEstelar[i].generarDisparosAleatorios(destructorEstelar,disparoDestructor);
					}
					if (segundos == 300) {
						destructorEstelar[i].generarDisparosAleatorios(destructorEstelar,disparoDestructor);
						segundos = 0;
					}
				}
			}
			
			// REGENERACION DE METEORITOS Y DESTRUCTORES
			if (detectorDestruidosMeteoritos > 0 && segundosMeteoritos >= 500) {
				destructorEstelar[posicionDestruidoMeteorito] = new DestructorEstelar(
						60 + (entorno.ancho() / 5) * posicionDestruidoMeteorito,
						-100 - (entorno.alto() / 5) * (Math.random() * 4 + 1));
				detectorDestruidosMeteoritos--;
				segundosMeteoritos = 0;
			}
			if (detectorDestruidosDestructores && segundosDestructores >= 1000) {
				puntos--;
				destructorEstelar[posicionDestruidoDestructor] = new DestructorEstelar(
						60 + (entorno.ancho() / 5) * posicionDestruidoDestructor,
						-100 - (entorno.alto() / 5) * (Math.random() * 4 + 1));
				detectorDestruidosDestructores = false;
				segundosDestructores = 0;
			}
			
			// DESTRUCCION DE METEORITOS POR COLISIONES
			Meteoritos.colisionConMeteorito(meteorito, detectorDestruidosMeteoritos);
			Meteoritos.colisionConDestructor(meteorito, destructorEstelar, detectorDestruidosMeteoritos);

			//AUMENTO DEL CONTADOR DE DESTRUIDOS SEGUN LA COLISION

			for (int bala = 0; bala < 1; bala++) {
				if (disparo[bala] != null) {
					for (int unElemento = 0; unElemento < 5; unElemento++) {
						if (meteorito[unElemento] != null && meteorito[unElemento].colisionaDisparo(disparo[bala])) {
							Meteoritos.destruirMeteorito(meteorito, unElemento);
							disparo[bala] = null;
							detectorDestruidosMeteoritos++;
							posicionDestruidoMeteorito = unElemento;
							break;
						}
						if (destructorEstelar[unElemento] != null
								&& destructorEstelar[unElemento].colisionaDisparo(disparo[bala])) {
							DestructorEstelar.destruirDestructor(destructorEstelar, unElemento);
							disparo[bala] = null;
							puntos++;
							detectorDestruidosDestructores = true;
							posicionDestruidoDestructor = unElemento;
							break;
						}
					}
				}
			}
			
			// MODIFIACION DEL BOOLEANO PERDISTE SEGUN LA COLISION DEL MEGASHIP
			if (megaShip != null) {
				for (int i = 0; i < 5; i++) {
					if (meteorito[i] != null && megaShip.colisionaMeteorito(meteorito[i])) {
						perdiste = true;
						megaShip = null;
						break;
					}
					if (destructorEstelar[i] != null && megaShip.colisionaDestructor(destructorEstelar[i])) {
						perdiste = true;
						megaShip = null;
						break;
					}
					if (disparoDestructor[i] != null && megaShip.colisionaDispDestructor(disparoDestructor[i])) {
						perdiste = true;
						megaShip = null;
						break;
					}
				}
			}
		}
		
		
		//PANTALLA PERDISTE
		if (perdiste) {
			megaShip = null;
			MetodosDelJuego.mostrarPantallaPerdiste(entorno, perdiste, gameOver);
		}
		
		//PANTALLA GANASTE
		if (puntos == 5) {
			megaShip = null;
			MetodosDelJuego.mostrarPantallaGanaste(entorno, puntos, imgGanaste, textGanaste);
		}
	}


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		// Agregamos Musica
		Sonido sonido = new Sonido();
		sonido.reproducirMusica("src/musica.wav");
	}
}