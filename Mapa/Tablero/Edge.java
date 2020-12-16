package Mapa.Tablero;

public class Edge {
	private Node origen;
	private Node destino;
	private boolean carretera;
	private String dueño;
	
	public Edge(Node origen, Node destino) {
		this.origen = origen;
		this.destino = destino;
		this.carretera = false;

	}
	public Node getAdyacente() {
		return destino;
	}
}
