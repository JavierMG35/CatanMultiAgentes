package src.Jugador.estrategias;

import java.util.List;
import java.util.Random;

import src.Jugador.Jugador;
import src.Mapa.Casilla;
import src.Mapa.Edge;
import src.Mapa.Node;
import src.ontologia.actions.RealizarOferta;
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

	@Override
	public List<Recurso> propuestaNegociarJugadorOfrecer(Jugador jugador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recurso propuestaNegociarJugadorRecibir(Jugador jugador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recurso propuestaNegociarBanca(Cartas carta) {
		// TODO Auto-generated method stub
		return null;
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
		// Podemos decidir construir aleatoriamente con una probabilidad p.
		Random rand1 = new Random();
		int p = rand1.nextInt(3);

		// Construye con una probabilidad de 1/3.
		if (p == 1) {

			Random rand2 = new Random();
			int q = rand2.nextInt(3);

			if (p == 0) { // Poblado

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
				}

			} else if (p == 1) { // Carretera

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
				}

			} else { // Ciudad

				if (cartas.getPaja().size() >= 2 && cartas.getPiedra().size() >= 3) {

					for (int i = 0; i < casillas.size(); i++) {
						List<Node> nodos = casillas.get(i).getAdyacentes();

						for (int j = 0; j < nodos.size(); j++) {

							if (nodos.get(j).getDueño().getNombre().equals(nombre)) {
								if (nodos.get(j).getTipo() == "Poblado" && cartas.getPaja().size() >= 2
										&& cartas.getPiedra().size() >= 3) {
									retorno = new Construccion("Ciudad", nodos.get(j), null);
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
	public Boolean decidirCompra() {
		// TODO Auto-generated method stub
		return null;
	}

}
