package src.ontologia.actions;

import src.ontologia.concepts.*;

public class OfrecerNegociacion extends Action{
	
	EstadoJuego estadodejuego;
	public OfrecerNegociacion() {
		
	}
	public OfrecerNegociacion(EstadoJuego estadojuego) {
		this.estadodejuego = estadojuego;
	}
	public EstadoJuego getEstadodejuego() {
		return estadodejuego;
	}
	public void setEstadodejuego(EstadoJuego estadodejuego) {
		this.estadodejuego = estadodejuego;
	}
	
}
