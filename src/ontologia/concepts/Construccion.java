package src.ontologia.concepts;



import src.Jugador.Jugador;
import src.Mapa.Node;
import src.Mapa.Edge;

public class Construccion {
	String tipo;
	Node nodo;
	Edge camino;
	Jugador dueño;
	
	public Construccion() {
	}
	
	public Construccion(String tipo, Node nodo, Jugador dueño, Edge camino) {
		this.tipo = tipo;
		this.nodo = nodo;
		this.dueño = dueño;
		this.camino = camino;
	}
	
	public Construccion(String tipo, Node nodo, Edge edge) {
		this.tipo = tipo;
		this.nodo = nodo;
		this.camino = edge;
	}
	
	public Node getNodo() {
		return nodo;
	}
	public void setNodo(Node nodo) {
		this.nodo = nodo;
	}
	public Edge getCamino() {
		return camino;
	}
	public void setCamino(Edge camino) {
		this.camino = camino;
	}
	public Jugador getDueño() {
		return dueño;
	}
	public void setDueño(Jugador dueño) {
		this.dueño = dueño;
	}
	public Construccion(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
