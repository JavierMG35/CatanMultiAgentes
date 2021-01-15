package src.Jugador.estrategias;

import java.util.ArrayList;
import java.util.List;


import src.ontologia.concepts.*;
import src.Jugador.Jugador;
import src.Mapa.Casilla;
import src.Mapa.Node;
import src.Mapa.Edge;
import src.ontologia.actions.RealizarOfertaJugador;


public class CosmopolitaEstrategia extends AbstractEstrategias{

	
	public CosmopolitaEstrategia() {
		
		// TODO Auto-generated constructor stub
	}
	
	
	public CosmopolitaEstrategia(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	public Recurso propuestaNegociarJugadorOfrecer(Jugador yo) {
		//Busca el recurso que mas me interesa comerciar con otro jugador
		Cartas cartas = yo.getCartas();
		List<Construccion> construcciones = yo.getConstrucciones();
		int poblados = 0;
		
		for(int i=0;i<construcciones.size();i++) {
			if(construcciones.get(i).getTipo().equals("Poblado")) {poblados++;}
		}
		int num_lana = cartas.getLana().size();
		int num_madera = cartas.getMadera().size();
		int num_piedra = cartas.getPiedra().size();
		int num_paja = cartas.getPaja().size();
		int num_arcilla = cartas.getArcilla().size(); 
		Recurso recurso_ofrecer = new Recurso();
		
		
		if(poblados==0) {/*busco poblado*/	
			
			if(num_piedra>1) {recurso_ofrecer.setTipo("Piedra");return recurso_ofrecer;}
			if(num_lana>2) {recurso_ofrecer.setTipo("Lana"); return recurso_ofrecer;}
			if(num_madera>2) {recurso_ofrecer.setTipo("Madera");return recurso_ofrecer;}
			if(num_arcilla>2) {recurso_ofrecer.setTipo("Arcilla"); return recurso_ofrecer;}
			if(num_paja>2) {recurso_ofrecer.setTipo("Paja"); return recurso_ofrecer;}
			else {recurso_ofrecer=null; return recurso_ofrecer;}
			}			
		
		if(poblados>1) {/* busco ciudad*/
			if(num_lana>2) {recurso_ofrecer.setTipo("Lana");  return recurso_ofrecer;}
			if(num_madera>2) {recurso_ofrecer.setTipo("Madera"); return recurso_ofrecer;}
			if(num_arcilla>2) {recurso_ofrecer.setTipo("Arcilla"); return recurso_ofrecer;}
			else {recurso_ofrecer=null; return recurso_ofrecer;}
		}
		else {/*busco poblado*/
			if(num_piedra>1) {recurso_ofrecer.setTipo("Piedra");return recurso_ofrecer;}
			if(num_lana>2) {recurso_ofrecer.setTipo("Lana");  return recurso_ofrecer;}
			if(num_madera>2) {recurso_ofrecer.setTipo("Madera"); return recurso_ofrecer;}
			if(num_arcilla>2) {recurso_ofrecer.setTipo("Arcilla");return recurso_ofrecer;}
			if(num_paja>2) {recurso_ofrecer.setTipo("Paja"); return recurso_ofrecer;}
			else {recurso_ofrecer=null; return recurso_ofrecer;}
		}
		
	}

	@Override
	public Recurso propuestaNegociarJugadorRecibir(Jugador yo) {
		//comprueba el recurso que le interesa recibir de otro jugador 
		Cartas cartas = yo.getCartas();
		List<Construccion> construcciones = yo.getConstrucciones();
		int poblados = 0;
		for(int i=0;i<construcciones.size();i++) {
			if(construcciones.get(i).getTipo().equals("Poblado")) {poblados++;}
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
	public Recurso[] propuestaNegociarBanca(Cartas cartas, Mapa mapa ,Jugador yo) {
		//Comprueba que recurso puedo cambiar con la banca y cual es el que mas me interesa
		boolean poblado =false;
		for (int i = 0; i < mapa.getCasillas().size();i++)

		{
			for (int j=0;j<mapa.getCasillas().get(i).getAdyacentes().size();j++) {

				if (mapa.getCasillas().get(i).getAdyacentes().get(j).getDueño()!=null) {
				if (mapa.getCasillas().get(i).getAdyacentes().get(j).getDueño().getNombre().equals(yo.getNombre()) && mapa.getCasillas().get(i).getAdyacentes().get(j).getTipo().equals("Poblado")) 
				{
					poblado = true;
				}
				
				}
			}
		}
		
		if (poblado) {
			
			Recurso[] Recursos = {new Recurso(),new Recurso()};
			Recurso recursoposible=null;
			recursoposible=yo.cuatroCartasIguales("Madera");
			recursoposible=yo.cuatroCartasIguales("Arcilla");
			recursoposible=yo.cuatroCartasIguales("Lana");
			if (recursoposible.getTipo()!=null) {
				Recursos[1]=recursoposible;
				List<Integer> ints = List.of(cartas.getPiedra().size(),cartas.getPaja().size());
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
					Recursos[0]=new Recurso("Piedra");
				case 1:
					Recursos[0]= new Recurso ("Paja");
				}
				return Recursos;
				
			}
			else {return null;}
			
		}
		else {
		
		Recurso[] Recursos = {new Recurso(),new Recurso()};
		Recurso recursoposible=null;
		recursoposible=yo.cuatroCartasIguales("Piedra");
		if (recursoposible.getTipo()!=null) {
			Recursos[1]=recursoposible;
			List<Integer> ints = List.of(cartas.getPaja().size(),cartas.getPiedra().size(),cartas.getLana().size(),cartas.getArcilla().size());
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
				Recursos[0]=new Recurso("Paja");
			case 1:
				Recursos[0]= new Recurso ("Piedra");
			case 2:
				Recursos[0]= new Recurso ("Lana");
			case 3:
				Recursos[0]= new Recurso ("Arcilla");
			}
			return Recursos;
			
		}
		else {return null;}
		}
	}

	
	public boolean aceptarOferta(RealizarOfertaJugador oferta, Jugador yo) {
		//Compruebo si me interesa aceptar una oferta recibida de otro jugador
		boolean acepto = false;
		Cartas cartas = yo.getCartas();
		Recurso recurso_recibir = oferta.getTe_doy();
		Recurso recurso_dar = oferta.getMe_das();
		List<Construccion> construcciones = yo.getConstrucciones();
		
		int poblados = 0;
		for(int i=0;i<construcciones.size();i++) {
			if(construcciones.get(i).getTipo().equals("Poblado")) {poblados++;}
		}
		int num_lana = cartas.getLana().size();
		int num_madera = cartas.getMadera().size();
		int num_piedra = cartas.getPiedra().size();
		int num_paja = cartas.getPaja().size();
		int num_arcilla = cartas.getArcilla().size(); 
		
		if(cartas.getRecurso(recurso_dar)==null||cartas.getRecurso(recurso_dar).size()==0) {	acepto = false; return acepto;	}
		else {
			int num_recurso_dar = cartas.getRecurso(recurso_dar).size();
		if(poblados==0&&num_recurso_dar>1) {/*busco poblado*/	
			if(recurso_recibir.getTipo().equals("Madera") && num_madera<3 ) {acepto = true;return acepto;}
			if(recurso_recibir.getTipo().equals("Arcilla") && num_arcilla<3) {acepto = true;return acepto;}
			if(recurso_recibir.getTipo().equals("Paja") && num_paja<3) {acepto = true;return acepto;}
			if(recurso_recibir.getTipo().equals("Lana") && num_lana<3) {acepto = true;return acepto;}
			else{acepto = false;return acepto;}
			}			
		
		if(poblados>1&&!recurso_dar.getTipo().equals("Piedra")&&!!recurso_dar.getTipo().equals("Paja")) {/* busco ciudad*/
			if(num_piedra<5 && recurso_recibir.getTipo().equals("Piedra") ) {acepto = true;return acepto;}
			if(num_paja<4&& recurso_recibir.getTipo().equals("Paja")) {acepto = true;return acepto;}
			else {acepto = false;return acepto;}
		}
		else {/*busco poblado*/
			if(num_lana<3 && recurso_recibir.getTipo().equals("Lana")) {acepto = true;return acepto;}
			if(num_madera<3 && recurso_recibir.getTipo().equals("Madera")) {acepto = true;return acepto;}
			if(num_arcilla<3 && recurso_recibir.getTipo().equals("Arcilla")) {acepto = true;return acepto;}
			if(num_paja<3 && recurso_recibir.getTipo().equals("Paja")) {acepto = true;return acepto;}
			else {acepto = false;return acepto;}
		}
		
		}
	}
	@Override
	public Construccion decidirConstruccion(Mapa mapa, Cartas cartas, String nombre) {
		//Decido que contruccion realizar
		// TODO Auto-generated method stub
		List<Casilla> casillas = mapa.getCasillas();
		Construccion retorno = null;

		// Queremos hacer ciudades.
		if (cartas.getPaja().size() >= 2 && cartas.getPiedra().size() >= 3) {
				for (int i = 0; i < casillas.size(); i++) {
					List<Node> nodos = casillas.get(i).getAdyacentes();
					for (int j = 0; j < nodos.size(); j++) {
						if (nodos.get(j).getDueño() != null && nodos.get(j).getDueño().getNombre().equals(nombre)) {
							if (nodos.get(j).getTipo().equals("Poblado")) {
								retorno = new Construccion("Ciudad", nodos.get(j), null);
								return retorno;
							}
						}
					}
				}
			}
		// Poblado
		if (cartas.getArcilla().size() >= 1 && cartas.getMadera().size() >= 1 && cartas.getPaja().size() >= 1
				&& cartas.getLana().size() >= 1) {

			for (int i = 0; i < casillas.size(); i++) {
				List<Node> nodos = casillas.get(i).getAdyacentes();

				for (int j = 0; j < nodos.size(); j++) {

					if (!nodos.get(j).isOcupado()) {
						retorno= new Construccion("Poblado", nodos.get(j), null);
						return retorno;
					}
				}
			}
		}
	    // Carretera
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
									return retorno;
								}
							}
						}
				}
			}
		}
		
		return retorno;
	}
	
	
	@Override
	public Boolean decidirCompra(Cartas cartas) {
		// Queremos hacer ciudades.
		if (cartas.getPaja().size() >= 1 && cartas.getPiedra().size() >= 1 && cartas.getLana().size() >= 1) {
			return true;
			}

		return false;
	}

	

}
