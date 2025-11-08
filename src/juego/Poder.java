package juego;
import entorno.Entorno;

public class Poder {
	private int poder;
	private Entorno entorno;
	private Estado estado;
	private Gondolf gondolf;
	private Cueva cueva;
	private CadaFuego[] cadaF;
	private Reloj reloj;
	private Bosque bosque;
	private VladTepes Vlad;
	
	public static final int CHISPA = 1;
	public static final int LLAMARADA = 2;
	public static final int MURO = 3;
	
	int nFuego = 0;
	int posX;
	int posY;

	public Poder(Entorno entorno, Estado estado, Gondolf gondolf, Cueva cueva, Reloj reloj, Bosque bosque, VladTepes vlad) {
		this.entorno = entorno;
		this.estado = estado;
		this.gondolf = gondolf;
		this.cueva = cueva;
		this.poder = CHISPA;
		this.reloj = reloj;
		this.bosque = bosque;
		this.Vlad = vlad;
		//si hay mil bats y un Vlad, con lanzar 5000 poderes debería sobrar por mucho
		
		this.cadaF = new CadaFuego[5000];
		
		for(int i = 0; i < cadaF.length; i++) {
			cadaF[i] = new CadaFuego(this.entorno, this.estado, this.reloj, this.bosque, this.cueva);
		}
			
		
		
	}
	
	public void lanzar() {
		int mouseX = entorno.mouseX();
		int mouseY = entorno.mouseY();
		if(estado.getEstado() == 2 && entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO) && mouseX < 601 && 0 < mouseX && mouseY < 600 && 0 < mouseY ) {
			if(getPoder() == CHISPA) {
				
			}
		}
	}
	
	public int getPoder() {	//getter
		return poder;
	}
	
	public void setPoder(int nuevoPoder) {	//setter
		this.poder = nuevoPoder;
	}
	
	//"getters" 
	public boolean esChispa() {
		return poder == CHISPA;
	}
	
	public boolean esLlamarada() {
		return poder == LLAMARADA;
	}
	
	public boolean esMuro() {
		return poder == MURO;
	}
}
