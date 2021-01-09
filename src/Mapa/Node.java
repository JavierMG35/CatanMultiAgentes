package src.Mapa;
import java.util.ArrayList;
import src.Jugador.*;
import java.util.List;
public class Node {
	private boolean construccion;
	private String tipo;
	private Jugador dueño;
	private List<Edge> edges;
	private String posicion;
	
	public Node(String posicion) {
		this.construccion = false;
		this.posicion = posicion;
	}
	//Edges en node o graph?? Mejor aqui, no??
	public void addEdge(Edge edge) {
		if(edges ==null) {
			edges = new ArrayList<>();
		}
		edges.add(edge);
	}
	
	public  String getPosicion() {
		return this.posicion;
	}
	
	public List<Edge> getEdges() {
		return this.edges;
	}
	public boolean isConstruccion() {
		return construccion;
	}
	public void setConstruccion(boolean construccion) {
		this.construccion = construccion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Jugador getDueño() {
		return dueño;
	}
	public void setDueño(Jugador dueño) {
		this.dueño = dueño;
	}
	
}

