package src.ontologia.actions;

import java.util.List;
import src.Jugador.*;
import src.ontologia.concepts.*;
public class RealizarOferta {
	List<Recurso> te_doy;
	Recurso me_das;
	Jugador jugador;

	 public RealizarOferta(List<Recurso> doy, Recurso a_cambio_de, Jugador ofertante) {
		 this.te_doy = doy;
		 this.me_das = a_cambio_de;
		 this.jugador = ofertante;
	 }

	public List<Recurso> getTe_doy() {
		return te_doy;
	}

	public void setTe_doy(List<Recurso> te_doy) {
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
	 
}
