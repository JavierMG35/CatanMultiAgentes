package src.ontologia.concepts;

import java.util.Random;

public class Dados {
	public int dados;
	public int dado1;
	public int dado2;
	public int id;
	public Random rand1;
	public Random rand2;
	

	
	public Dados() {
		Random rand3=new Random();
		this.id=rand3.nextInt(312312312);
		Random rand1 =  new Random();
		Random rand2 =  new Random();
		this.dado1 = rand1.nextInt(6)+1;
		this.dado2 = rand2.nextInt(6) +1;
		this.dados = dado1 + dado2;
	}
	

	
	public int getId() {
		return id;
	}






	public int getDado1() {
		return dado1;
	}

	public void setDado1(int dado1) {
		this.dado1 = dado1;
	}

	public int getDado2() {
		return dado2;
	}

	public void setDado2(int dado2) {
		this.dado2 = dado2;
	}

	public Random getRand1() {
		return rand1;
	}

	public void setRand1(Random rand1) {
		this.rand1 = rand1;
	}

	public Random getRand2() {
		return rand2;
	}

	public void setRand2(Random rand2) {
		this.rand2 = rand2;
	}

	public int getDados() {
		return dados;
	}

	public void setDados(int dados) {
		this.dados = dados;
	}

	
}
