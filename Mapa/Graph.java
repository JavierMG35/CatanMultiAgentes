package Mapa;
import java.util.ArrayList;
import java.util.List;
public class Graph {
	private List<Node> nodos;
	private List<Edge> edges;
	public int arcilla = 3;
	public int lana = 4;
	public int piedra = 4;
	public int paja = 4;
	public int madera  = 4;
	public int desierto = 1;
	
	
	public void addNode(Node node) {
		if (nodos ==null) {
			nodos = new ArrayList<>();
		}
		nodos.add(node);
	}
	
	public Node getNode(String posicion) {
		Node nodo = new Node(posicion);
		for (int i=0;i< nodos.size();i++) {
			 nodo = nodos.get(i);	
			if(nodo.getPosicion().equals(posicion)) {
				break;	
			}
		}
		return nodo;
	}



	
	//edges en node o en graph??
	public void addEdge(Edge edge) {
		if (edges ==null) {
			edges = new ArrayList<>();
		}
		edges.add(edge);
	}


	public void setArcilla() {
		this.arcilla = arcilla -1;
	}
	public int getArcilla() {
		return arcilla;
	}

	public int getLana() {
		return lana;
	}

	public void setLana() {
		this.lana = lana -1;
	}

	public int getPiedra() {
		return piedra;
	}

	public void setPiedra() {
		this.piedra = piedra -1;
	}

	public int getPaja() {
		return paja;
	}

	public void setPaja() {
		this.paja = paja -1;
	}

	public int getMadera() {
		return madera;
	}

	public void setMadera() {
		this.madera = madera -1;
	}

	public int getDesierto() {
		return desierto;
	}

	public void setDesierto() {
		this.desierto = desierto -1;
	}

	

}
