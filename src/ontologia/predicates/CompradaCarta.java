package src.ontologia.predicates;

import src.ontologia.concepts.CartaDesarrollo;
import src.ontologia.concepts.EstadoJuego;

public class CompradaCarta {

	CartaDesarrollo carta;

		
	public CompradaCarta() {
		// TODO Auto-generated constructor stub
	}
	
	public  CompradaCarta(CartaDesarrollo carta) {
	this.carta = carta;
	}

	public CartaDesarrollo getCarta() {
		return carta;
	}

	public void setCarta(CartaDesarrollo carta) {
		this.carta = carta;
	}


	
}
