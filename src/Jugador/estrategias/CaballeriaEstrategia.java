package src.Jugador.estrategias;

import java.util.List;
import java.util.Random;

import src.Jugador.Jugador;
import src.Mapa.Casilla;
import src.Mapa.Edge;
import src.Mapa.Node;
import src.ontologia.actions.RealizarOfertaJugador;
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.Construccion;
import src.ontologia.concepts.Mapa;
import src.ontologia.concepts.Recurso;

public class CaballeriaEstrategia extends AbstractEstrategias {

	public CaballeriaEstrategia() {
		
		// TODO Auto-generated constructor stub
	}
	public CaballeriaEstrategia(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	public  Recurso propuestaNegociarJugadorOfrecer(Jugador yo) {

        Cartas cartas = yo.getCartas();
        int num_madera = cartas.getMadera().size();
        int num_arcilla = cartas.getArcilla().size();
        int[] numero_cada_recurso = new int[]{num_madera,num_arcilla}; 
        Recurso recurso_recibir = new Recurso();
        int maximo = 0;
        for(int i=0;i<numero_cada_recurso.length;i++) {
            if(numero_cada_recurso[i]>maximo) {maximo = numero_cada_recurso[i];}
        }

            if(cartas.getPaja().size()==maximo) {recurso_recibir.setTipo("Madera");return recurso_recibir;}
            if(cartas.getPiedra().size()==maximo) {recurso_recibir.setTipo("Arcilla");return recurso_recibir;}

        return null;
    }

    @Override
    public Recurso propuestaNegociarJugadorRecibir(Jugador yo) {
        Cartas cartas = yo.getCartas();

        int num_madera =  cartas.getMadera().size();
        int num_piedra = cartas.getPiedra().size();
        int num_paja = cartas.getPaja().size();

        if(num_paja <3) {
            Recurso paja_recurso = new Recurso();
            paja_recurso.setTipo("Paja");
            return paja_recurso; }
        if(num_piedra <3) {
        Recurso piedra_recurso = new Recurso();
        piedra_recurso.setTipo("Piedra");
        return piedra_recurso; }
        if(num_madera <3) {
        Recurso lana_recurso = new Recurso();
        lana_recurso.setTipo("Lana");
        return lana_recurso; }

        return null;
    }
	
	@Override
	public Recurso[] propuestaNegociarBanca(Cartas cartas, Mapa mapa,Jugador yo) {
		Recurso[] Recursos = {new Recurso(),new Recurso()};
		Recurso recursoposible=null;
		recursoposible=yo.cuatroCartasIguales("Madera");
		recursoposible=yo.cuatroCartasIguales("Arcilla");
		if (recursoposible.getTipo()!=null) {
			Recursos[1]=recursoposible;
			List<Integer> ints = List.of(cartas.getPaja().size(),cartas.getPiedra().size(),cartas.getLana().size());
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
			}
			return Recursos;
			
		}
		else {return null;}
		
	}

