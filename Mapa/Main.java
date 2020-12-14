package Mapa;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		Graph mapa = new Graph();
		int lana = mapa.getLana();
		System.out.println(lana);
		mapa.crearMapa(mapa);
		
		//Esto es un poco mierda lo sé jaja
		//Aqui entra en bucle infinito por el metodo setRecurso()
		mapa.iniciarMapa(mapa);
		
		List<Casilla> casillas = mapa.getCasillas();
		List<Node> adyacentes = casillas.get(0).getAdyacentes();
		for(int i=0; i<adyacentes.size();i++) {
			String posicion = adyacentes.get(i).getPosicion();
			System.out.println("Nodo: " + posicion );
			}
		
	}
}
