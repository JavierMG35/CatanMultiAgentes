package src.ontologia.predicates;

import src.ontologia.concepts.EstadoJuego;

public class LadronDesplazado {

	EstadoJuego EstadoJuego;
	
	public LadronDesplazado() {
		this.EstadoJuego = new EstadoJuego();
	}
	
	public LadronDesplazado(EstadoJuego estado) {
		this.EstadoJuego = estado;
	}
	
	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}
	
	public void setEstadoJuego(EstadoJuego estado) {
		EstadoJuego = estado;
	}
	
}
