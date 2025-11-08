package juego;
import entorno.Entorno;
import entorno.Herramientas;

public class VladTepes {
    private Entorno entorno;
    private Estado estado;
    private Gondolf gondolf;
    private Reloj reloj;
    
    int estadoTepes = 0;
    int vida = 100;
    int posX = 300;
    int posY = -100;
    int tiempoInicioAtaque = 0;
    boolean ataqueEnCurso = false;
    int ataqueX;
    int ataqueY;
    
    public VladTepes(Entorno entorno, Estado estado, Gondolf gondolf) {
        this.entorno = entorno;
        this.estado = estado;
        this.gondolf = gondolf;
        this.reloj = new Reloj(this.entorno, this.estado, 0);
    }
    
    public void entrada() {
        if(posY < 100) {
            posY++;
            if (posY == 100) {
                tiempoInicioAtaque = entorno.numeroDeTick();
                ataqueEnCurso = true;
            }
        }
    }
    
    public void juegaVlad() {
        if (posY < 100) return; // No hacer nada hasta estar en posición
        
        int tiempoActual = entorno.numeroDeTick();
        int tiempoTranscurrido = tiempoActual - tiempoInicioAtaque;
        
        // Ciclo de ataque cada 5 segundos
        if (tiempoTranscurrido > 300 && ataqueEnCurso) {
            tiempoInicioAtaque = tiempoActual;
            ataqueX = gondolf.posX;
            ataqueY = gondolf.posY;
        }
        
        // Dibujar a Vlad
        entorno.dibujarImagen(Herramientas.cargarImagen("murc/Vlad1.png"), posX, posY, 0);
        
        // Animación de ataque
        if (ataqueEnCurso) {
            int faseAtaque = tiempoTranscurrido % 300;
            
            if (faseAtaque < 100) {
                // Fase 1: Preparación
                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Vlad2.png"), posX, posY, 0);
            } 
            else if (faseAtaque < 200) {
                // Fase 2: Lanzamiento
                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Vlad3.png"), posX, posY, 0);
                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Estaca1.png"), ataqueX, ataqueY, 0);
            } 
            else {
                // Fase 3: Impacto
                entorno.dibujarImagen(Herramientas.cargarImagen("murc/Vlad4.png"), posX, posY, 0);
                
                // Verificar colisión con Gondolf
                if (Math.abs(gondolf.posX - ataqueX) < 30 && Math.abs(gondolf.posY - ataqueY) < 30) {
                    gondolf.vida = 0;
                    entorno.dibujarImagen(Herramientas.cargarImagen("murc/EstacaGon1.png"), ataqueX, ataqueY, 0);
                } else {
                    entorno.dibujarImagen(Herramientas.cargarImagen("murc/Estaca3.png"), ataqueX, ataqueY, 0);
                }
            }
        }
    }
}
