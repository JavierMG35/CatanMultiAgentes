package src.Tablero;

import java.util.Random;

public class Dados {
	public int dados;
	public int dado1;
	public int dado2;
	public Random rand1;
	public Random rand2;
	public Dados() {
		
		
		
	}
	
	public  void tirarDados() {
		rand1 = new Random();
		rand2 = new Random();
		dado1 = rand1.nextInt(6)+1;
		dado2 = rand2.nextInt(6) +1;
		this.dados = dado1 + dado2;
		
	}
	
	public int getDados() {
		return this.dados;
	}

}
