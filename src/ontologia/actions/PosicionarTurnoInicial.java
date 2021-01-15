package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class PosicionarTurnoInicial {

	EstadoJuego estadojuego;
	
	public PosicionarTurnoInicial() {
		// TODO Auto-generated constructor stub
	}
	public PosicionarTurnoInicial(EstadoJuego estadojuego) {
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