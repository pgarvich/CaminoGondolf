package juego;
import entorno.Entorno;
import entorno.Herramientas;

public class Cueva {

	private Entorno entorno;
	private Estado estado;
	private Reloj reloj;
	private Nivel nivel;
	private Gondolf gondolf;
	private Bat[] bats;
	private Bosque bosque;
	int ticksFuera;
	int cuantosTicks;
	int conteoBats;
	int batsMuertos = 0;
	
	public Cueva(Entorno entorno, Estado estado, Reloj reloj, Gondolf gondolf, Nivel nivel, Bosque bosque) {
		this.entorno = entorno;
		this.estado = estado;
		this.reloj = reloj;
		this.gondolf = gondolf;
		this.nivel = nivel;
		this.bosque = bosque;
		
		
		
		//1000 bats alcanzan para todos los niveles y sobran para matar a Vlad Tepes
		this.bats = new Bat[1000];
		
		for(int i = 0; i < bats.length; i++) {
			bats[i] = new Bat(this.entorno, this.estado, this.nivel, this.gondolf, this.reloj, this.bosque);
								
		}
	}
	public void contarTicks(boolean iniciar) {
		if(entorno == null) {
			return;
		}
		if(iniciar) {
			ticksFuera = entorno.numeroDeTick();
		}
		else{
			cuantosTicks = entorno.numeroDeTick() - ticksFuera;
		}
	}
	
	public void cadaBat() {
		
		if(conteoBats >= bats.length) {
			return; //no crear mas bats, array lleno
		}
		bats[conteoBats].vivo = true;
		
		int cara = (int) (Math.random() * 4) + 1;
		
		if(cara == 1) {
			bats[conteoBats].posY = (int) (Math.random() * 600);
			bats[conteoBats].posX = 0;
		}
		if(cara == 2) {
			bats[conteoBats].posY = (int) (Math.random() * 600);
			bats[conteoBats].posX = 600;
		}
		if(cara == 3) {
			bats[conteoBats].posX = (int) (Math.random() * 600);
			bats[conteoBats].posY = 0;
		}
		if(cara == 4) {
			bats[conteoBats].posX = (int) (Math.random() * 600);
			bats[conteoBats].posY = 600;
	}
		conteoBats += 1;
	}
	
