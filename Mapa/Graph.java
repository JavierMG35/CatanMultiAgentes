package Mapa;
import java.util.ArrayList;
import java.util.List;
public class Graph {
	private List<Node> nodos;
	private List<Edge> edges;
	private List<Casilla> casillas;
	public int arcilla ;
	public int lana ;
	public int piedra;
	public int paja ;
	public int madera ;
	public int desierto;
	public List<Integer> valores;
	
	public Graph() {
		this.arcilla = 3;
		this.lana = 4;
		this.piedra = 3;
		this.paja = 4;
		this.madera = 4;
		this.desierto = 1;
		crearValores();
	}
	
	public void crearMapa(Graph mapa) {
		String posicion;
		int nivel = 0;
		int cantidad = 4;
		
		//no sabe que casillas son adyacentes a el (aunque se puede hacer con un metodo)
		
		for(nivel=0; nivel<16;nivel++) {
			if(nivel<8) {
			for (int j=0;j<cantidad;j++) {
				if (nivel ==0) {
					posicion = Integer.toString(nivel) + Integer.toString(j);
					//System.out.print(posicion + " ");
					Node nodo = new Node(posicion);
					posicion = Integer.toString(nivel+1) + Integer.toString(j);
					//System.out.print(posicion + " ");
					Node nodo1 = new Node(posicion);
					posicion = Integer.toString(nivel+1) + Integer.toString(j+1);
					//System.out.println(posicion);
					Node nodo2 = new Node(posicion);
					Edge edge1 = new Edge(nodo, nodo1);
					Edge edge2 = new Edge(nodo, nodo2);
					mapa.addNode( nodo );
					nodo.addEdge(edge1);
					nodo.addEdge(edge2);
				}
				if (nivel%2== 0 && nivel !=0) {
					posicion = Integer.toString(nivel) + Integer.toString(j);
					//System.out.print(posicion + " ");
					Node nodo = new Node(posicion);
					posicion = Integer.toString(nivel-1) + Integer.toString(j);
					//System.out.print(posicion + " ");
					Node nodo1 = new Node(posicion);
					posicion = Integer.toString(nivel+1) + Integer.toString(j);
					//System.out.print(posicion + " ");
					Node nodo2 = new Node(posicion);
					posicion = Integer.toString(nivel+1) + Integer.toString(j+1);
					//System.out.println(posicion + " ");
					Node nodo3 = new Node(posicion);
					Edge edge1 = new Edge(nodo, nodo1);
					Edge edge2 = new Edge(nodo, nodo2);
					Edge edge3 = new Edge(nodo, nodo3);
					mapa.addNode(nodo);
					nodo.addEdge(edge1);
					nodo.addEdge(edge2);
					nodo.addEdge(edge3);
				}
				if (nivel%2!= 0) {
					if(j!=0 && j!=(cantidad-1)) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.print(posicion + " ");
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j-1);
						//System.out.print(posicion + " ");
						Node nodo1 = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						//System.out.print(posicion + " ");
						Node nodo2 = new Node(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j);
						//System.out.println(posicion + " ");
						Node nodo3 = new Node(posicion);
						Edge edge1 = new Edge(nodo, nodo1);
						Edge edge2 = new Edge(nodo, nodo2);
						Edge edge3 = new Edge(nodo, nodo3);
						mapa.addNode(nodo);
						nodo.addEdge(edge1);
						nodo.addEdge(edge2);
						nodo.addEdge(edge3);
					}
					if(j==0 ) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("B");
						//System.out.print(posicion + " ");
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						//System.out.print(posicion + " ");
						Node nodo1 = mapa.getNode(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j);
						//System.out.println(posicion + " ");
						Node nodo2 = mapa.getNode(posicion);
						Edge edge1 = new Edge(nodo, nodo1);
						Edge edge2 = new Edge(nodo, nodo2);
						mapa.addNode(nodo);
						nodo.addEdge(edge1);
						nodo.addEdge(edge2);
					}
					if( j==(cantidad-1)) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("C");
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j-1);
						Node nodo1 = mapa.getNode(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j);
						Node nodo2 = mapa.getNode(posicion);
						Edge edge1 = new Edge(nodo, nodo1);
						Edge edge2 = new Edge(nodo, nodo2);
						mapa.addNode(nodo);
						nodo.addEdge(edge1);
						nodo.addEdge(edge2);
					}
				}				
			}
			if(nivel%2== 0 || nivel ==0) {cantidad++;}
		}
		if(nivel>=8) {
			for (int j=0;j<cantidad;j++) {
				if (nivel%2== 0) {
					if(j!=0 && j!=(cantidad-1)) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						Node nodo1 = new Node(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j-1);
						Node nodo2 = new Node(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j);
						Node nodo3 = new Node(posicion);
						Edge edge1 = new Edge(nodo, nodo1);
						Edge edge2 = new Edge(nodo, nodo2);
						Edge edge3 = new Edge(nodo, nodo3);
						mapa.addNode(nodo);
						nodo.addEdge(edge1);
						nodo.addEdge(edge2);
						nodo.addEdge(edge3);
					}
					if(j==0 ) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("B");
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						Node nodo1 = mapa.getNode(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j);
						Node nodo2 = mapa.getNode(posicion);
						Edge edge1 = new Edge(nodo, nodo1);
						Edge edge2 = new Edge(nodo, nodo2);
						mapa.addNode(nodo);
						nodo.addEdge(edge1);
						nodo.addEdge(edge2);
					}
					if( j==(cantidad-1)) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("C");
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						Node nodo1 = mapa.getNode(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j-1);
						Node nodo2 = mapa.getNode(posicion);
						Edge edge1 = new Edge(nodo, nodo1);
						Edge edge2 = new Edge(nodo, nodo2);
						mapa.addNode(nodo);
						nodo.addEdge(edge1);
						nodo.addEdge(edge2);
					}
				}
				if (nivel%2!= 0 && nivel!=15) {	
					
					posicion = Integer.toString(nivel) + Integer.toString(j);
					//System.out.println("A");
					//System.out.println(posicion);
					Node nodo = new Node(posicion);
					posicion = Integer.toString(nivel-1) + Integer.toString(j);
					Node nodo1 = mapa.getNode(posicion);
					posicion = Integer.toString(nivel-1) + Integer.toString(j+1);
					Node nodo2 = mapa.getNode(posicion);
					posicion = Integer.toString(nivel+1) + Integer.toString(j);
					Node nodo3 = mapa.getNode(posicion);
					Edge edge1 = new Edge(nodo, nodo1);
					Edge edge2 = new Edge(nodo, nodo2);
					Edge edge3 = new Edge(nodo, nodo3);
					mapa.addNode(nodo);
					nodo.addEdge(edge1); 
					nodo.addEdge(edge2);
					nodo.addEdge(edge3);
				}
				if (nivel%2!= 0 && nivel==15) {	
					posicion = Integer.toString(nivel) + Integer.toString(j);
					//System.out.println("A");
					//System.out.print(posicion + " ");
					Node nodo = new Node(posicion);
					posicion = Integer.toString(nivel-1) + Integer.toString(j);
					//System.out.print(posicion + " ");
					Node nodo1 = mapa.getNode(posicion);
					posicion = Integer.toString(nivel-1) + Integer.toString(j+1);
					//System.out.println(posicion + " ");
					Node nodo2 = mapa.getNode(posicion);
					Edge edge1 = new Edge(nodo, nodo1);
					Edge edge2 = new Edge(nodo, nodo2);
					mapa.addNode(nodo);
					nodo.addEdge(edge1); 
					nodo.addEdge(edge2);
					
				}
			}
			if(nivel%2== 0) {cantidad--;}
		}
		}
		
	
	}

	public void iniciarMapa(Graph mapa) {
		int nivel = 0;
		int cantidad = 4;
		for(nivel=0;nivel<16;nivel++) {
			//System.out.println(nivel);
			for(int j=0;j<cantidad;j++) {
			if(nivel%2==0 && nivel!=14) {
				if(nivel>=8) {
					
					if(j!=0 && j!=(cantidad-1)) {
						String posicion1 = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println(posicion);
						Node n1= mapa.getNode(posicion1);
						String posicion2 = Integer.toString(nivel+1) + Integer.toString(j);
						//System.out.println(posicion);
						Node n2= mapa.getNode(posicion2);
						String posicion3 = Integer.toString(nivel+2) + Integer.toString(j);
						//System.out.println(posicion);
						Node n3= mapa.getNode(posicion3);
						String posicion4 = Integer.toString(nivel+3) + Integer.toString(j-1);
						//System.out.println(posicion);
						Node n4= mapa.getNode(posicion4);
						String posicion5 = Integer.toString(nivel+2) + Integer.toString(j-1);
						//System.out.println(posicion);
						Node n5= mapa.getNode(posicion5);
						String posicion6 = Integer.toString(nivel+1) + Integer.toString(j-1);
						//System.out.println(posicion);
						//System.out.println("   ");
						Node n6= mapa.getNode(posicion6);
						Casilla casilla = new Casilla();
						casilla.crearAdyacentes(n1, n2, n3, n4, n5, n6);
						casilla.setCasilla(mapa,valores);
						addCasilla(casilla);
						//System.out.println("OUT!!!!");
					}
					
				}
				else {
				String posicion1 = Integer.toString(nivel) + Integer.toString(j);
				//System.out.println(posicion1);
				Node n1= mapa.getNode(posicion1);
				String posicion2 = Integer.toString(nivel+1) + Integer.toString(j+1);
				//System.out.println(posicion2);
				Node n2= mapa.getNode(posicion2);
				String posicion3 = Integer.toString(nivel+2) + Integer.toString(j+1);
				//System.out.println(posicion);
				Node n3= mapa.getNode(posicion3);
				String posicion4 = Integer.toString(nivel+3) + Integer.toString(j+1);
				//System.out.println(posicion);
				Node n4= mapa.getNode(posicion4);
				String posicion5 = Integer.toString(nivel+2) + Integer.toString(j);
				//System.out.println(posicion);
				Node n5= mapa.getNode(posicion5);
				String posicion6 = Integer.toString(nivel+1) + Integer.toString(j);
				//System.out.println(posicion);
				//System.out.println("   ");
				Node n6= mapa.getNode(posicion6);
				Casilla casilla = new Casilla();
				casilla.crearAdyacentes(n1, n2, n3, n4, n5, n6);
				casilla.setCasilla(mapa,valores);
				addCasilla(casilla);
				//System.out.println("OUT!!!!");
				}
			}
			}
			if(nivel<8) {if(nivel%2==0) {cantidad++;}}
			else {if(nivel%2==0) {cantidad--;}}
		}
		
	}

	
	
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
	
	public void addCasilla(Casilla casilla) {
		if (casillas ==null) {
			casillas = new ArrayList<>();
		}
		casillas.add(casilla);
	}
	
	public List<Casilla> getCasillas() {
		return casillas;
		
	}

	public void crearValores() {
		valores = new ArrayList<>();
		valores.add(2);
		valores.add(12);
		valores.add(3);
		valores.add(3);
		valores.add(4);
		valores.add(4);
		valores.add(5);
		valores.add(5);
		valores.add(6);
		valores.add(6);
		valores.add(8);
		valores.add(8);
		valores.add(9);
		valores.add(9);
		valores.add(10);
		valores.add(10);
		valores.add(11);
		valores.add(11);
		
	}
	
	//Getters and Setters
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
