package src.ontologia.actions;

import java.util.List;
import src.Jugador.*;
import src.ontologia.concepts.*;
public class RealizarOfertaJugador {
	Recurso te_doy;
	Recurso me_das;
	Jugador jugador;
	EstadoJuego estadojuego;

	 public RealizarOfertaJugador() {
		 
	 }
	 public RealizarOfertaJugador(Recurso doy, Recurso a_cambio_de, Jugador ofertante, EstadoJuego estadodeljuego) {
		 this.te_doy = doy;
		 this.me_das = a_cambio_de;
		 this.jugador = ofertante;
		 this.estadojuego = estadodeljuego;
	 }

	public Recurso getTe_doy() {
		return te_doy;
	}

	public void setTe_doy(Recurso te_doy) {
		this.te_doy = te_doy;
	}

	public Recurso getMe_das() {
		return me_das;
	}

	public void setMe_das(Recurso me_das) {
		this.me_das = me_das;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public EstadoJuego getEstadojuego() {
		return estadojuego;
	}
	public void setEstadojuego(EstadoJuego estadojuego) {
		this.estadojuego = estadojuego;
	}
	
	
	 
}