	public void spawnBats() {
	    if (nivel.getNivel() == 1) {
	        contarTicks(false);
	        if (cuantosTicks > 100 && conteoBats < 30) {
	            cadaBat();
	            contarTicks(true);
	        }
	        if (batsMuertos >= 30) {
	            nivel.setNivel(2);
	        }
	    }
	    
	    if (nivel.getNivel() == 2) {
	        contarTicks(false);
	        if (cuantosTicks > 80 && conteoBats < 90) {
	            cadaBat();
	            contarTicks(true);
	        }
	        if (batsMuertos >= 90) {
	            nivel.setNivel(3);
	        }
	    }
	    
	    if (nivel.getNivel() == 3) {
	        contarTicks(false);
	        if (cuantosTicks > 60 && conteoBats < 200) {
	            cadaBat();
	            contarTicks(true);
	        }
	        if (batsMuertos >= 200) {
	            nivel.setNivel(4);
	        }
	    }
	    
	    if (nivel.getNivel() == 4) {
	        contarTicks(false);
	        if (cuantosTicks > 40 && conteoBats < 500) {
	            cadaBat();
	            contarTicks(true);
	        }
	        if (batsMuertos >= 500) {
	            nivel.setNivel(5);
	            //VladTepes.vivo = true;
	        }
	    }
	    
	    if (nivel.getNivel() == 5) {
	        contarTicks(false);
	        if (cuantosTicks > 40 && conteoBats < 1000) {
	            cadaBat();
	            contarTicks(true);
	        }
	    }
	}
	public void dibujarBats() {
	    if(estado.getEstado() == 2){
	        for (int i = 0; i < bats.length; i++) {
	            if (bats[i] == null) 
	                continue;
	            
	            // Procesar TODOS los murciélagos, estén vivos o quemados
	            if (bats[i].vivo) {
	                bats[i].relativo_a_Gondolf(); // Siempre calcular posición relativa
	            if (!bats[i].quemar) {
	                bats[i].relativo_a_Gondolf();
	                bats[i].interaccion();
	                
	                // Dirección 1: Front derecha (Mur_Der_Fx)
	                if (bats[i].haciaGondolf == 1) {
	                    if (reloj.ciclos(100, 400)) {
	                        entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_F1.png"), bats[i].posX, bats[i].posY, 0);
	                    } 
	                    else {
	                        if (reloj.ciclos(200, 400)) {
	                            entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_F2.png"), bats[i].posX, bats[i].posY, 0);
	                        } else {
	                            if (reloj.ciclos(300, 400)) {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_F3.png"), bats[i].posX, bats[i].posY, 0);
	                            } else {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_F4.png"), bats[i].posX, bats[i].posY, 0);
	                            }
	                        }
	                    }
	                }
	                
	                // Dirección 2: Front izquierda (Mur_Izq_Fx)
	                else if (bats[i].haciaGondolf == 2) {
	                    if (reloj.ciclos(100, 400)) {
	                        entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_F1.png"), bats[i].posX, bats[i].posY, 0);
	                    } else {
	                        if (reloj.ciclos(200, 400)) {
	                            entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_F2.png"), bats[i].posX, bats[i].posY, 0);
	                        } else {
	                            if (reloj.ciclos(300, 400)) {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_F3.png"), bats[i].posX, bats[i].posY, 0);
	                            } else {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_F4.png"), bats[i].posX, bats[i].posY, 0);
	                            }
	                        }
	                    }
	                }
	                
	                // Dirección 3: Back izquierda (Mur_Izq_Bx)
	                else if (bats[i].haciaGondolf == 3) {
	                    if (reloj.ciclos(100, 400)) {
	                        entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_B1.png"), bats[i].posX, bats[i].posY, 0);
	                    } else {
	                        if (reloj.ciclos(200, 400)) {
	                            entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_B2.png"), bats[i].posX, bats[i].posY, 0);
	                        } else {
	                            if (reloj.ciclos(300, 400)) {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_B3.png"), bats[i].posX, bats[i].posY, 0);
	                            } else {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Izq_B4.png"), bats[i].posX, bats[i].posY, 0);
	                            }
	                        }
	                    }
	                }
	                
	                // Dirección 4: Back derecha (Mur_Der_Bx)
	                else if (bats[i].haciaGondolf == 4) {
	                    if (reloj.ciclos(100, 400)) {
	                        entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_B1.png"), bats[i].posX, bats[i].posY, 0);
	                    } else {
	                        if (reloj.ciclos(200, 400)) {
	                            entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_B2.png"), bats[i].posX, bats[i].posY, 0);
	                        } else {
	                            if (reloj.ciclos(300, 400)) {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_B3.png"), bats[i].posX, bats[i].posY, 0);
	                            } else {
	                                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Der_B4.png"), bats[i].posX, bats[i].posY, 0);
	                            }
	                        }
	                    }
	                }
	            }
	            else { 
	                bats[i].contarTicks(false);

	                if (bats[i].ticks > 50) {
	                    batsMuertos++;
	                    bats[i].vivo = false; //dejar de dibujar 
	                    bats[i] = null; // marcar para limpiar
	                    continue;
	                }

	                // Animación de quemado
	                if (reloj.ciclos(200, 400)) {
	                    entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Q1.png"), bats[i].posX, bats[i].posY, 0);
	                } else {
	                    entorno.dibujarImagen(Herramientas.cargarImagen("murc/Mur_Q2.png"), bats[i].posX, bats[i].posY, 0);
	                }
	            }

                
                // Colisión con Gondolf (para murciélagos no quemados)
                if (!bats[i].quemar && bats[i].distGondolf < 30) {
                    gondolf.vida = gondolf.vida - 1;
                    bats[i] = null;
                    batsMuertos += 1;
                }
            }
        }
    }
}
}


	
	

