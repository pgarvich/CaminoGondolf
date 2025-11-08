package juego;

import entorno.Entorno;
import entorno.Herramientas;

public class Bosque {

	private Entorno entorno;
	private Estado estado;
	private Reloj reloj;
	private Pino pino1, pino2, pino3, pino4, pino5, pino6, pino7;
	
	public Bosque(Entorno entorno, Estado estado, Reloj reloj) {
		this.entorno = entorno;
		this.estado = estado;
		this.reloj = reloj;
				
		this.pino1 = new Pino(this.entorno, this.estado, 480, 100);
		this.pino2 = new Pino(this.entorno, this.estado, 170, 150);
		this.pino3 = new Pino(this.entorno, this.estado, 240, 520);
		this.pino4 = new Pino(this.entorno, this.estado, 530, 400);
		this.pino5 = new Pino(this.entorno, this.estado, 370, 240);
		this.pino6 = new Pino(this.entorno, this.estado, 60, 350);
		this.pino7 = new Pino(this.entorno, this.estado, 400, 480);
		
		this.pino7.setEstado(2);
	}
	
	public Pino[] getPinos() {
		return new Pino[]{ pino1, pino2, pino3, pino4, pino5, pino6, pino7};
	}
	public void dibujarPiso() {
		if(estado.esJuego()) {
			if(reloj.ciclos(200, 400))
				entorno.dibujarImagen(Herramientas.cargarImagen("bosque/Pasto.png"), 300, 303, 0);
			else
				entorno.dibujarImagen(Herramientas.cargarImagen("bosque/Pasto2.png"), 300, 303, 0);
		}
	}
	public void dibujarPinos() {
		if(estado.esJuego()) {
			pino1.dibujarPino();
			pino2.dibujarPino();
			pino3.dibujarPino();
			pino4.dibujarPino();
			pino5.dibujarPino();
			pino6.dibujarPino();
			pino7.dibujarPino();
	}
	}

}
