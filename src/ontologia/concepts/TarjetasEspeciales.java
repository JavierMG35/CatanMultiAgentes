package src.ontologia.concepts;

import java.util.*;
import src.Jugador.Jugador;

public class TarjetasEspeciales {

	private String tipo;
	private Jugador due�o;
	
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
	public Jugador getDue�o() {
		return due�o;
	}
	public void setDue�o(Jugador due�o) {
		this.due�o = due�o;
	}
}
