package src.Mapa;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.Mapa.Node;
import src.ontologia.concepts.Mapa;
import src.ontologia.concepts.Recurso;
public class Casilla {
	private List<Node> nodos_adyacentes;
	private List<Edge> edges_adyacentes;
	private int valor;
	private Recurso recurso;
	private boolean ladron;
	
	public Casilla() {
		this.ladron = false;
		this.recurso = new Recurso();
		
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
	
	public void getAdyacentesString() {
		if (this.ladron) System.out.println("Aqui esta el ladrón");
		for (int i =0; i<this.nodos_adyacentes.size();i++) {
			System.out.println("Nodo adyacente : "+nodos_adyacentes.get(i).getPos_x()+nodos_adyacentes.get(i).getPos_y());
			if (this.nodos_adyacentes.get(i).getDueño() != null) {System.out.println("ocupado por : "+this.nodos_adyacentes.get(i).getDueño().getNombre() );}
			if (this.nodos_adyacentes.get(i).isOcupado()) {System.out.println("Esta ocupado en general");}
		}
	}
	
	public List<Node> getNodos_adyacentes() {
		return nodos_adyacentes;
	}

	public void setNodos_adyacentes(List<Node> nodos_adyacentes) {
		this.nodos_adyacentes = nodos_adyacentes;
	}

	public List<Node> getAdyacentes(){
		return nodos_adyacentes;
	}
	
	public void setCasilla(Mapa mapa, List<Integer> valores) {
		Node nodo = new Node();
		boolean set = false;
		while(!set) {
			//System.out.println("WHile setCasilla");
			if(nodos_adyacentes.get(0).getPos_x()==6 && nodos_adyacentes.get(0).getPos_y()==3) {
				this.recurso.setTipo("Desierto");set=true;
			//	System.out.println("desierto!!!!");
				}
		
			for(int i=0;i<nodos_adyacentes.size();i++) {
				nodo = nodos_adyacentes.get(i);
				if(nodo.getEdges().size()==2) {
					this.recurso.setTipo("Agua");
					set = true;
				//	System.out.println("Agua");
					//System.out.println(set);
					break;}
			}
			if(set==false)	{
				
				setRecurso(mapa);
				setValor(mapa,valores);
				}
			
			set = true;
		}
		
	}
	
	
	public void setValor(Mapa mapa, List<Integer> valores) {
		Random random = new Random();
		int numero_random = random.nextInt(valores.size());
		this.valor = Integer.parseInt(valores.get( Integer.valueOf(numero_random) ).toString());		
		valores.remove( numero_random);
		
	}
	
	
	
	public void setRecurso(Mapa mapa) {
		//l = lana = 0
		//m = madera = 1
		//r = piedra = 2
		//a = arcilla = 3
		//p = paja = 4
		//System.out.println("Otro Recurso");
		boolean hecho = false;
		do {
			//System.out.println("while de setRecurso");
		Random random = new Random();
		int numero_random = random.nextInt(5);
		//System.out.println(numero_random);
			switch(numero_random) {
			  case 0:
				if(mapa.getLana()>0) {
					this.recurso.setTipo("Lana");
					mapa.DisminuirCasillasLana();
					hecho = true;}
			    break;
			  case 1:
				if(mapa.getMadera()>0) {
					this.recurso.setTipo("Madera");
					mapa.DisminuirCasillasMadera();;
					hecho = true;}
			    break;
			  case 2:
				if(mapa.getPiedra()>0) {
					this.recurso.setTipo("Piedra");
					mapa.DisminuirCasillasPiedra();
					hecho = true;}
				break;
			  case 3:
				if(mapa.getArcilla()>0) {
					this.recurso.setTipo("Arcilla");
					mapa.DisminuirCasillasArcilla();	
					hecho = true;}
				break;
			  case 4:
				if(mapa.getPaja()>0) {
					this.recurso.setTipo("Paja");
					mapa.DisminuirCasillasPaja();
					hecho = true;}
				break;
			} 
		}
		while(hecho==false);
	}

	public List<Edge> getEdges_adyacentes() {
		return edges_adyacentes;
	}

	public void setEdges_adyacentes(List<Edge> edges_adyacentes) {
		this.edges_adyacentes = edges_adyacentes;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public boolean isLadron() {
		return ladron;
	}

	public void setLadron(boolean ladron) {
		this.ladron = ladron;
	}
		

}