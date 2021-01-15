package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class DesplazarLadron {

	EstadoJuego estadojuego;
	
	public DesplazarLadron() {
		// TODO Auto-generated constructor stub
	}
	public DesplazarLadron(EstadoJuego estadojuego) {
		// TODO Auto-generated constructor stub
		this.estadojuego=estadojuego;
	}
	public EstadoJuego getEstadojuego() {
		return estadojuego;
	}
	public void setEstadojuego(EstadoJuego estadojuego) {
		this.estadojuego = estadojuego;
	}
}