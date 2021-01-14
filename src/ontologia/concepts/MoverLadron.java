package src.ontologia.concepts;

public class MoverLadron {

	EstadoJuego EstadoJuego;
	
	public MoverLadron() {
		this.EstadoJuego = new EstadoJuego();
	}
	
	public MoverLadron(EstadoJuego estado) {
		this.EstadoJuego = estado;
	}
	
	public EstadoJuego getEstadoJuego() {
		return EstadoJuego;
	}
	
	public void setEstadoJuego(EstadoJuego estado) {
		EstadoJuego = estado;
	}
	
}
