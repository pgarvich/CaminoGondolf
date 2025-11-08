package juego;
import entorno.Entorno;
import entorno.Herramientas;

public class Pino {

	private Entorno entorno;
	private Reloj reloj;
	private Estado estado;
	int estadoPino = 1;
	int posX;
	int posY;
	int estadoAnterior = 1;
	
	
	public Pino(Entorno entorno, Estado estado, int posX, int posY) {
		this.entorno = entorno;
		this.estado = estado;
		this.reloj = new Reloj(entorno, estado, 0);
		this.posX = posX;
		this.posY = posY;
	}
	
	
	public void setEstado(int nuevoEstado) {
		if(nuevoEstado != this.estadoPino) {
			this.estadoPino = nuevoEstado;
			}
		}
	
	public void dibujarPino() {
	    tiempoDelPino();
	    
	    if(estadoPino == 1) { // pino vivo
	        if(reloj.ciclos(180, 360))
	            entorno.dibujarImagen(Herramientas.cargarImagen("bosque/Pino1.png"), posX, posY, 0);
	        else
	            entorno.dibujarImagen(Herramientas.cargarImagen("bosque/Pino12.png"), posX, posY, 0);
	    }
	    else if(estadoPino == 2) { // pino prendiéndose fuego
	        for (int i = 1; i <= 8; i++) {
	            int inicio = (i - 1) * 360;
	            int fin = i * 360;
	            if (inicio <= reloj.enJuego && reloj.enJuego < fin) {
	                entorno.dibujarImagen(
	                    Herramientas.cargarImagen("bosque/PinoQ" + i + ".png"), posX, posY, 0);
	            }
	        }
	        if(reloj.enJuego > 2880) // 2.88 segundos
	            setEstado(3);
	    }
	    else if(estadoPino == 3) { // pino prendido fuego
	        if(reloj.enJuego < 12000) { // 12 segundos
	            if(reloj.ciclos(360, 720))
	                entorno.dibujarImagen(Herramientas.cargarImagen("bosque/PinoQ9.png"), posX, posY, 0);
	            else
	                entorno.dibujarImagen(Herramientas.cargarImagen("bosque/PinoQ10.png"), posX, posY, 0);
	        }
	        else {
	            // Al pasar a estado 4, reiniciamos el contador de tiempo para este estado
	            reloj.tiempoObjeto(true, false, false, false);
	            setEstado(4);
	        }
	    }
	    else if(estadoPino == 4) {
	        // Tiempo relativo al inicio del estado 4
	        int tiempoEnEstado4 = reloj.enJuego;
	        
	        for (int i = 11; i <= 24; i++) {
	            int inicio = (i - 11) * 360;
	            int fin = inicio + 360;
	            if (inicio <= tiempoEnEstado4 && tiempoEnEstado4 < fin) {
	                entorno.dibujarImagen(
	                    Herramientas.cargarImagen("bosque/PinoQ" + i + ".png"), posX, posY, 0);
	            }
	        }
	        // 5.04 segundos para la animación de quemado completo (14 frames * 0.36s)
	        if(tiempoEnEstado4 > 5040) {
	            reloj.tiempoObjeto(true, false, false, false);
	            setEstado(5);
	        }
	    }
	    else if(estadoPino == 5) { // pino quemado
	        if(reloj.ciclos(360, 720))
	            entorno.dibujarImagen(Herramientas.cargarImagen("bosque/PinoQuemado1.png"), posX, posY, 0);
	        else
	            entorno.dibujarImagen(Herramientas.cargarImagen("bosque/PinoQuemado2.png"), posX, posY, 0);
	    }
	    
	    reloj.mostrarTiempo(20, posX, posY + 70);
	}
	
	
	public void tiempoDelPino() {
	    // Lógica común de pausa para todos los estados
	    if (estado.esPausa() && estado.estadoAnterior == 2) {
	        reloj.tiempoObjeto(false, true, false, false); // Pausar
	        return;
	    }
	    
	    // Lógica común de reanudación para todos los estados
	    if (estado.esJuego() && estado.estadoAnterior == 3) {
	        reloj.tiempoObjeto(false, false, true, false); // Reanudar
	    }

	    // Estado 1: Pino vivo
	    if (estadoPino == 1) {
	        if (estado.estadoAnterior == 1) {
	            reloj.tiempoObjeto(true, false, false, false);
	        }
	        if (estado.estadoAnterior == estado.estadoActual) {
	            reloj.tiempoObjeto(false, false, false, true);
	        }
	    }
	    // Estados 2-5: Pino en diferentes fases de quema
	    else if (estadoPino >= 2 && estadoPino <= 5) {
	        if (estadoAnterior != estadoPino) {
	            reloj.tiempoObjeto(true, false, false, false); // Reiniciar al cambiar estado
	            estadoAnterior = estadoPino;
	        }
	        if (estado.esJuego()) {
	            reloj.tiempoObjeto(false, false, false, true); // Continuar
	        }
	    }
	}}