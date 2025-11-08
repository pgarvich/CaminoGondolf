package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Gondolf {
	private Entorno entorno;
	private Estado estado;
	private Reloj reloj;
	private Bosque bosque;
	int vidaMax = 10;
	int manaftaMax = 10;
	int vida = 10;
	int manafta = 10;
	int posX = 300;
	int posY = 303;
	int cara = 1;
	int velocidad = 1;
	boolean izqDer = true;
	
	public Gondolf(Entorno entorno, Estado estado, Reloj reloj, Bosque bosque) {
		this.entorno = entorno;
		this.estado = estado;
		this.reloj = reloj;
		this.bosque = bosque;
		}

	public boolean relativo_a_Pino() {		//¿Atrás o adelante del pino?
		Pino pino_a_Comparar = pinoMasCercano();
		int piesGondolf = posY + 40;
		int basePino = pino_a_Comparar.posY + 60;
		
		if(piesGondolf > basePino)
			return true;
		else
			return false;
	}
	public Pino pinoMasCercano() {
		Pino[] pino = bosque.getPinos();
		Pino masCerca = pino[0];
		for(int i = 0; i <= pino.length - 1; i++) {
			double dis1 = Math.sqrt(Math.pow((posX- masCerca.posX), 2)+ Math.pow((posY - masCerca.posY), 2));
			double dis2 = Math.sqrt(Math.pow((posX- pino[i].posX), 2)+ Math.pow((posY - pino[i].posY), 2));
			if(dis2 < dis1) {
				masCerca = pino[i];
			}
		}
		return masCerca;
	}
	public void moverGondolf() {
		
		Pino masCercano = pinoMasCercano();
		
		int pinoDerecha = masCercano.posX+30;						//colision pinos
		int pinoIzquierda = masCercano.posX-30;
		int pinoArriba = masCercano.posY + 10;
		int pinoAbajo = masCercano.posY + 40;
		

		//posicion siguiente de gondolf
		int sigArriba = posY - velocidad;
		int sigAbajo = posY + velocidad;
		int sigIzquierda = posX - velocidad;
		int sigDerecha = posX + velocidad;
		
	    //boolean solapaX = (sigIzquierda <= pinoDerecha && sigDerecha >= pinoIzquierda);
	    //boolean solapaY = (sigArriba <= pinoAbajo && sigAbajo >= pinoArriba);

	    boolean colisionIzquierda = (sigIzquierda <= pinoDerecha) && (posX >= pinoIzquierda) && (posY >= pinoArriba) && (posY <= pinoAbajo);
	    boolean colisionDerecha = (sigDerecha >= pinoIzquierda) && (posX <= pinoDerecha) && (posY >= pinoArriba) && (posY <= pinoAbajo);
	    boolean colisionArriba = (sigArriba <= pinoAbajo) && (posY >= pinoArriba) && (posX >= pinoIzquierda) && (posX <= pinoDerecha);
	    boolean colisionAbajo = (sigAbajo >= pinoArriba) && (posY <= pinoAbajo) && (posX >= pinoIzquierda) && (posX <= pinoDerecha);
		
	    // Movimiento horizontal (A/D)
	    if (20 < posX && entorno.estaPresionada('a') && !colisionIzquierda) {
	        posX = sigIzquierda;
	    }
	    if (posX < 580 && entorno.estaPresionada('d') && !colisionDerecha) {
	        posX = sigDerecha;
	    }
	    
	    // Movimiento vertical (W/S)
	    if (33 < posY && entorno.estaPresionada('w') && !colisionArriba) {
	        posY = sigArriba;
	    }
	    if (posY < 573 && entorno.estaPresionada('s') && !colisionAbajo) {
	        posY = sigAbajo;
	    }
	}
	

	public void dibujarGondolf() {
		if(estado.esJuego()) {
			cualCara();
			moverGondolf();
			if(cara == 1 && izqDer) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
				entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf23_1.png"), posX, posY, 0);		
				}
				else {
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf23_1.png"), posX, posY, 0);	
					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf23_2.png"), posX, posY, 0);	
					}
				}
					
			}
			if(cara == 1 && !izqDer) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
					entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf187_1.png"), posX, posY, 0);
				}
				else{
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf187_1.png"), posX, posY, 0);
						}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf187_2.png"), posX, posY, 0);
					}
				}
			}
			if(cara == 8 || cara == 7) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
					entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf187_1.png"), posX, posY, 0);}
				else {
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf187_1.png"), posX, posY, 0);
					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf187_2.png"), posX, posY, 0);
					}
				}
				izqDer = false;
			}
			if(cara == 2 || cara == 3) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
					entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf23_1.png"), posX, posY, 0);
				}
				else {
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf23_1.png"), posX, posY, 0);
					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf23_2.png"), posX, posY, 0);
					}
				}
				izqDer = true;
			}
			if(cara == 4){
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {	
					entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf45_1.png"), posX, posY, 0);}
				else {
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf45_1.png"), posX, posY, 0);
					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf45_2.png"), posX, posY, 0);
					}
				}
				izqDer = true;
			}
			if(cara == 5 && izqDer) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
					entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf45_1.png"), posX, posY, 0);
			}
				else {
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf45_1.png"), posX, posY, 0);
					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf45_2.png"), posX, posY, 0);
					}
				}
			}
			if(cara== 5 && !izqDer) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
					entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf6_1.png"), posX, posY, 0);
			}
				else{
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf6_1.png"), posX, posY, 0);
					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf6_2.png"), posX, posY, 0);
					}
				}}
			if(cara == 6) {
				if(!entorno.estaPresionada('w') && !entorno.estaPresionada('s') && !entorno.estaPresionada('a') && !entorno.estaPresionada('d')) {
				entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf6_1.png"), posX, posY, 0);
				}
				else {
					if(reloj.ciclos(300, 600)) {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf6_1.png"), posX, posY, 0);

					}
					else {
						entorno.dibujarImagen(Herramientas.cargarImagen("gondolf/Gondolf6_2.png"), posX, posY, 0);

					}
				}
		}}
	}
	
	public void cualCara() {
		boolean arriba = entorno.estaPresionada('w');
		boolean abajo = entorno.estaPresionada('s');
		boolean izquierda = entorno.estaPresionada('a');
		boolean derecha = entorno.estaPresionada('d');
		
		int clickX = entorno.mouseX();
		int clickY = entorno.mouseY();
		
		if(arriba && !abajo && !izquierda && !derecha) {
			cara = 5; //arriba
		}		
		if(!arriba && abajo && !izquierda && !derecha) {
			cara = 1; //abajo
		}		
		if(!arriba && !abajo && izquierda && !derecha) {
			cara = 3; //izquierda
		}		
		if(!arriba && !abajo && !izquierda && derecha) {
			cara = 7; //derecha
		}		
		if(arriba && !abajo && izquierda && !derecha) {
			cara = 4; //arriba izquierda
		}
		if(arriba && !abajo && !izquierda && derecha) {
			cara = 6; //arriba derecha
		}
		if(!arriba && abajo && izquierda && !derecha) {
			cara = 2;  //abajo izquierda
		}
		if(!arriba && abajo && !izquierda && derecha) {
			cara = 8; //abajo derecha
		}		
		if((entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) || entorno.estaPresionado(entorno.BOTON_IZQUIERDO)) && 0 < clickX && clickX < 600 && 0 < clickY && clickY < 600) {
			double dx = clickX - posX;
			double dy = clickY - posY;

			double angulo = Math.atan2(dy, dx);
			double grados = Math.toDegrees(angulo);
			
			if (grados < 0) {
				grados += 360;
			}

			if (grados >= 337.5 || grados < 22.5) {
				cara = 7; // derecha
			} else if (grados >= 22.5 && grados < 67.5) {
				cara = 8; // abajo derecha
			} else if (grados >= 67.5 && grados < 112.5) {
				cara = 1; // abajo
			} else if (grados >= 112.5 && grados < 157.5) {
				cara = 2; // abajo izquierda
			} else if (grados >= 157.5 && grados < 202.5) {
				cara = 3; // izquierda
			} else if (grados >= 202.5 && grados < 247.5) {
				cara = 4; // arriba izquierda
			} else if (grados >= 247.5 && grados < 292.5) {
				cara = 5; // arriba
			} else if (grados >= 292.5 && grados < 337.5) {
				cara = 6; // arriba derecha
			}
		}

	}
	

}
