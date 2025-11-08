package juego;
import entorno.Entorno;
import entorno.Herramientas;

public class Boton {
	private Entorno entorno;
	private int tipo;
	private int xCentro;
	private int yCentro;
	private Estado estado;
	private Nivel nivel;
	private Sonido sonido;
	private Poder poder;
	//tipo 1 comenzar juego
	//tipo 2 configuracion
	//tipo 3 al 7 niveles
	//tipo 8 y 9 musica
	//tipo 10, 11 y 12 poderes
	//tipo 13 pausa
	
	public Boton(Entorno entorno, int tipo, Estado estado, Nivel nivel, Sonido sonido, Poder poder) {
		this.tipo = tipo;
		this.entorno = entorno;
		this.xCentro = 700;
		this.yCentro = 0;
		this.estado = estado;
		this.nivel = nivel;
		this.sonido = sonido;
		this.poder = poder;
}
	public void pulsarBoton() {
		
		boolean pulsado = entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO);
		int tipo = this.tipo;
		if(this.sobreBoton() && pulsado && tipo == 1) {
			this.estado.setEstado(2);
		}
		
		if(this.sobreBoton() && pulsado && tipo == 2) {
			this.estado.setEstado(6);
		}
		
		if(this.sobreBoton() && pulsado && 2 < tipo && tipo < 7) {
			this.nivel.setNivel((tipo-1));
		}
		
		if(this.sobreBoton() && pulsado && tipo == 7) {
			this.nivel.setNivel(1);
		}
		
		if(this.sobreBoton() && pulsado && (tipo == 8 || tipo == 9)) {
			boolean prendido = sonido.get_onOff();
			this.sonido.setOnOff(!prendido);
		}
		
		if(this.sobreBoton() && pulsado && (tipo == 10)) {
			poder.setPoder(1);			
		}
		
		if(this.sobreBoton() && pulsado && (tipo == 11)) {
			poder.setPoder(2);
		}
		
		if(this.sobreBoton() && pulsado && (tipo == 12)) {
			poder.setPoder(3);
		}
	}
	public boolean sobreBoton() {
		int posX = entorno.mouseX();
		int posY = entorno.mouseY();
		
		if((this.xCentro - 75 < posX)  && posX < (this.xCentro + 75) && (this.yCentro - 30) < posY && posY < this.yCentro + 30) {
			return true;
		}
		else
			return false;
	}
	
	public void dibujarBoton() {
		if(tipo == 1) {
			this.yCentro = 210;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/BotComenzar.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 2) {
			this.yCentro = 315;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/BotConfig.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 3) {
			this.yCentro = 315;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Oleada_1.png"), xCentro, yCentro, 0);
			}
		
		if(tipo == 4) {
			this.yCentro = 315;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Oleada_2.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 5) {
			this.yCentro = 315;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Oleada_3.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 6) {
			this.yCentro = 315;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Oleada_4.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 7) {
			this.yCentro = 315;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Oleada_dracula.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 8) {
			this.yCentro = 420;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/MusicaOff.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 9) {
			this.yCentro = 420;
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/MusicaOn.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 10) {
			this.yCentro = 300;
			if(!poder.esChispa()) {
				entorno.dibujarImagen(Herramientas.cargarImagen("multim/MenChispa.png"), xCentro, yCentro, 0);
			}
			else {
				entorno.dibujarImagen(Herramientas.cargarImagen("multim/MenChispaActiva.png"), xCentro, yCentro, 0);
			}
		}
		
		if(tipo == 11) {
			this.yCentro = 405;
			if(!poder.esLlamarada())
				entorno.dibujarImagen(Herramientas.cargarImagen("multim/MenLlam.png"), xCentro, yCentro, 0);
			
			else
				entorno.dibujarImagen(Herramientas.cargarImagen("multim/MenLlamActiva.png"), xCentro, yCentro, 0);
		}
		
		if(tipo == 12) {
			this.yCentro = 510;
			if(!poder.esMuro())
				entorno.dibujarImagen(Herramientas.cargarImagen("multim/MenMuro.png"), xCentro, yCentro, 0);
			
			else
				entorno.dibujarImagen(Herramientas.cargarImagen("multim/MenMuroAct.png"), xCentro, yCentro, 0);
		}
	}
}
	