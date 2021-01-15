package src.ontologia.predicates;

import src.ontologia.concepts.EstadoJuego;

public class Construido {
	EstadoJuego estadodejuego;

		
	public Construido() {
		// TODO Auto-generated constructor stub
	}
	
	public  Construido(EstadoJuego estadojuego) {
	this.estadodejuego = estadojuego;
	}

	public EstadoJuego getEstadodejuego() {
		return estadodejuego;
	}

	public void setEstadodejuego(EstadoJuego estadodejuego) {
		this.estadodejuego = estadodejuego;
	}

	
}
