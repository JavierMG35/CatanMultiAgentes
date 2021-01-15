package src.ontologia.concepts;

import java.util.*;
import src.Jugador.Jugador;

public class TarjetasEspeciales {

	private String tipo;
	private Jugador dueño;
	
	public TarjetasEspeciales(){
	}
	
	public TarjetasEspeciales(String nuevo){
		this.tipo=nuevo;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Jugador getDueño() {
		return dueño;
	}
	public void setDueño(Jugador dueño) {
		this.dueño = dueño;
	}
}
