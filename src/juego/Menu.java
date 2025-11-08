package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;


public class Menu {
		Entorno entorno; // Referencia a entorno
		Estado estado;	//Referencia a estado
		Nivel nivel;
		Sonido sonido;
		Poder poder;
		int ticksFuera;
		int cuantosTicks = 0;
	
	public Menu(Entorno entorno, Estado estado, Nivel nivel, Sonido sonido, Poder poder) {
		this.entorno = entorno;
		this.estado = estado;
		this.nivel = nivel;
		this.sonido = sonido;
		this.poder = poder;
	}

	public void contarTicks(boolean iniciar) {
		if(iniciar) {
			ticksFuera = entorno.numeroDeTick();
		}
		else{
			cuantosTicks = entorno.numeroDeTick() - ticksFuera;
		}
		
	}
	public void dibujarVarita(boolean a){
		int posX = entorno.mouseX();
		int posY = entorno.mouseY();
		
		if(!a && 610 < posX && posX < 790 && 10 < posY && posY < 590)
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Varita.png"), posX, posY - 10, 0);
		if (a)
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Llama.png"), posX, posY - 10, 0);
	}
	
	public void dibujarMenu() {
		entorno.dibujarImagen(Herramientas.cargarImagen("multim/Pergamino.png"), 700, 303, 0);

		//System.out.println(cuantosTicks);
		
		
		if (estado.esInicio()) {
		entorno.dibujarImagen(Herramientas.cargarImagen("multim/Portada.png"), 300, 303, 0);
		entorno.dibujarImagen(Herramientas.cargarImagen("multim/Bienvenida.png"), 700, 90, 0);

		
		Boton botonComenzar = new Boton(this.entorno, 1, this.estado, this.nivel, this.sonido, this.poder);
		Boton botonConfig = new Boton(this.entorno, 2, this.estado, this.nivel, this.sonido, this.poder);
		
		botonComenzar.dibujarBoton();
		botonConfig.dibujarBoton();

		dibujarVarita(botonComenzar.sobreBoton());
		dibujarVarita(botonConfig.sobreBoton());

		if(estado.estadoAnterior != 1)
			contarTicks(true);
		else
			contarTicks(false);

		
		if(cuantosTicks > 10) {
			botonComenzar.pulsarBoton();
			botonConfig.pulsarBoton();
		
		}
		}
		
		if (estado.esJuego()) {
			
			Boton botonChispa = new Boton(this.entorno, 10, this.estado, this.nivel, this.sonido, this.poder);
			Boton botonLlamarada = new Boton(this.entorno, 11, this.estado, this.nivel, this.sonido, this.poder);
			Boton botonMuro = new Boton(this.entorno, 12, this.estado, this.nivel, this.sonido, this.poder);
			
			botonChispa.dibujarBoton();
			botonLlamarada.dibujarBoton();
			botonMuro.dibujarBoton();
			
			dibujarVarita(botonChispa.sobreBoton());
			dibujarVarita(botonLlamarada.sobreBoton());
			dibujarVarita(botonMuro.sobreBoton());
			
			if(estado.estadoAnterior != 2)
				contarTicks(true);
			else
				contarTicks(false);
			
			
			if(cuantosTicks > 10) {			
				botonChispa.pulsarBoton();
				botonLlamarada.pulsarBoton();
				botonMuro.pulsarBoton();			
				if(entorno.sePresiono('p')) 
					estado.setEstado(3);
			}
			
			}
		
		if (estado.esConfig()) {
			
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/ConfigMenu.png"), 700, 90, 0);
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Controles.png"), 300, 303, 0);

			Boton botonComenzar = new Boton(this.entorno, 1, this.estado, this.nivel, this.sonido, this.poder);
			Boton botonNivel = new Boton(this.entorno, nivel.getNivel()+2, this.estado, this.nivel, this.sonido, this.poder);
			Boton botonMusica = new Boton(this.entorno, this.sonido.onOff_enNumeritos()+8, this.estado, this.nivel, this.sonido, this.poder);
			
			botonComenzar.dibujarBoton();
			botonNivel.dibujarBoton();
			botonMusica.dibujarBoton();
			
			dibujarVarita(botonNivel.sobreBoton());
			dibujarVarita(botonComenzar.sobreBoton());
			dibujarVarita(botonMusica.sobreBoton());
			
			if(estado.estadoAnterior != 6)
				contarTicks(true);
			else
				contarTicks(false);
			
			if(cuantosTicks > 10) {
				botonComenzar.pulsarBoton();
				botonNivel.pulsarBoton();
				botonMusica.pulsarBoton();
			}
		}
		
		if (estado.esPausa()) {
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Gondolf_pausa.png"), 300, 303, 0);
			entorno.dibujarImagen(Herramientas.cargarImagen("multim/Pausa_bot.png"), 700, 303, 0);
			//entorno.dibujarImagen(Herramientas.cargarImagen("multim/tecla_c.png"), cuantosTicks, ticksFuera, cuantosTicks)
			if(estado.estadoAnterior == 2)
				contarTicks(true);
			else
				contarTicks(false);
			 

			 if(cuantosTicks > 10) {
				if(entorno.sePresiono('c'))
					estado.setEstado(2);
		}
}
		if (estado.esDerrota()) {
			
		}
		if (estado.esVictoria()) {
			
		}
	}
}
