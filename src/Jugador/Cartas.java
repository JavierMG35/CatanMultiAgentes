package src.Jugador;

import java.util.*;
import src.Mapa.Edge;
import src.ontologia.concepts.Recurso;


public class Cartas {
	
	protected List<Recurso> lana;
	protected List<Recurso> madera;
	protected List<Recurso> arcilla;
	protected List<Recurso> piedra;
	protected List<Recurso> paja;
	protected List<Recurso> cartas_desarrollo;
	public Cartas() {
		
	}
	
	public void aņadirRecurso(Recurso recurso) {
		
		switch(recurso.getTipo()) {
		  case "Lana":
			addLana(recurso);
			System.out.println(recurso.getTipo());
		    break;
		  case "Madera":
			  addMadera(recurso);
			  System.out.println(recurso.getTipo());
		    break;
		  case "Piedra":
			  addPiedra(recurso);
			  System.out.println(recurso.getTipo());
			break;
		  case "Arcilla":
			  addArcilla(recurso);
			  System.out.println(recurso.getTipo());
			break;
		  case  "Paja":
			  addPaja(recurso);
			  System.out.println(recurso.getTipo());
			break;
		} 
	}
	
	//public List getTipoRecurso()
	public List getLana() {
		return lana;
	}
	public void setLana(List lana) {
		this.lana = lana;
	}
	public List getMadera() {
		return madera;
	}
	public void setMadera(List madera) {
		this.madera = madera;
	}
	public List getArcilla() {
		return arcilla;
	}
	public void setArcilla(List arcilla) {
		this.arcilla = arcilla;
	}
	public List getPiedra() {
		return piedra;
	}
	public void setPiedra(List piedra) {
		this.piedra = piedra;
	}
	public List getPaja() {
		return paja;
	}
	public void setPaja(List paja) {
		this.paja = paja;
	}
	public List getCartas_desarrollo() {
		return cartas_desarrollo;
	}
	public void setCartas_desarrollo(List cartas_desarrollo) {
		this.cartas_desarrollo = cartas_desarrollo;
	}

	public void addLana(Recurso lanas) {
		if(lana ==null) {
			lana = new ArrayList<>();
		}
		this.lana.add(lanas);
	}
	
	public void addMadera(Recurso maderas) {
		if(madera ==null) {
			madera = new ArrayList<>();
		}
		this.madera.add(maderas);
	}
	
	public void addArcilla(Recurso arcillas) {
		if(arcilla ==null) {
			arcilla = new ArrayList<>();
		}
		this.arcilla.add(arcillas);
	}
	
	public void addPiedra(Recurso piedras) {
		if(piedra ==null) {
			piedra = new ArrayList<>();
		}
		this.piedra.add(piedras);
	}
	public void addPaja(Recurso pajas) {
		if(paja ==null) {
			paja = new ArrayList<>();
		}
		this.paja.add(pajas);
	}
	
	

	
}
