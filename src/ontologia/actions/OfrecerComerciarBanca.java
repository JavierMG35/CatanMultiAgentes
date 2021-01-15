package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class OfrecerComerciarBanca {
	
	EstadoJuego EstadoJuego;
	
	
	public OfrecerComerciarBanca() {
		this.EstadoJuego= new EstadoJuego();
				}
	
	public OfrecerComerciarBanca(EstadoJuego EstadoJuego) {
		this.EstadoJuego= EstadoJuego;
				}


	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}


	public void setEstadoJuego(EstadoJuego estadoJuego) {
		EstadoJuego = estadoJuego;
	}

}
