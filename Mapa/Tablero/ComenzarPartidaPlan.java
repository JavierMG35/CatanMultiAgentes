package Mapa.Tablero;

public class ComenzarPartidaPlan {
	
	public void body() {
		Tablero tablero = new Tablero();
		tablero.crearTablero(tablero);
		//Esto es un poco mierda lo s� jaja
		tablero.iniciarTablero(tablero);
	}

}
