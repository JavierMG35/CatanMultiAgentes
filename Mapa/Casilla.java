package Mapa;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Casilla {
	private List<Node> nodos_adyacentes;
	private List<Edge> edges_adyacentes;
	private int valor;
	private String recurso;
	private boolean ladron;
	
	public Casilla() {
		this.ladron = false;
	}
	
	public void crearAdyacentes(Node n1,Node n2,Node n3,Node n4,Node n5,Node n6) {
		if (nodos_adyacentes ==null) {
			nodos_adyacentes = new ArrayList<>();
		}
		nodos_adyacentes.add(n1);
		nodos_adyacentes.add(n2);
		nodos_adyacentes.add(n3);
		nodos_adyacentes.add(n4);
		nodos_adyacentes.add(n5);
		nodos_adyacentes.add(n6);
	}
	
	public List<Node> getAdyacentes(){
		return nodos_adyacentes;
	}
	
	public void setCasilla(Graph mapa) {
		Node nodo = new Node("00");
		boolean set = false;
		while(set==false) {
			if(nodos_adyacentes.get(0).getPosicion().equals("63")) {this.recurso = "Desierto";set=true;}
		
			for(int i=0;i<nodos_adyacentes.size();i++) {
				nodo = nodos_adyacentes.get(i);
				
				if(nodo.getEdges().size()==2) {this.recurso = "Agua"; set = true;System.out.println("Agua");break;}
			if(set==false)	{
			setRecurso(mapa);	}
			}
		}
	}
	
	
	public void setDesierto() {
		this.recurso = "Desierto";
		
	}
	public void setRecurso(Graph mapa) {
		//AQUI SE ATASCA
		//el metodo getLana() (o cualquier recurso vaya) está devolviendo 0, cuando debería, al menos al principio, devovler 4. Está fallando el get
		//Debe de ser un tema de clases o algo...ni idea
		//Desde Casilla no accedo a las variables de Graph...jerarguia??permisos??
		//l = lana = 0
		//m = madera = 1
		//r = piedra = 2
		//a = arcilla = 3
		//p = paja = 4
		System.out.println("Otro Recurso");
		boolean hecho = false;
		while(!hecho) {
			System.out.println("while");
		Random random = new Random();
		int numero_random = random.nextInt(5);
		System.out.println(numero_random);
			switch(numero_random) {
			  case 0:
				  System.out.println("o");
				  System.out.println(mapa.getLana());
				if(mapa.getLana()!=0) {
					System.out.println("Lana");
			    this.recurso = "Lana";
			    mapa.setLana();
			    
			    hecho = true;}
			    break;
			  case 1:
				if(mapa.getMadera()!=0) {
			    this.recurso = "Madera";
			    mapa.setMadera();;
			    hecho = true;}
			    break;
			  case 2:
				if(mapa.getPiedra()!=0) {
				this.recurso = "Piedra";
				mapa.setPiedra();
				hecho = true;}
				break;
			  case 3:
				if(mapa.getArcilla()!=0) {
				this.recurso = "Arcilla";
				mapa.setArcilla();	
				hecho = true;}
				break;
			  case 4:
				if(mapa.getPaja()!=0) {
				this.recurso = "Paja";
				mapa.setPaja();
				hecho = true;}
				break;
			} 
		}
	}
		

}