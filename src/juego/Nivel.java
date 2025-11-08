package juego;

public class Nivel {
	private int nivel;
	
	public static final int OLEADA_1 = 1;
	public static final int OLEADA_2 = 2;
	public static final int OLEADA_3 = 3;
	public static final int OLEADA_4 = 4;
	public static final int JEFE_FINAL = 5;
	
	public Nivel() {
		this.nivel = OLEADA_1;
	}
	
	public int getNivel() {	//getter
		return nivel;
	}
	
	public void setNivel(int nuevoNivel) {	//setter
		this.nivel = nuevoNivel;
	}
	
	//"getters" 
	public boolean es_1() {
		return nivel == OLEADA_1;
	}
	
	public boolean es_2() {
		return nivel == OLEADA_2;
	}
	
	public boolean es_3() {
		return nivel == OLEADA_3;
	}
	
	public boolean es_4(){
		return nivel == OLEADA_4;
	}
	
	public boolean esJefe() {
		return nivel == JEFE_FINAL;
	}
}
