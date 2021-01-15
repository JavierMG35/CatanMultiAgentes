package src.Mapa;

import java.util.ArrayList;
import java.util.List;

import src.Jugador.Jugador;

public class Edge {
	private Node origen;
	private Node destino;
	private boolean carretera;
	private Jugador dueño;
	private List<Casilla> casillas;
	
	
	public Edge() {
		casillas= new ArrayList<>();
		this.carretera = false;
	}
	
	public List<Casilla> getCasillas() {
		return casillas;
	}
	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}
	public Edge(Node origen, Node destino) {
		casillas= new ArrayList<>();
		this.origen = origen;
		this.destino = destino;
		this.carretera = false;

	}
	
	
	public boolean posible() {	
		int count=0;
		for (int i=0; i < casillas.size(); i++) {
			if (casillas.get(i).getRecurso().getTipo().equals("Agua"))count++;
		}
		if (count<=1) {return true;}
		return false;
	}
	
	public Node getAdyacente() {
		return destino;
	}
	public Node getOrigen() {
		return origen;
	}
	public void setOrigen(Node origen) {
		this.origen = origen;
	}
	public Node getDestino() {
		return destino;
	}
	public void setDestino(Node destino) {
		this.destino = destino;
	}
	public boolean isCarretera() {
		return carretera;
	}
	public void setCarretera(boolean carretera) {
		this.carretera = carretera;
	}
	public Jugador getDueño() {
		return dueño;
	}
	public void setDueño(Jugador dueño) {
		this.destino.getEdge(this.origen).setDueño(dueño);
		this.dueño = dueño;
	}
}

