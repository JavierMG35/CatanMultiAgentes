package src.ontologia.concepts;

import java.util.*;
import src.Mapa.Edge;


public class Cartas {
	
	protected List<Recurso> lana;
	protected List<Recurso> madera;
	protected List<Recurso> arcilla;
	protected List<Recurso> piedra;
	protected List<Recurso> paja;
	protected List<CartaDesarrollo> cartas_desarrollo;
	
	
	public Cartas() {
		lana = new ArrayList<>();
		madera = new ArrayList<>();
		arcilla = new ArrayList<>();
		piedra = new ArrayList<>();
		paja = new ArrayList<>();
		this.cartas_desarrollo = new ArrayList<>();
	}
	
	
	
	public void setRecurso(Recurso recurso) {
		
		switch(recurso.getTipo()) {
		  case "Lana":
			addLana(recurso);
		    break;
		  case "Madera":
			  addMadera(recurso);
		    break;
		  case "Piedra":
			  addPiedra(recurso);
			break;
		  case "Arcilla":
			  addArcilla(recurso);
			break;
		  case  "Paja":
			  addPaja(recurso);
			break;
		} 
	}
	
	public void removeRecurso(Recurso recurso) {
		 switch(recurso.getTipo()) {
	     case "Lana":
	       this.lana.remove(recurso);
	     case "Madera":
	         this.madera.remove(recurso);
	     case "Piedra":
	         this.piedra.remove(recurso);
	     case "Arcilla":
	          this.arcilla.remove(recurso);
	     case  "Paja":
	          this.paja.remove(recurso);
		 }
	   } 
	
	public List<Recurso> getRecurso(Recurso recurso) {

        switch(recurso.getTipo()) {
          case "Lana":
            return this.lana;

          case "Madera":
              return this.madera;
          case "Piedra":
              return this.piedra;
          case "Arcilla":
              return this.arcilla;
          case  "Paja":
              return this.paja;
        } 
        return null;
    }
	
	public void setLana(List<Recurso> lana) {
		this.lana = lana;
	}



	public void setMadera(List<Recurso> madera) {
		this.madera = madera;
	}



	public void setArcilla(List<Recurso> arcilla) {
		this.arcilla = arcilla;
	}



	public void setPiedra(List<Recurso> piedra) {
		this.piedra = piedra;
	}



	public void setPaja(List<Recurso> paja) {
		this.paja = paja;
	}



	public void setCartas_desarrollo(List<CartaDesarrollo> cartas_desarrollo) {
		this.cartas_desarrollo = cartas_desarrollo;
	}



	//public List getTipoRecurso()
	public List getLana() {
		return lana;
	}
	
	public List getMadera() {
		return madera;
	}
	
	public List getArcilla() {
		return arcilla;
	}
	
	public List getPiedra() {
		return piedra;
	}
	
	public List getPaja() {
		return paja;
	}
	
	public List getCartas_desarrollo() {
		return cartas_desarrollo;
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
	public void addDesarrollo(CartaDesarrollo desarrollo) {
		if(this.cartas_desarrollo ==null) {
			cartas_desarrollo = new ArrayList<>();
		}
		this.cartas_desarrollo.add(desarrollo);
	}
	public void printCartas() {
		System.out.println("Mis cartas son: ");
		System.out.println("Arcilla: "+getArcilla().size());
		System.out.println("Madera: "+getMadera().size());
		System.out.println("Paja: "+getPaja().size());
		System.out.println("Lana: "+getLana().size());
		System.out.println("Piedra: "+getPiedra().size());
		
	}

	
}
