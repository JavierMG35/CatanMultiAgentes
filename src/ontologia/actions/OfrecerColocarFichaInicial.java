package src.ontologia.actions;

import src.ontologia.concepts.EstadoJuego;

public class OfrecerColocarFichaInicial {

EstadoJuego EstadoJuego;
	
	
	public OfrecerColocarFichaInicial() {
		this.EstadoJuego= new EstadoJuego();
				}
	
	public OfrecerColocarFichaInicial(EstadoJuego EstadoJuego) {
		this.EstadoJuego= EstadoJuego;
				}


	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}


	public void setEstadoJuego(EstadoJuego estadoJuego) {
		EstadoJuego = estadoJuego;
	}
}
