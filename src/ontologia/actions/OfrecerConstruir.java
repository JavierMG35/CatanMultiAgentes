package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class OfrecerConstruir {
	
	EstadoJuego EstadoJuego;
	
	public OfrecerConstruir() {
		this.EstadoJuego = new EstadoJuego();
	}
	
	public OfrecerConstruir(EstadoJuego estado) {
		this.EstadoJuego = estado;
	}
	
	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}
	
	public void setEstadoJuego(EstadoJuego estado) {
		EstadoJuego = estado;
	}
}
