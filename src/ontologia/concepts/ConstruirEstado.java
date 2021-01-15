package src.ontologia.concepts;

public class ConstruirEstado {
	
	EstadoJuego EstadoJuego;
	
	public ConstruirEstado() {
		this.EstadoJuego = new EstadoJuego();
	}
	
	public ConstruirEstado(EstadoJuego estado) {
		this.EstadoJuego = estado;
	}
	
	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}
	
	public void setEstadoJuego(EstadoJuego estado) {
		EstadoJuego = estado;
	}
}
