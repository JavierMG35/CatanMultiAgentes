package src.ontologia;

import java.util.ArrayList;
import src.Jugador.Jugador;
import java.util.List;
public class Orden {
	
	List<Jugador> jugadores;
	int turno;
	int inicializada=0;
	Jugador siguiente_jugador;
	
	
	
	public Jugador getSiguiente_jugador() {
		this.turno = this.turno + 1;
		if(turno==jugadores.size()) {
			this.turno = 0;
			return jugadores.get(jugadores.size()-1);
		}
		else {return jugadores.get(this.turno-1);}
	}

	public void setSiguiente_jugador(Jugador siguiente_jugador) {
		this.siguiente_jugador = siguiente_jugador;
	}


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
	public int  getNumeroJugadores() {
		return jugadores.size();
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