	public boolean aceptarOferta(RealizarOfertaJugador oferta,Jugador yo) {
		Cartas mis_cartas = yo.getCartas();
		boolean acepto = false;
		Recurso oferta_recibir_recursos = oferta.getTe_doy();
		Recurso oferta_dar_recursos = oferta.getMe_das();
		String tipo_recurso_recibir = oferta_recibir_recursos.getTipo();
		String tipo_recurso_dar = oferta_dar_recursos.getTipo();
		if(tipo_recurso_recibir.equals("Piedra")||tipo_recurso_recibir.equals("Lana")||tipo_recurso_recibir.equals("Paja")) {
			if(tipo_recurso_recibir.equals("Piedra") && mis_cartas.getMadera().size()<4) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==0) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>2 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana") ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()<=2 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana")) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>2 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja") ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()<=2 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja")) {acepto = false; return acepto;}
				if(!mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra") && mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {acepto = true; return acepto;}
				else {acepto = true; return acepto;}

			}
			if(tipo_recurso_recibir.equals("Lana") && mis_cartas.getArcilla().size()<4) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==0) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja") && mis_cartas.getRecurso(oferta_dar_recursos).size()<4 ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja")) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra") && mis_cartas.getRecurso(oferta_dar_recursos).size()<4 ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra")) {acepto = false; return acepto;}
				if(!mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra") && mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {acepto = true; return acepto;}
				else {acepto = true; return acepto;}
			}
			if(tipo_recurso_recibir.equals("Paja") && mis_cartas.getArcilla().size()<4) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==0) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra") && mis_cartas.getRecurso(oferta_dar_recursos).size()<4 ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra")) {acepto = false; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()>1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana") && mis_cartas.getRecurso(oferta_dar_recursos).size()<4 ) {acepto = true; return acepto;}
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()==1 && mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana")) {acepto = false; return acepto;}
				if(!mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Paja") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Lana") && !mis_cartas.getRecurso(oferta_dar_recursos).get(0).getTipo().equals("Piedra") && mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {acepto = true; return acepto;}
				else {acepto = true; return acepto;}
			}
			else{acepto = true; return acepto;}
			
		}
		else {
			if(mis_cartas.getRecurso(oferta_dar_recursos).size()==0) {acepto = false; return acepto;}
			if(mis_cartas.getRecurso(oferta_recibir_recursos).size()==0 && ( tipo_recurso_dar.equals("Piedra")||tipo_recurso_dar.equals("Lana")||tipo_recurso_dar.equals("Paja"))) {
				if(tipo_recurso_dar.equals("Piedra") && mis_cartas.getRecurso(oferta_dar_recursos).size()>2) {acepto = true; return acepto;}
				if(tipo_recurso_dar.equals("Lana") && mis_cartas.getRecurso(oferta_dar_recursos).size()>2) {acepto = true; return acepto;}
				if(tipo_recurso_dar.equals("Paja") && mis_cartas.getRecurso(oferta_dar_recursos).size()>2) {acepto = true; return acepto;}
				else {acepto = false; return acepto;}
			}
			if(mis_cartas.getRecurso(oferta_recibir_recursos).size()==0 && ( !tipo_recurso_dar.equals("Paja")|| !tipo_recurso_dar.equals("Lana")|| !tipo_recurso_dar.equals("Piedra"))) {
				if(mis_cartas.getRecurso(oferta_dar_recursos).size()!=0) {	acepto = true; return acepto;		}
				else {acepto = false; return acepto;}
			}
			
			else {acepto = true; return acepto;}
		}
		
		
	}

	
	
	@Override
	public Construccion decidirConstruccion(Mapa mapa, Cartas cartas, String nombre) {
		// TODO Auto-generated method stub
		List<Casilla> casillas = mapa.getCasillas();
		Construccion retorno = null;
		// Podemos decidir construir aleatoriamente con una probabilidad p.
		Random rand1 = new Random();
		int p = rand1.nextInt(3);

		// Construye con una probabilidad de 1/3.
		if (p == 1) {

			Random rand2 = new Random();
			int q = rand2.nextInt(3);

			if (q == 0) { // Poblado

				if (cartas.getArcilla().size() >= 1 && cartas.getMadera().size() >= 1 && cartas.getPaja().size() >= 1
						&& cartas.getLana().size() >= 1) {

					for (int i = 0; i < casillas.size(); i++) {
						List<Node> nodos = casillas.get(i).getAdyacentes();

						for (int j = 0; j < nodos.size(); j++) {

							if (!nodos.get(j).isOcupado()) {
								retorno = new Construccion("Poblado", nodos.get(j), null);
								return retorno;
							}
						}
					}
				}

			} if (q == 1) { // Carretera

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

			} if (q == 2) { // Ciudad

				if (cartas.getPaja().size() >= 2 && cartas.getPiedra().size() >= 3) {

					for (int i = 0; i < casillas.size(); i++) {
						List<Node> nodos = casillas.get(i).getAdyacentes();

						for (int j = 0; j < nodos.size(); j++) {

							if (nodos.get(j).getDueño().getNombre().equals(nombre)) {
								if (nodos.get(j).getTipo() == "Poblado" && cartas.getPaja().size() >= 2
										&& cartas.getPiedra().size() >= 3) {
									retorno = new Construccion("Ciudad", nodos.get(j), null);
									return retorno;
								}
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
