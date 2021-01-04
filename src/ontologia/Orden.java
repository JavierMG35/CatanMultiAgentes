package src.ontologia;

import java.util.ArrayList;
import src.Jugador.Jugador;
import java.util.List;
public class Orden {
	
	List<Jugador> jugadores;
	int turno;
	int inicializada=0;
	
	
	
	public int getInicializada() {
		return inicializada;
	}

	public void setInicializada(int inicializada) {
		this.inicializada = inicializada;
	}

	public Orden() {
		this.jugadores = new ArrayList<>();
	}

	public int getTurno() {
		return turno;
	}
	
	public boolean isEmpty() {
		return this.jugadores.isEmpty();
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public List<Jugador>  getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador>  jugadores) {
		this.jugadores = jugadores;
	}
	
	public void addJugador(Jugador jugador) {
		if (jugadores ==null) {
			jugadores = new ArrayList<>();
		}
		jugadores.add(jugador);
	}


	
	
	
}
