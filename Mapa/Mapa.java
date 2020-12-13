package Mapa;

public class Mapa {
	
	
	public static void main(String[] args) {
		Graph mapa = crearMapa();
		Casilla casilla_central = crear_casilla_central();
		iniciarMapa(mapa,casilla_central);
	}

	public static Graph crearMapa() {
		Graph mapa = new Graph();
		String posicion;
		//int num_nodo = 0;
		int nivel = 0;
		int cantidad = 4;
		//Cosas a cambiar, o no:
		//Cada nodo no sabe cuantos vertices (edges)tiene adyacentes . Esto es así por como he hecho el algoritmo de este metodo (crearMapa()). Si quisieramos saber cuantos vertices adyacentes tiene un nodo y es útil para el Catán, cambiamos este metodo (se puede anyways)
		//Aunque veais que he puesto nodo.addEdge(), no esta sumando todos los edges en realidad. Esto lo he hecho porque me servia para iniciar las casillas de agua, desierto y recursos
		//no sabe que casillas son adyacentes a el (aunque se puede hacer con un metodo)
		
		for(nivel=0; nivel<16;nivel++) {
			if(nivel<8) {
			for (int j=0;j<cantidad;j++) {
				if (nivel ==0) {
					posicion = Integer.toString(nivel) + Integer.toString(j);
					//System.out.println(posicion);
					Node nodo = new Node(posicion);
					mapa.addNode( nodo );
				}
				if (nivel%2== 0 && nivel !=0) {
					posicion = Integer.toString(nivel) + Integer.toString(j);
					//System.out.println(posicion);
					Node nodo = new Node(posicion);
					posicion = Integer.toString(nivel-1) + Integer.toString(j);
					Node nodo1 = new Node(posicion);
					Edge edge11 = new Edge(nodo1, nodo);
					Edge edge12 = new Edge(nodo, nodo1);
					mapa.addNode(nodo);
					nodo.addEdge(edge12);
					mapa.addEdge(edge11);
					mapa.addEdge(edge12);
				}
				if (nivel%2!= 0) {
					if(j!=0 && j!=(cantidad-1)) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("A");
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						Node nodo1 = mapa.getNode(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j-1);
						Node nodo2 = mapa.getNode(posicion);
						Edge edge11 = new Edge(nodo1, nodo);
						Edge edge12 = new Edge(nodo, nodo1);
						Edge edge21 = new Edge(nodo2, nodo);
						Edge edge22 = new Edge(nodo, nodo2);
						mapa.addNode(nodo);
						nodo.addEdge(edge12);
						nodo.addEdge(edge22);
						mapa.addEdge(edge11);
						mapa.addEdge(edge12);
						mapa.addEdge(edge21);
						mapa.addEdge(edge22);
					}
					if(j==0 ) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("B");
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j);
						Node nodo1 = mapa.getNode(posicion);
						Edge edge11 = new Edge(nodo1, nodo);
						Edge edge12 = new Edge(nodo, nodo1);
						mapa.addNode(nodo);
						mapa.addEdge(edge11);
						mapa.addEdge(edge12);
						nodo.addEdge(edge12);
					}
					if( j==(cantidad-1)) {
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println("C");
						//System.out.println(posicion);
						Node nodo = new Node(posicion);
						posicion = Integer.toString(nivel-1) + Integer.toString(j-1);
						Node nodo1 = mapa.getNode(posicion);
						Edge edge11 = new Edge(nodo1, nodo);
						Edge edge12 = new Edge(nodo, nodo1);
						mapa.addNode(nodo);
						mapa.addEdge(edge11);
						mapa.addEdge(edge12);
						nodo.addEdge(edge12);
					}
				}				
			}
			if(nivel%2== 0 || nivel ==0) {cantidad++;}
		}
		if(nivel>=8) {
			for (int j=0;j<cantidad;j++) {
				if (nivel%2==0) {
				posicion = Integer.toString(nivel) + Integer.toString(j);
				//System.out.println(posicion);
				Node nodo = new Node(posicion);
				posicion = Integer.toString(nivel-1) + Integer.toString(j);
				Node nodo1 = new Node(posicion);
				Edge edge11 = new Edge(nodo1, nodo);
				Edge edge12 = new Edge(nodo, nodo1);
				mapa.addNode(nodo);
				mapa.addEdge(edge11);
				mapa.addEdge(edge12);
				nodo.addEdge(edge12);
				}
				if (nivel%2!= 0) {	
				posicion = Integer.toString(nivel) + Integer.toString(j);
				//System.out.println("A");
				//System.out.println(posicion);
				Node nodo = new Node(posicion);
				posicion = Integer.toString(nivel-1) + Integer.toString(j);
				Node nodo1 = mapa.getNode(posicion);
				posicion = Integer.toString(nivel-1) + Integer.toString(j-1);
				Node nodo2 = mapa.getNode(posicion);
				Edge edge11 = new Edge(nodo1, nodo);
				Edge edge12 = new Edge(nodo, nodo1);
				Edge edge21 = new Edge(nodo2, nodo);
				Edge edge22 = new Edge(nodo, nodo2);
				mapa.addNode(nodo);
				mapa.addEdge(edge11);
				mapa.addEdge(edge12);
				mapa.addEdge(edge21);
				mapa.addEdge(edge22);
				nodo.addEdge(edge12);
				nodo.addEdge(edge22);
					
 
				}
			}
			if(nivel%2== 0) {cantidad--;}
		}
		}
		
	return mapa;
	}

	public static void iniciarMapa(Graph mapa, Casilla casilla_central) {
		//definir casilla central
		int nivel = 0;
		int cantidad = 4;
		String posicion;
		for(nivel=0;nivel<16;nivel++) {
			for(int j=0;j<cantidad;j++) {
			if(nivel%2==0 && nivel!=14) {
				if(nivel>=8) {
					if(j!=0 && j!=(cantidad-1)) {
						
						//Falla: Como estoy creando un objeto Nodo nuevo para las adyacentes de las casillas, no son los nodos originales del mapa. 
						//Por tanto, los nodos que tiene guardada cada casilla son nuevos, y no son los mismos a los que tenemos realmente en el mapa.
						//Para ello habría que cambiar las entradas de .crearAdyacentes() y meter directamente el objeto. Para ello hay que cambiar como busca las posiciones
						//Ahora mismo no compila por este motivo
						posicion = Integer.toString(nivel) + Integer.toString(j);
						//System.out.println(posicion);
						Node n1= mapa.getNode(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j);
						//System.out.println(posicion);
						Node n2= mapa.getNode(posicion);
						posicion = Integer.toString(nivel+2) + Integer.toString(j);
						//System.out.println(posicion);
						Node n3= mapa.getNode(posicion);
						posicion = Integer.toString(nivel+3) + Integer.toString(j-1);
						//System.out.println(posicion);
						Node n4= mapa.getNode(posicion);
						posicion = Integer.toString(nivel+2) + Integer.toString(j-1);
						//System.out.println(posicion);
						Node n5= mapa.getNode(posicion);
						posicion = Integer.toString(nivel+1) + Integer.toString(j-1);
						//System.out.println(posicion);
						//System.out.println("   ");
						Node n6= mapa.getNode(posicion);
						Casilla casilla = new Casilla();
						casilla.crearAdyacentes(n1, n2, n3, n4, n5, n6);
						casilla.setCasilla(mapa);
					}else {}
					
				}
				else {
				posicion = Integer.toString(nivel) + Integer.toString(j);
				//System.out.println(posicion);
				Node n1= mapa.getNode(posicion);
				posicion = Integer.toString(nivel+1) + Integer.toString(j+1);
				//System.out.println(posicion);
				Node n2= mapa.getNode(posicion);
				posicion = Integer.toString(nivel+2) + Integer.toString(j+1);
				//System.out.println(posicion);
				Node n3= mapa.getNode(posicion);
				posicion = Integer.toString(nivel+3) + Integer.toString(j+1);
				//System.out.println(posicion);
				Node n4= mapa.getNode(posicion);
				posicion = Integer.toString(nivel+2) + Integer.toString(j);
				//System.out.println(posicion);
				Node n5= mapa.getNode(posicion);
				posicion = Integer.toString(nivel+1) + Integer.toString(j);
				//System.out.println(posicion);
				//System.out.println("   ");
				Node n6= mapa.getNode(posicion);
				Casilla casilla = new Casilla();
				casilla.crearAdyacentes(n1, n2, n3, n4, n5, n6);
				casilla.setCasilla(mapa);
				}
			}
			else {}
			}
			if(nivel<8) { if(nivel%2==0) {cantidad++;}}
			else {if(nivel%2==0) {cantidad--;}}
		}
		
	}

	public static Casilla crear_casilla_central() {
		Node n1 = new Node("63");
		Node n2 = new Node("74");
		Node n3 = new Node("84");
		Node n4 = new Node("93");
		Node n5 = new Node("83");
		Node n6 = new Node("73");
		Casilla casilla_central = new Casilla();
		casilla_central.crearAdyacentes(n1,n2,n3,n4,n5,n6);
		return casilla_central;
	}
}
