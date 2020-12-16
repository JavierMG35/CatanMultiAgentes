package cons;

import java.util.Random;

public class Hexagono {

	//Atributos de la casilla
	int Prob;
	
	public int [] setValores() {
		int [] Posicion = new int[Constantes.CasillaRecurso];
		int [] Puntuacion  = {0,2,12,3,3,11,11,4,4,10,10,5,5,9,9,6,6,8,8};
		
		for (int i=0; i<Posicion.length; i++) {
			
			int rnd = new Random().nextInt(Puntuacion.length);
			int val = Puntuacion[rnd];
			Posicion[i] = val;
			System.out.println("Posicion " + i + ":" + val);
			int []copy = new int[Puntuacion.length - 1];
			
			for (int i2=0, j=0; i2<Puntuacion.length; i2++) {
							
				if (i2 != rnd) {
					copy[j] = Puntuacion[i2];
					j++;
				}
			}
			
			Puntuacion = copy;
			
		}
		return Posicion;
	}
	
	
}

