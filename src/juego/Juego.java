package juego;
import java.util.Arrays;
import java.awt.Color;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Variables y métodos propios de cada grupo
	private Menu menu;
	private Estado estado;
	private Nivel nivel;
	private Sonido sonido;
	private Poder poder;
	private Bosque bosque;
	private Reloj reloj;
	private Gondolf gondolf;
	private Cueva cueva;
	private VladTepes vlad;
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "El camino de Gondolf - TP en Proceso", 800, 600);

		// Inicializar lo que haga falta para el juego
		this.estado = new Estado();
		this.nivel = new Nivel();
		this.sonido = new Sonido(this.entorno);

		this.reloj = new Reloj(this.entorno, this.estado, 0);
		this.bosque = new Bosque(this.entorno, this.estado, this.reloj);
		this.gondolf = new Gondolf(this.entorno, this.estado, this.reloj, this.bosque);
		this.cueva = new Cueva(this.entorno, this.estado, this.reloj, this.gondolf, this.nivel, this.bosque);
		this.vlad = new VladTepes(this.entorno, this.estado, this.gondolf);
		this.poder = new Poder(this.entorno, this.estado, this.gondolf, this.cueva, this.reloj, this.bosque, this.vlad);
		this.menu = new Menu(this.entorno, estado, nivel, sonido, poder);
		
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		/*if(estado.esJuego() && entorno.sePresiono('p'))
			estado.setEstado(3);
		if(estado.esPausa() && entorno.sePresiono('p'))
			estado.setEstado(2);
		*/
		
		////////////////////////////GESTION DEL RELOJ///////////////////////////////////////
		if(estado.esJuego() && (estado.estadoAnterior == 1 || estado.estadoAnterior == 6)) 
			reloj.tiempoObjeto(true, false, false, false);
		if(estado.esPausa() && estado.estadoAnterior == 2)
			reloj.tiempoObjeto(false, true, false, false);
		if(estado.esJuego() && estado.estadoAnterior == 3)
			reloj.tiempoObjeto(false, false, true, false);
		if(estado.esJuego())
			reloj.tiempoObjeto(false, false, false, true);
		estado.estadoAnterior = estado.getEstado();	
		////////////////////////////////////////////////////////////////////////////////////
		bosque.dibujarPiso();
		if(gondolf.relativo_a_Pino()) {
			bosque.dibujarPinos();
			if(gondolf.vida > 0)
				gondolf.dibujarGondolf();
		}
		if(!gondolf.relativo_a_Pino()) {
			if(gondolf.vida > 0)
				gondolf.dibujarGondolf();
			bosque.dibujarPinos();
		}
		
		if(estado.getEstado() == 2) {
		
			cueva.spawnBats();
			cueva.dibujarBats();
		}
		if(nivel.getNivel() == 5) {
			vlad.entrada();
			vlad.juegaVlad();
		}
		
		
		menu.dibujarMenu();
		reloj.mostrarTiempo(30, 650, 600);
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
