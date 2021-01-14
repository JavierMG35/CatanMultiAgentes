package src.ontologia.concepts;

public class OfertaCartaDesarrollo {
	
	EstadoJuego EstadoJuego;
	
	
	public OfertaCartaDesarrollo() {
		this.EstadoJuego= new EstadoJuego();
				}
	
	public OfertaCartaDesarrollo(EstadoJuego EstadoJuego) {
		this.EstadoJuego= EstadoJuego;
				}


	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}


	public void setEstadoJuego(EstadoJuego estadoJuego) {
		EstadoJuego = estadoJuego;
	}

}
