package src.Jugador.estrategias;

import java.util.List;

import src.Jugador.Jugador;
import src.Mapa.Node;
import src.Mapa.Casilla;
import src.Mapa.Edge;
import src.ontologia.actions.RealizarOferta;
import src.ontologia.concepts.*;
//import java.util.List;
import java.util.ArrayList;

public class GranRutaComeracialEstrategia extends AbstractEstrategias {

	public GranRutaComeracialEstrategia() {
		// TODO Auto-generated constructor stub
	}
	public GranRutaComeracialEstrategia(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Recurso propuestaNegociarJugadorRecibir(Jugador yo) {
		Cartas cartas = yo.getCartas();
		boolean necesito_arcilla = false;
		boolean necesito_madera = false;
		List<Cartas> lana = cartas.getLana();
		List<Cartas> madera = cartas.getMadera();
		List<Cartas> piedra = cartas.getPiedra();
		List<Cartas> paja = cartas.getPaja();
		List<Cartas> arcilla = cartas.getArcilla();
		int num_lana = lana.size();
		int num_madera = madera.size();
		int num_piedra = piedra.size();
		int num_paja = paja.size();
		int num_arcilla = arcilla.size(); 
		if(num_arcilla == 0) {necesito_arcilla = true;
			Recurso arcilla_recurso = new Recurso();
			arcilla_recurso.setTipo("Arcilla");
			return arcilla_recurso; }
		if(num_madera == 0) {necesito_madera = true;
		Recurso madera_recurso = new Recurso();
		madera_recurso.setTipo("Madera");
		return madera_recurso; }
		
		return null;
	}
	
	public  List<Recurso> propuestaNegociarJugadorOfrecer(Jugador yo) {
		
		Cartas cartas = yo.getCartas();
		List<Cartas> lana = cartas.getLana();
		List<Cartas> madera = cartas.getMadera();
		List<Cartas> piedra = cartas.getPiedra();
		List<Cartas> paja = cartas.getPaja();
		List<Cartas> arcilla = cartas.getArcilla();
		int num_lana = lana.size();
		//int num_madera = madera.size();
		int num_piedra = piedra.size();
		int num_paja = paja.size();
		///int num_arcilla = arcilla.size(); 
		int[] numero_cada_recurso = new int[]{num_lana,num_piedra,num_paja }; 
		List<Recurso> lista_recibir = new ArrayList<>();
		Recurso max_recurso = new Recurso();
		int maximo = 0;
		for(int i=0;i<numero_cada_recurso.length;i++) {
			if(numero_cada_recurso[i]>maximo) {maximo = numero_cada_recurso[i];}
		}
		
			//if(cartas.getArcilla().size()==maximo) {max_recurso.setTipo("Arcilla"); lista_recibir.add(max_recurso); return lista_recibir;}
			if(cartas.getPaja().size()==maximo) {max_recurso.setTipo("Paja");lista_recibir.add(max_recurso);return lista_recibir;}
			if(cartas.getPiedra().size()==maximo) {max_recurso.setTipo("Piedra");lista_recibir.add(max_recurso);return lista_recibir;}
			//if(cartas.getMadera().size()==maximo) {max_recurso.setTipo("Madera");lista_recibir.add(max_recurso);return lista_recibir;}
			if(cartas.getLana().size()==maximo) {max_recurso.setTipo("Lana");lista_recibir.add(max_recurso);return lista_recibir;}
		
		return null;
	}

	@Override
	public Recurso[] propuestaNegociarBanca(Cartas cartas, Mapa mapa,Jugador yo) {
		Recurso[] Recursos = {new Recurso(),new Recurso()};
		Recurso recursoposible=null;
		recursoposible=yo.cuatroCartasIguales("Paja");
		recursoposible=yo.cuatroCartasIguales("Lana");
		recursoposible=yo.cuatroCartasIguales("Piedra");
		if (recursoposible.getTipo()!=null) {
			Recursos[1]=recursoposible;
			List<Integer> ints = List.of(cartas.getMadera().size(),cartas.getArcilla().size());
			int min=10;
			int index=2;
			for (int i = 0; i<ints.size() ;i++) {
				if (ints.get(i)<min) {
					min = ints.get(i);
					index=i;
					}
				}
			switch (index) {
			case 0:
				Recursos[0]=new Recurso("Madera");
			case 1:
				Recursos[0]= new Recurso ("Arcilla");
			}
			return Recursos;
			
		}
		else {return null;}
		
	}
	
	@Override
	public boolean aceptarOferta(RealizarOferta oferta, Cartas mis_cartas) {
		boolean acepto = false;
		List<Recurso> oferta_recibir_recursos = oferta.getTe_doy();
		Recurso oferta_dar_recursos = oferta.getMe_das();
		String tipo_recurso_recibir = oferta_recibir_recursos.get(0).getTipo();
		String tipo_recurso_dar = oferta_dar_recursos.getTipo();
		if(tipo_recurso_recibir.equals("Arcilla")||tipo_recurso_recibir.equals("Madera")) {
			if(tipo_recurso_recibir.equals("Madera") && mis_cartas.getMadera().size()<2) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==0) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>2 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Arcilla") ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()<=2 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Arcilla")) {acepto = false; return acepto;}
				if(!mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Madera") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Arcilla") && mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {acepto = true; return acepto;}


			}
			if(tipo_recurso_recibir.equals("Arcilla") && mis_cartas.getArcilla().size()<2) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==0) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Madera") && mis_cartas.getRecurso(oferta_dar_recursos).size()<4 ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Madera")) {acepto = false; return acepto;}
				if(!mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Madera") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Arcilla") && mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {acepto = true; return acepto;}

			}
			else{acepto = false; return acepto;}
			
		}
		else {
			if(mis_cartas.getRecurso(oferta_recibir_recursos.get(0)).size()==0 && ( tipo_recurso_dar.equals("Arcilla")||tipo_recurso_dar.equals("Madera"))) {
				if(tipo_recurso_dar.equals("Arcilla") && mis_cartas.getRecurso(oferta_dar_recursos).size()>2) {acepto = true; return acepto;}
				if(tipo_recurso_dar.equals("Madera") && mis_cartas.getRecurso(oferta_dar_recursos).size()>2) {acepto = true; return acepto;}
				else {acepto = false; return acepto;}
			}
			if(mis_cartas.getRecurso(oferta_recibir_recursos.get(0)).size()==0 && ( !tipo_recurso_dar.equals("Arcilla")|| !tipo_recurso_dar.equals("Madera"))) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {	acepto = true; return acepto;		}
				else {acepto = false; return acepto;}
			}
			else {acepto = false; return acepto;}
		}
		
		acepto = (Boolean) null;
		return acepto;
	}


	@Override
	public Boolean decidirCompra() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Construccion decidirConstruccion(Mapa mapa, Cartas cartas, String nombre) {
		List<Casilla> casillas = mapa.getCasillas();
		Construccion retorno = null;

		// Queremos hacer carreteras primero
		if (cartas.getArcilla().size() >= 1 && cartas.getMadera().size() >= 1) {
			// Ya tenemos suficientes recursos para construir carretera

			for (int i = 0; i < casillas.size(); i++) {
				List<Node> nodos = casillas.get(i).getAdyacentes();

				for (int j = 0; j < nodos.size(); j++) {

					if (nodos.get(j).getDueño().getNombre().equals(nombre)) {
						List<Edge> caminos = nodos.get(j).getEdges();

						for (int k = 0; k < caminos.size(); k++) {

							if (!caminos.get(k).isCarretera() && cartas.getArcilla().size() >= 1
									&& cartas.getMadera().size() >= 1) {
								retorno = new Construccion("Carretera", null, caminos.get(k));
							}
						}
					}
				}
			}
		} // Poblado
		if (cartas.getArcilla().size() >= 1 && cartas.getMadera().size() >= 1 && cartas.getPaja().size() >= 1
				&& cartas.getLana().size() >= 1) {

			for (int i = 0; i < casillas.size(); i++) {
				List<Node> nodos = casillas.get(i).getAdyacentes();

				for (int j = 0; j < nodos.size(); j++) {

					if (!nodos.get(j).isOcupado()) {
						retorno = new Construccion("Poblado", nodos.get(j), null);
					}
				}
			}
		} // Ciudades.
		if (cartas.getPaja().size() >= 2 && cartas.getPiedra().size() >= 3) {
			for (int i = 0; i < casillas.size(); i++) {
				List<Node> nodos = casillas.get(i).getAdyacentes();

				for (int j = 0; j < nodos.size(); j++) {

					if (nodos.get(j).getDueño().getNombre().equals(nombre)) {
						
						if (nodos.get(j).getTipo().equals("Poblado")) {
							retorno = new Construccion("Ciudad", nodos.get(j), null);
						}
					}
				}
			}
		}
		return retorno;
	}
	
}
