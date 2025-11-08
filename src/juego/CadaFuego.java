package juego;
import entorno.Entorno;

public class CadaFuego {

	private Entorno entorno;
	private Estado estado;
	private Reloj reloj;
	private Bosque bosque;
	private Cueva cueva;
	int inicioX;
	int inicioY;
	int destinoX;
	int destinoY;
	boolean activo = false;
	
	public CadaFuego(Entorno entorno, Estado estado, Reloj reloj, Bosque bosque, Cueva cueva) {
		this.entorno = entorno;
		this.estado = estado;
		this.reloj = reloj;
		this.bosque = bosque;
		this.cueva = cueva;
		
	}
	
}
