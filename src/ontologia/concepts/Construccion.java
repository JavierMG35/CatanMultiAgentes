package src.ontologia.concepts;



import src.Jugador.Jugador;
import src.Mapa.Node;
import src.Mapa.Edge;

public class Construccion {
	String tipo;
	Node nodo;
	Edge camino;
	Jugador due�o;
	
	public Construccion() {
	}
	
	public Construccion(String tipo, Node nodo, Jugador due�o, Edge camino) {
		this.tipo = tipo;
		this.nodo = nodo;
		this.due�o = due�o;
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
	public Jugador getDue�o() {
		return due�o;
	}
	public void setDue�o(Jugador due�o) {
		this.due�o = due�o;
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
