package src.ontologia.predicates;

import src.ontologia.concepts.EstadoJuego;

public class NegociacionTerminada {
	EstadoJuego estadodejuego;
	public  NegociacionTerminada() {
		
	}
	
	public  NegociacionTerminada(EstadoJuego estadojuego) {
	this.estadodejuego = estadojuego;
	}

	public EstadoJuego getEstadodejuego() {
		return estadodejuego;
	}

	public void setEstadodejuego(EstadoJuego estadodejuego) {
		this.estadodejuego = estadodejuego;
	}

	
}
