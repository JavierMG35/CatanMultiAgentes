package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class OfrecerCartaDesarrollo {
	
	EstadoJuego EstadoJuego;
	
	
	public OfrecerCartaDesarrollo() {
		this.EstadoJuego= new EstadoJuego();
				}
	
	public OfrecerCartaDesarrollo(EstadoJuego EstadoJuego) {
		this.EstadoJuego= EstadoJuego;
				}


	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}


	public void setEstadoJuego(EstadoJuego estadoJuego) {
		EstadoJuego = estadoJuego;
	}

}
