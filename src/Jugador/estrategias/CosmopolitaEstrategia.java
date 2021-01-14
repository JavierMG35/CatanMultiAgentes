package src.Jugador.estrategias;

import java.util.ArrayList;
import java.util.List;


import src.ontologia.concepts.*;
import src.Jugador.Jugador;
import src.Mapa.Casilla;
import src.Mapa.Node;
import src.Mapa.Edge;
import src.ontologia.actions.RealizarOferta;


public class CosmopolitaEstrategia extends AbstractEstrategias{

	
	public CosmopolitaEstrategia() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	public CosmopolitaEstrategia(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Recurso> propuestaNegociarJugadorOfrecer(Jugador yo) {
		Cartas cartas = yo.getCartas();
		List<Construccion> construcciones = yo.getConstrucciones();
		int poblados = 0;
		int ciudades = 0;
		for(int i=0;i<construcciones.size();i++) {
			if(construcciones.get(i).getTipo().equals("Poblado")) {poblados++;}
			if(construcciones.get(i).getTipo().equals("Ciudad")) {ciudades++;}
		}
		int num_lana = cartas.getLana().size();
		int num_madera = cartas.getMadera().size();
		int num_piedra = cartas.getPiedra().size();
		int num_paja = cartas.getPaja().size();
		int num_arcilla = cartas.getArcilla().size(); 
		List<Recurso> lista_ofrecer = new ArrayList<>();
		Recurso recurso = new Recurso();
		
		/*int[] numero_cada_recurso = new int[]{num_lana,num_piedra,num_paja }; 
		List<Recurso> lista_recibir = new ArrayList<>();
		Recurso max_recurso = new Recurso();
		int maximo = 0;
		for(int i=0;i<numero_cada_recurso.length;i++) {
			if(numero_cada_recurso[i]>maximo) {maximo = numero_cada_recurso[i];}
		}*/
		
		if(poblados==0) {/*busco poblado*/	
			
			if(cartas.getPiedra().size()>0) {recurso.setTipo("Piedra");lista_ofrecer.add(recurso);return lista_ofrecer;}
			if(num_lana>2) {recurso.setTipo("Lana"); lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_madera>2) {recurso.setTipo("Madera");lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_arcilla>2) {recurso.setTipo("Arcilla");lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_paja>2) {recurso.setTipo("Paja");lista_ofrecer.add(recurso); return lista_ofrecer;}
			else {recurso.setTipo("Lana");lista_ofrecer.add(recurso); return lista_ofrecer;}
			}			
		
		if(poblados>1) {/* busco ciudad*/
			if(num_lana>2) {recurso.setTipo("Lana"); lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_madera>2) {recurso.setTipo("Madera");lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_arcilla>2) {recurso.setTipo("Arcilla");lista_ofrecer.add(recurso); return lista_ofrecer;}
			else {recurso.setTipo("Arcilla");lista_ofrecer.add(recurso); return lista_ofrecer;}
		}
		else {/*busco poblado*/
			if(cartas.getPiedra().size()>0) {recurso.setTipo("Piedra");lista_ofrecer.add(recurso);return lista_ofrecer;}
			if(num_lana>2) {recurso.setTipo("Lana"); lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_madera>2) {recurso.setTipo("Madera");lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_arcilla>2) {recurso.setTipo("Arcilla");lista_ofrecer.add(recurso); return lista_ofrecer;}
			if(num_paja>2) {recurso.setTipo("Paja");lista_ofrecer.add(recurso); return lista_ofrecer;}
			else {recurso.setTipo("Lana");lista_ofrecer.add(recurso); return lista_ofrecer;}
		}
		
	}

	@Override
	public Recurso propuestaNegociarJugadorRecibir(Jugador yo) {
		
		Cartas cartas = yo.getCartas();
		List<Construccion> construcciones = yo.getConstrucciones();
		int poblados = 0;
		int ciudades = 0;
		for(int i=0;i<construcciones.size();i++) {
			if(construcciones.get(i).getTipo().equals("Poblado")) {poblados++;}
			if(construcciones.get(i).getTipo().equals("Ciudad")) {ciudades++;}
		}
		int num_lana = cartas.getLana().size();
		int num_madera = cartas.getMadera().size();
		int num_piedra = cartas.getPiedra().size();
		int num_paja = cartas.getPaja().size();
		int num_arcilla = cartas.getArcilla().size(); 
		Recurso recurso = new Recurso();
		
		if(poblados==0) {/*busco poblado*/	
			if(num_lana==0) {recurso.setTipo("Lana");return recurso;}
			if(num_madera==0) {recurso.setTipo("Madera");return recurso;}
			if(num_arcilla==0) {recurso.setTipo("Arcilla");return recurso;}
			if(num_paja==0) {recurso.setTipo("Paja");return recurso;}
			else {recurso.setTipo("Lana");return recurso;}
			}			
		
		if(poblados>1) {/* busco ciudad*/
			if(num_piedra<3) {recurso.setTipo("Piedra");return recurso;}
			if(num_paja<2) {recurso.setTipo("Paja");return recurso;}
			else {recurso.setTipo("Piedra");return recurso;}
		}
		else {/*busco poblado*/
			if(num_lana==0) {recurso.setTipo("Lana");return recurso;}
			if(num_madera==0) {recurso.setTipo("Madera");return recurso;}
			if(num_arcilla==0) {recurso.setTipo("Arcilla");return recurso;}
			if(num_paja==0) {recurso.setTipo("Paja");return recurso;}
			else {recurso.setTipo("Lana");return recurso;}
		}
		
	}

	@Override
	public Recurso propuestaNegociarBanca(Cartas carta) {
		// TODO Auto-generated method stub
		return new Recurso("Madera");
	}

	@Override
	public boolean aceptarOferta(RealizarOferta oferta, Cartas mis_cartas) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Construccion decidirConstruccion(Mapa mapa, Cartas cartas, String nombre) {
		// TODO Auto-generated method stub
		List<Casilla> casillas = mapa.getCasillas();
		Construccion retorno = null;

		// Queremos hacer ciudades.
		if (cartas.getPaja().size() >= 2 && cartas.getPiedra().size() >= 3) {

				for (int i = 0; i < casillas.size(); i++) {
					List<Node> nodos = casillas.get(i).getAdyacentes();

					for (int j = 0; j < nodos.size(); j++) {

						if (nodos.get(j).getDue�o().getNombre().equals(nombre)) {
							if (nodos.get(j).getTipo() == "Poblado" && cartas.getPaja().size() >= 2
									&& cartas.getPiedra().size() >= 3) {
								retorno = new Construccion("Ciudad", nodos.get(j), null);
							}
						}
					}
				}
			}
		// Poblado
		else if (cartas.getArcilla().size() >= 1 && cartas.getMadera().size() >= 1 && cartas.getPaja().size() >= 1
				&& cartas.getLana().size() >= 1) {

			for (int i = 0; i < casillas.size(); i++) {
				List<Node> nodos = casillas.get(i).getAdyacentes();

				for (int j = 0; j < nodos.size(); j++) {

					if (!nodos.get(j).isOcupado()) {
						retorno= new Construccion("Poblado", nodos.get(j), null);
					}
				}
			}
		}
	    // Carretera
		else if (cartas.getArcilla().size() >= 1 && cartas.getMadera().size() >= 1) {
			// Ya tenemos suficientes recursos para construir carretera

				for (int i = 0; i < casillas.size(); i++) {
					List<Node> nodos = casillas.get(i).getAdyacentes();

					for (int j = 0; j < nodos.size(); j++) {

						if (nodos.get(j).getDue�o().getNombre().equals(nombre)) {
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
		}
		
		return retorno;
	}
	@Override
	public Boolean decidirCompra() {
		// TODO Auto-generated method stub
		return true;
	}

	

}