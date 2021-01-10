package src.Mapa;
import java.util.ArrayList;
import src.Jugador.*;
import java.util.List;

import src.Mapa.Edge;
import src.Mapa.Node;


public class Node {
	private boolean construccion;
	private String tipo;
	private Jugador dueño;
	private List<Edge> edges= new ArrayList<>();
	private int pos_x;
	private int pos_y;
	
	public Node(){
	}
	public Node(int pos_x,int pos_y){
		this.pos_x=pos_x;
		this.pos_y=pos_y;
	}
	
	//Edges en node o graph?? Mejor aqui, no??
	public void addEdge(Edge edge) {
		if(edges ==null) {
			edges = new ArrayList<>();
		}
		edges.add(edge);
	}
	
	
	public void addEdge(Node destino) {
		Edge edge = new Edge(this,destino);
		this.edges.add(edge);
	}
	
	public boolean equalspos(int posx, int posy) {
		if(this.pos_x == posx && this.pos_y==posy) {return true;}
		return false;
	}
	
	public int getPos_x() {
		return pos_x;
	}
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}
	public int getPos_y() {
		return pos_y;
	}
	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}
	public List<Edge> getEdges() {
		return this.edges;
	}
	
	public Edge getEdge(Node nodo) {
		Edge edge = null;
		for (int i = 0; i < nodo.edges.size();i++) {
			if (nodo.edges.get(i).getDestino().getPos_x()==this.pos_x && nodo.edges.get(i).getDestino().getPos_y()==this.pos_y){
				edge=  nodo.edges.get(i);
			} 
		}
		return edge;
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

