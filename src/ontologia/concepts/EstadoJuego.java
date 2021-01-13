package src.ontologia.concepts;

import src.Jugador.*;
import java.util.List;
import java.util.ArrayList;

public class EstadoJuego {
	
	Mapa Mapa;
	List<Jugador> jugadores ;
	
	public EstadoJuego() {
		jugadores = new ArrayList<>();
	}

	public EstadoJuego(Mapa Mapa) {
		this.Mapa=Mapa;
	}
		
	public Mapa getMapa() {
		return this.Mapa;
	}
	public void setMapa(Mapa mapa) {
		this.Mapa = mapa;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
	
	
}
