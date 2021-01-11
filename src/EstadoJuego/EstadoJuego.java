package src.EstadoJuego;

import src.Mapa.Mapa;
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
	
	public Jugador getMyself(Jugador jugador) {
		Jugador myself = new Jugador();
		for(int i=0; i<jugadores.size();i++) {
			if(jugadores.get(i).equals(jugador)) {
				myself = jugadores.get(i);
			}
		}
		return myself;
	}
	
	public void setMyself(Jugador jugador) {
		//Jugador myself = new Jugador();
		int posicion = 0;
		for(int i=0; i<jugadores.size();i++) {
			if(jugadores.get(i).getNombre().equals(jugador.getNombre())) {
			 jugadores.set(i, jugador);
			 posicion = i;
			}
		}
		System.out.println(jugadores.get(posicion).getNombre());
		
	}
	
	public Mapa getMapa() {
		return this.Mapa;
	}
	public void setMapa(Mapa mapsa) {
		this.Mapa = mapsa;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
	
	
}
