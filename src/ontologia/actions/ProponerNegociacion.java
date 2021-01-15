package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class ProponerNegociacion {

	EstadoJuego estadojuego;
	
	public ProponerNegociacion() {
		// TODO Auto-generated constructor stub
	}
	public ProponerNegociacion(EstadoJuego estadojuego) {
		// TODO Auto-generated constructor stub
			this.estadojuego= estadojuego;
	}
	public EstadoJuego getEstadojuego() {
		return estadojuego;
	}
	public void setEstadojuego(EstadoJuego estadojuego) {
		this.estadojuego = estadojuego;
	}
}