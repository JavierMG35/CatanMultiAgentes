package src.ontologia.predicates;

import src.ontologia.concepts.*;
public class TurnoInicialPosicionado {

	EstadoJuego estadojuego;
	public TurnoInicialPosicionado() {
		// TODO Auto-generated constructor stub
	}
	public TurnoInicialPosicionado(EstadoJuego estadojuego) {
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
