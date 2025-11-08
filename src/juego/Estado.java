package juego;


public class Estado {

	int estadoActual;
	int estadoAnterior = 1;
	
	public static final int INICIO = 1;
	public static final int JUGANDO = 2;
	public static final int PAUSA = 3;
	public static final int DERROTA = 4;
	public static final int VICTORIA = 5;
	public static final int CONFIGURACION = 6;
	
	public Estado() {

		this.estadoActual = INICIO;
	}
	
	public int getEstado() {	//getter
		return estadoActual;
	}
	
	public void setEstado(int nuevoEstado) {	//setter
//		this.estadoAnterior = this.estadoActual;
		this.estadoActual = nuevoEstado;
	}
	
	//"getters" 
	public boolean esInicio() {
		return estadoActual == INICIO;
	}
	
	public boolean esJuego() {
		return estadoActual == JUGANDO;
	}
	
	public boolean esPausa() {
		return estadoActual == PAUSA;
	}
	
	public boolean esDerrota(){
		return estadoActual == DERROTA;
	}
	
	public boolean esVictoria() {
		return estadoActual == VICTORIA;
	}
	
	public boolean esConfig() {
		return estadoActual == CONFIGURACION;
	}
}


