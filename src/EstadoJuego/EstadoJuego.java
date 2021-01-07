package src.EstadoJuego;

import src.Mapa.Mapa;

public class EstadoJuego {
	
	Mapa Mapa;
	
	public EstadoJuego() {
		
	}

	public EstadoJuego(Mapa Mapa) {
		
		this.Mapa=Mapa;
	}
	
	public Mapa getMapa() {
		return this.Mapa;
	}

	
}
