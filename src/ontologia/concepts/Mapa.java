package src.ontologia.concepts;

import src.Jugador.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.Mapa.Casilla;
import src.Mapa.Edge;
import src.Mapa.Node;

public class Mapa {
	private int id;
	private List<Node> nodos;
	private List<Edge> edges = new ArrayList<>();
	private List<Casilla> casillas;
	public int arcilla ;
	public int lana ;
	public int piedra;
	public int paja ;
	public int madera ;
	public int desierto;
	public List<Integer> valores;
	public List<Jugador> turnos; 
	public int dados;
	

	public Mapa() {
		this.arcilla = 3;
		this.lana = 4;
		this.piedra = 3;
		this.paja = 4;
		this.madera = 4;
		this.desierto = 1;
		Random rand1 =  new Random();
		this.id=rand1.nextInt(123123123);
		crearValores();
		crearTablero();
		iniciarTablero();
		ManejarCasillas();
		IniciarNodos();
	}
	
	private void ManejarCasillas() {
		 List<Node> nodos;
		 List<Edge> edges;
		for (int i= 0; i<this.casillas.size();i++) {
			nodos=this.casillas.get(i).getAdyacentes();
			for (int j= 0; j<nodos.size();j++) {
				edges=nodos.get(j).getEdges();
				for (int z=0; z<edges.size();z++) {
					
					Node nodoDestino=edges.get(z).getDestino();
					for(int t = 0; t< nodos.size();t++) {
						if (nodoDestino.equalspos(nodos.get(t).getPos_x(), nodos.get(t).getPos_y())) {
							edges.get(z).getCasillas().add(this.casillas.get(i));
						}
					}
					
				}
			}
		}		
	}

	public Node fichaInicial(int posicion, Jugador due�o) {
		
		List<Node> listanodos;
		List<Casilla> listacasillas = this.casillas;
		Casilla casilla = listacasillas.get(posicion);
		listanodos = casilla.getAdyacentes();
		Recurso recurso = casilla.getRecurso();
		String recursos = recurso.getTipo();

		if (recursos.equals("Agua")) {
			return null;}
		else if (recursos.equals("Desierto")) {return null;}
			else {
				for(int i = 0; i < listanodos.size();i++) {
					////cuidado esta linea es un poco trampa y puede dar fallos si se repite el numero
					Random rand1 =  new Random();
					int arista = rand1.nextInt(listanodos.size());
					if (!listanodos.get(arista).isConstruccion()) {
						listanodos.get(arista).setConstruccion(true);
						listanodos.get(arista).setTipo("Poblado");
						listanodos.get(arista).setDue�o(due�o);
						System.out.println(due�o.getNombre()+" coloca la construccion");
						due�o.getCartas().setRecurso(casilla.getRecurso());
						//System.out.println(due�o.getCartas().getTipoRecurso(recursos));
						
						return listanodos.get(arista);
				
					}
				}
		}
		
		
		
		return null;
		
	}
	
