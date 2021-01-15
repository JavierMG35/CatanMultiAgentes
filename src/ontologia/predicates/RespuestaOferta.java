package src.ontologia.predicates;

import src.ontologia.concepts.EstadoJuego;

public class RespuestaOferta {
	
	boolean acepto = false;
	EstadoJuego estadojuego;
	
	
	public RespuestaOferta() {
		
	}
	public RespuestaOferta(boolean decision, EstadoJuego estadodeljuego) {
		this.acepto = decision;
		this.estadojuego = estadodeljuego;
	}

	public boolean isAcepto() {
		return acepto;
	}

	public void setAcepto(boolean acepto) {
		this.acepto = acepto;
	}
	public EstadoJuego getEstadojuego() {
		return estadojuego;
	}
	public void setEstadojuego(EstadoJuego estadojuego) {
		this.estadojuego = estadojuego;
	}
	
	

}
