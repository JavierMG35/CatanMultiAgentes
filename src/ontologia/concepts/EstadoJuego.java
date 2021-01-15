package src.ontologia.concepts;

import src.Jugador.*;
import java.util.List;
import java.util.ArrayList;

public class EstadoJuego {
	
	Mapa Mapa;
	List<Jugador> jugadores ;
	TarjetasEspeciales Ruta_Comercial;
	TarjetasEspeciales Mayor_Ejercito;
	
	public EstadoJuego() {
		jugadores = new ArrayList<>();
	}

	public EstadoJuego(Mapa Mapa) {
		this.Mapa=Mapa;
		crearTarjetasEspeciales("Ruta Comercial", "Mayor Ejercito");
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
	
	
	public void setRuta_Comercial(TarjetasEspeciales Ruta_Comercial) {
		this.Ruta_Comercial = Ruta_Comercial;
	}
	public void setMayor_Ejercito(TarjetasEspeciales Mayor_Ejercito) {
		this.Mayor_Ejercito = Mayor_Ejercito;
	}
	
	public TarjetasEspeciales getRuta_Comercial() {
		return this.Ruta_Comercial;
	}
	public TarjetasEspeciales getMayor_Ejercito() {
		return this.Mayor_Ejercito;
	}
	
	//Metodo para la creacion de los dos tipos de tarjetas especiales
	public void crearTarjetasEspeciales(String ruta, String ejercito) {
		this.Ruta_Comercial = new TarjetasEspeciales(ruta);
		this.Mayor_Ejercito = new TarjetasEspeciales(ejercito);
	}
	
}
