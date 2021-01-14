package src.Jugador.Plan;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Jugador.estrategias.IEstrategia;
import src.Mapa.Casilla;
import src.Mapa.Edge;
import src.Mapa.Node;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Mapa;
import src.ontologia.concepts.Recurso;
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.Construccion;
import java.util.List;
import java.util.Random;

public class ConstruirPlan extends Plan {

	public void body() {

		/////////////////////////////////

		System.out.println("Jugador recibe mensaje para construir");

		IMessageEvent request = (IMessageEvent) getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		EstadoJuego estadojuego = (EstadoJuego) request.getParameter(SFipa.CONTENT).getValue();
		Mapa mapa = estadojuego.getMapa();
		Jugador yo = (Jugador) getBeliefbase().getBelief("myself").getFact();
		System.out.println(yo.getNombre() + " construye.");

		// Dependiendo de la estrategia construimos algo distinto
		IEstrategia estrategia = yo.getStrategy();
		Cartas cartas = yo.getCartas();

		Construccion construccion = estrategia.decidirConstruccion(mapa, cartas, yo.getNombre());

		if (construccion != null) {

			if (construccion.getTipo().equals("Poblado")) {
				
				mapa.ColocarPoblado(construccion.getNodo().getPos_x(),construccion.getNodo().getPos_y(),yo);
				yo.ConstruirPoblado(construccion.getNodo());
				
				System.out.println("El jugador " + yo.getNombre() + " ha construido un poblado en "
						+ construccion.getNodo().getPos_x() + "," + construccion.getNodo().getPos_y());
				
				
			} else if (construccion.getTipo().equals("Ciudad")) {

				mapa.ColocarCiudad(construccion.getNodo().getPos_x(),construccion.getNodo().getPos_y(),yo);
				yo.ConstruirCiudad(construccion.getNodo());
				
				System.out.println("El jugador " + yo.getNombre() + " ha construido una ciudad en "
						+ construccion.getNodo().getPos_x() + "," + construccion.getNodo().getPos_y());
				
			} else {

				mapa.ColocarCarretera(construccion.getCamino(), yo);
				yo.ConstruirCarretera(construccion.getCamino());
				System.out.println("El jugador " + yo.getNombre()
				+ " ha colocado una carretera que va del nodo "
				+ construccion.getCamino().getOrigen() + " a " + construccion.getCamino().getDestino());
			}

		} else {
			System.out.println("No se construye nada en este turno");
		}

	}

}
