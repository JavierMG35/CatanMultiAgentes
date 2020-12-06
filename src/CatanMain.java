package src;

public class CatanMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	//Turno de jugador
		int dice1=(int)(Math.random()*6+1);
		int dice2=(int)(Math.random()*6+1);
		int sum = dice1 + dice2;
		
		System.out.println("El valor de la suma de los dados es " + sum);
		
		Hexagono Tablero = new Hexagono();
		int[] valores = Tablero.setValores();
		
		System.out.println("Las posiciones del tablero son " + valores);
	}

}
