package src.ontologia;

import java.util.ArrayList;

public class Orden {
	
	ArrayList jugadores;
	int turno;
	int inicializada=0;
	
	
	
	public int getInicializada() {
		return inicializada;
	}

	public void setInicializada(int inicializada) {
		this.inicializada = inicializada;
	}

	public Orden() {
		this.jugadores = new ArrayList();
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

	public ArrayList getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList jugadores) {
		this.jugadores = jugadores;
	}


	
	
	
}
