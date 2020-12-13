package Mapa;
import java.util.ArrayList;
import java.util.List;
public class Node {
	private boolean construccion;
	private String tipo;
	private String dueño;
	private List<Edge> edges;
	private String posicion;
	
	public Node(String posicion) {
		this.construccion = false;
		this.posicion = posicion;
	}
	//Edges en node o graph??
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
}

