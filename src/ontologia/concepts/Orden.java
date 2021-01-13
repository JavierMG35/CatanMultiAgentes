package src.ontologia.concepts;

import java.util.ArrayList;
import src.Jugador.Jugador;
import java.util.List;
public class Orden {
	
	List<Jugador> jugadores;
	Jugador siguiente_jugador;
	Jugador anterior_jugador;
	
	public Orden() {
		this.jugadores = new ArrayList<>();
		siguiente_jugador=new Jugador();
		anterior_jugador=new Jugador();
	}
	
	/*public Jugador getSiguiente_jugador() {
		this.turno = this.turno + 1;
		if(turno==jugadores.size()) {
			this.turno = 0;
			return jugadores.get(jugadores.size()-1);
		}
		else {return jugadores.get(this.turno-1);}
	}

	public void setSiguiente_jugador(Jugador siguiente_jugador) {
		this.siguiente_jugador = siguiente_jugador;
	}*/
	

	
	public Jugador getSiguiente_jugador() {
		return siguiente_jugador;
	}

	public void setSiguiente_jugador(Jugador siguiente_jugador) {
		this.siguiente_jugador = siguiente_jugador;
	}

	public Jugador getAnterior_jugador() {
		return anterior_jugador;
	}


	
	/*public Jugador getAnterior_jugador() {
		this.turno = this.turno - 1;
		if(turno == -1) {
			this.turno = jugadores.size();
			return jugadores.get(jugadores.size());
		}
		else {return jugadores.get(this.turno);}
	}*/

	public void setAnterior_jugador(Jugador anterior_jugador) {
		this.anterior_jugador = anterior_jugador;
	}




	public List<Jugador>  getJugadores() {
		return jugadores;
	}


	public void setJugadores(List<Jugador>  jugadores) {
		this.jugadores = jugadores;
	}
	
	/*public void addJugador(Jugador jugador) {
		if (jugadores ==null) {
			jugadores = new ArrayList<>();
		}
		jugadores.add(jugador);
	}*/


	
	
	
}