	public void IniciarNodos() {
        for (int i = 0; i < this.getCasillas().size(); i++) {
            for (int j = 0; j < this.getCasillas().get(i).getAdyacentes().size(); j++) {
                    Node nodo = this.getCasillas().get(i).getAdyacentes().get(j);
                    nodo = new Node();
            }
        }
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean caminoInicial(Node nodo, Jugador due�o) {
		boolean posible= false;
		List<Edge> listaAristas=nodo.getEdges();
		for(int i = 0; i < listaAristas.size();i++) {
			if (listaAristas.get(i).posible()) {
				/*listaAristas.get(i).setCarretera(true);
				listaAristas.get(i).setDue�o(due�o);*/
				posible = true;
				System.out.println("He colocado un camino desde x: "+listaAristas.get(i).getOrigen().getPos_x()+ " y: "+
						listaAristas.get(i).getOrigen().getPos_y()+" a "+ " x: "+listaAristas.get(i).getDestino().getPos_x()+" y: "+listaAristas.get(i).getDestino().getPos_y());
				
				break;
				}
			
		}
		
		return posible;
	}
	
	public void crearTablero() {
		String posicion = null;
		int nivel = 0;
		int cantidad = 4;
		int node_counter=0;
		nodos = new ArrayList<>();
		int pos_x=0;
		int pos_y=0;
		
		//no sabe que casillas son adyacentes a el (aunque se puede hacer con un metodo)
		
		
		for (nivel=0; nivel<16; nivel++) {
			pos_x=nivel;
			if (nivel<8) {
				int parcial=0;
				for (int i=0; i<cantidad; i++) {
					pos_y=i;
					Node nodo = new Node(pos_x,pos_y);
					nodos.add(nodo);
					node_counter++;
					parcial++;
				}
				if (nivel%2==0) {cantidad++;}			
			}
			else {
				int parcial=0;
				for (int i=0; i<cantidad; i++) {
					pos_y=i;
					Node nodo = new Node(pos_x,pos_y);
					nodos.add(nodo);
					node_counter++;
					parcial++;
				}
				if (nivel%2==0) {cantidad--;}
			}
		}
		
		
		
		
	
		
		
		
		
		
		int contador_edge=0;
		cantidad = 4;
		for (nivel=0; nivel<15; nivel++) {
			if (nivel<8) {
				if (nivel%2==0) {
					for (int i= 0; i< cantidad; i++){
						Node nodo1=getNode(nivel, i);
						Node nodo2=getNode(nivel+1, i);
						Node nodo3=getNode(nivel+1, i+1);
						nodo1.addEdge(nodo2);
						nodo1.addEdge(nodo3);
						nodo2.addEdge(nodo1);
						nodo3.addEdge(nodo1);
						contador_edge++;
						contador_edge++;
						
					}
				}
				else {
					for (int i= 0; i< cantidad; i++){
					Node nodo1=getNode(nivel, i);
					Node nodo2=getNode(nivel+1, i);
					nodo1.addEdge(nodo2);
					nodo2.addEdge(nodo1);	
					contador_edge++;
					}
					
				}
				if (nivel%2==0) {cantidad++;}			
			}
			else {
				if (nivel%2==0) {
					for (int i=0; i<cantidad; i++) {
						if (i<cantidad-1) {
						Node nodo1=getNode(nivel, i);
						Node nodo2=getNode(nivel+1, i);
						nodo1.addEdge(nodo2);
						nodo2.addEdge(nodo1);
						contador_edge++;
						}
						if (i>0) {
							Node nodo1=getNode(nivel, i);
							Node nodo3=getNode(nivel+1, i-1); 
							nodo1.addEdge(nodo3);
							nodo3.addEdge(nodo1);
							contador_edge++;						
							
						}

					}
					if (nivel%2==0) {cantidad--;}
				}
				else {
					for (int i=0; i<cantidad; i++) {
					Node nodo1=getNode(nivel, i);
					Node nodo2=getNode(nivel+1, i); 
					nodo1.addEdge(nodo2);
					nodo2.addEdge(nodo1);
					contador_edge++;
					}
				}
			}
		}
		
		
		
		
		
	
		
		
		
	
	}

	public int getPosicionLadron() {
		int casillaLadron=-1;
		for (int i =0; i<getCasillas().size();i++) {
			
				if (getCasillas().get(i).isLadron())casillaLadron=i+1;
			}
		
		return casillaLadron;
	
		
	}
	
	public void ColocarPoblado(int posx, int posy, Jugador due�o) {
        Node poblado = this.getNode(posx, posy);
        poblado.setConstruccion(true);
        poblado.setTipo("Poblado");
        poblado.setDue�o(due�o);
    }

    public void ColocarCiudad(int posx, int posy, Jugador due�o) {
        Node poblado = this.getNode(posx, posy);
        poblado.setConstruccion(true);
        poblado.setTipo("Ciudad");
        poblado.setDue�o(due�o);
    }

    public void ColocarCarretera(Edge edge, Jugador due�o) {
        List<Edge> edges = this.getEdges();

        for (int i = 0; i < edges.size(); i++) {

            if(edges.get(i).getOrigen() == edge.getOrigen() && edges.get(i).getDestino() == edge.getDestino()) {
                edges.get(i).setCarretera(true);
                edges.get(i).setDue�o(due�o);
            }
        }
    }
	
	
	public void printMapa() {
		System.out.println("///////////////////////////////////////////////////////////////Este es EL MAPA DE DORA");
		System.out.println("ID: "+this.id);
		for (int i = 0 ; i < this.getCasillas().size() ;i++) {
		System.out.println("Casilla: "+ (i+1));
		System.out.println(this.getCasillas().get(i).getRecurso().getTipo());
		if (this.getCasillas().get(i).getValor()!= 0)System.out.println(this.getCasillas().get(i).getValor());
		this.getCasillas().get(i).getAdyacentesString();
		}
	}
	

	public void iniciarTablero() {
		int casillas_counter=0;
		int nivel = 0;
		int cantidad = 4;
		for(nivel=0;nivel<14;nivel++) {
			if(nivel%2==0 ) {
				for(int j=0;j<cantidad;j++) {
			
					if(nivel<8) {
							Node n1=getNode(nivel, j);
							Node n2=getNode(nivel+1, j+1);
							Node n3=getNode(nivel+2, j+1);
							Node n4=getNode(nivel+3, j+1);
							Node n5=getNode(nivel+2, j);
							Node n6=getNode(nivel+1, j);
							Casilla casilla = new Casilla();
							casilla.crearAdyacentes(n1, n2, n3, n4, n5, n6);
							casilla.setCasilla(this,valores);
							addCasilla(casilla);		
							
							
							
							casillas_counter++;
							
							
						}
					
					else {
						if (j>0 && j < cantidad-1) {
							Node n1=getNode(nivel, j);
							Node n2=getNode(nivel+1, j);
							Node n3=getNode(nivel+2, j);
							Node n4=getNode(nivel+3, j-1);
							Node n5=getNode(nivel+2, j-1);
							Node n6=getNode(nivel+1, j-1);
							Casilla casilla = new Casilla();
							casilla.crearAdyacentes(n1, n2, n3, n4, n5, n6);
							casilla.setCasilla(this,valores);
							addCasilla(casilla);
							casillas_counter++;
							//System.out.println("OUT!!!!");
							
						}
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
	public Node getNode(int posx, int posy) {
		Node nodo= new Node();
		for (int i = 0; i < this.nodos.size();i++) 
		{
			if(this.nodos.get(i).equalspos(posx,posy)) {nodo=this.nodos.get(i); break;};
		}
		return nodo;
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
	public void DisminuirCasillasArcilla() {
		this.arcilla = arcilla -1;
	}
	
	public int getArcilla() {
		return arcilla;
	}

	public int getLana() {
		return lana;
	}

	public void DisminuirCasillasLana() {
		this.lana = lana -1;
	}

	public int getPiedra() {
		return piedra;
	}

	public void DisminuirCasillasPiedra() {
		this.piedra = piedra -1;
	}

	public int getPaja() {
		return paja;
	}

	public void DisminuirCasillasPaja() {
		this.paja = paja -1;
	}

	public int getMadera() {
		return madera;
	}

	public void DisminuirCasillasMadera() {
		this.madera = madera -1;
	}

	public int getDesierto() {
		return desierto;
	}

	public void DisminuirCasillasDesierto() {
		this.desierto = desierto -1;
	}

	public void setDesierto(int desierto) {
		this.desierto = desierto;
	}

	public List<Node> getNodos() {
		return nodos;
	}

	public void setNodos(List<Node> nodos) {
		this.nodos = nodos;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public List<Integer> getValores() {
		return valores;
	}

	public void setValores(List<Integer> valores) {
		this.valores = valores;
	}

	public List<Jugador> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Jugador> turnos) {
		this.turnos = turnos;
	}

	public int getDados() {
		return dados;
	}

	public void setDados(int dados) {
		this.dados = dados;
	}

	public void setCasillas(List<Casilla> casillas) {
		this.casillas = casillas;
	}

	public void setArcilla(int arcilla) {
		this.arcilla = arcilla;
	}

	public void setLana(int lana) {
		this.lana = lana;
	}

	public void setPiedra(int piedra) {
		this.piedra = piedra;
	}

	public void setPaja(int paja) {
		this.paja = paja;
	}

	public void setMadera(int madera) {
		this.madera = madera;
	}


	

}
