package juego;
import entorno.Entorno;

public class Bat {

	private Entorno entorno;
	private Estado estado;
	private Nivel nivel;
	private Gondolf gondolf;
	private Reloj reloj;
	private Bosque bosque;
	int vida = 20;
	int posX;
	int posY;
	boolean vivo = false;
	int haciaGondolf; //1 front der, 2 front izq, 3 back izq, 4 back der
	int distGondolf;
	boolean quemar = false;
	boolean colGondolf = false;
	int ticksFuera;
	int ticks = 0;
	
	public Bat(Entorno entorno, Estado estado, Nivel nivel, Gondolf gondolf, Reloj reloj, Bosque bosque) {
		this.entorno = entorno;
		this.estado = estado;
		this.nivel = nivel;
		this.gondolf = gondolf;
		this.reloj = reloj;
		this.bosque = bosque;
	}
	
	
	
	public void contarTicks(boolean iniciar) {
		if(iniciar) {
			ticksFuera = entorno.numeroDeTick();
		}
		else{
			ticks = entorno.numeroDeTick() - ticksFuera;
		}
		
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
	public void interaccion() {
	    Pino pinoCerca = pinoMasCercano();
	    int dis = (int) Math.sqrt(Math.pow((posX - pinoCerca.posX), 2) + Math.pow((posY - pinoCerca.posY), 2));
	    
	    if (pinoCerca.estadoPino != 1 && pinoCerca.estadoPino != 5 && dis < 30) {
	        if (!quemar) {           // sólo la primera vez
	            quemar = true;
	            ticks = 0;           // reiniciamos el contador de animación
	            contarTicks(true);   // marcamos inicio
	        }
	    }

	    
	    if (distGondolf < 20 && !colGondolf) {
	        colGondolf = true;
	        gondolf.vida = gondolf.vida - 1;
	        quemar = true;
	        // NO usar return aquí
	    }
	    
	    if (!quemar && !colGondolf && gondolf.vida > 0) { // Solo moverse si no está quemado ni ha colisionado y si gondolf vive
	        if (gondolf.posX > posX)
	            posX += 1;
	        if (gondolf.posX < posX)
	            posX -= 1;
	        if (gondolf.posY > posY)
	            posY += 1;
	        if (gondolf.posY < posY)
	            posY -= 1;
	    }
	}
	
	public void relativo_a_Gondolf() {
	    int ejeX = gondolf.posX - posX;
	    int ejeY = gondolf.posY - posY; // Cambié posX por posY aquí
	    if(ejeX > 0) {
	        if(ejeY > 0)
	            haciaGondolf = 4;
	        else
	            haciaGondolf = 1;
	    }
	    else {
	        if(ejeY > 0)
	            haciaGondolf = 3;
	        else
	            haciaGondolf = 2;
	    }
	    
	    distGondolf = (int) Math.sqrt(Math.pow(ejeX, 2) + Math.pow(ejeY, 2));
	}
}
