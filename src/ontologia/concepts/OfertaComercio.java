package src.ontologia.concepts;

public class OfertaComercio {
	
	EstadoJuego EstadoJuego;
	
	
	public OfertaComercio() {
		this.EstadoJuego= new EstadoJuego();
				}
	
	public OfertaComercio(EstadoJuego EstadoJuego) {
		this.EstadoJuego= EstadoJuego;
				}


	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}


	public void setEstadoJuego(EstadoJuego estadoJuego) {
		EstadoJuego = estadoJuego;
	}

}
