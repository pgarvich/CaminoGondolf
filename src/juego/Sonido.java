package juego;
import entorno.Entorno;
import entorno.Herramientas;

public class Sonido {
	private boolean on_Off;
	private int tema;
	private Entorno entorno;

	public Sonido(Entorno entorno) {
		this.on_Off = true;
		this.tema = 1;
		this.entorno = entorno;
	}
	
	public boolean get_onOff() {	//getter
		return on_Off;
	}
	
	public int onOff_enNumeritos(){
		if(this.on_Off)
			return 1;
		else
			return 0;
	}
	
	public void setOnOff(boolean onOff) {	//setter
		this.on_Off = onOff;
	}
}
