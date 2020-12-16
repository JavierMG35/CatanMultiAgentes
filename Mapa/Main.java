package Mapa;
import java.util.ArrayList;
import java.util.List;

import Mapa.Tablero.Casilla;
import Mapa.Tablero.Node;
import Mapa.Tablero.Tablero;


public class Main {

	public static void main(String[] args) {
		Tablero tablero = new Tablero();
		tablero.crearTablero(tablero);
		//Esto es un poco mierda lo sé jaja
		tablero.iniciarTablero(tablero);
		
		
		//Comprobación
		List<Casilla> casillas = tablero.getCasillas();
		//List<Node> adyacentes = casillas.get(0).getAdyacentes();
		for(int i=0; i<casillas.size();i++) {
			int q = i+1;
			System.out.println("Casilla número: " + q);
			System.out.println("    Recurso: " + casillas.get(i).getRecurso());
			System.out.println("    Valor: " + casillas.get(i).getValor());
			List<Node> adyacentes = casillas.get(i).getAdyacentes();
			for(int j=0;j<adyacentes.size();j++) {
				String posicion = adyacentes.get(j).getPosicion();
				System.out.println("    Nodo: " + posicion );
			}
			}
		
	}
}
