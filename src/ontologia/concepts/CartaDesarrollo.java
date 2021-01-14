package src.ontologia.concepts;

import java.util.Random;

public class CartaDesarrollo {
	
	String Tipo;
	
	public CartaDesarrollo() {
		int id;
		Random rand1 =  new Random();
		id=rand1.nextInt(2);
		
		switch (id) {
			case 0:
				this.Tipo="Caballero";
			break;
			
			case 1:
				this.Tipo="Progreso";
				break;
			case 2:
				this.Tipo="Puntos de victoria";
				break;
			

		default:
			break;
		}
		// TODO Auto-generated constructor stub
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

}
