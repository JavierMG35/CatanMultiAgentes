package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class ProponerCartaDesarrollo {
	
	EstadoJuego EstadoJuego;
	
	
	public ProponerCartaDesarrollo() {
		this.EstadoJuego= new EstadoJuego();
				}
	
	public ProponerCartaDesarrollo(EstadoJuego EstadoJuego) {
		this.EstadoJuego= EstadoJuego;
				}


	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}


	public void setEstadoJuego(EstadoJuego estadoJuego) {
		EstadoJuego = estadoJuego;
	}

}
