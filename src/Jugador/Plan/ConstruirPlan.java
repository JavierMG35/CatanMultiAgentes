package src.Jugador.Plan;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Jugador.estrategias.IEstrategia;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Mapa;
import src.ontologia.concepts.Recurso;
import src.ontologia.predicates.Construido;
import src.ontologia.actions.OfrecerConstruir;
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.Construccion;

public class ConstruirPlan extends Plan {

	public void body() {

		// Se ha notificado a un agente Jugador de que comienza la fase de construccion

		IMessageEvent request = (IMessageEvent) getInitialEvent();
		OfrecerConstruir construircontenido = (OfrecerConstruir) request.getParameter(SFipa.CONTENT).getValue();
		EstadoJuego estadojuego = construircontenido.getEstadoJuego();
		// Acutalizamos el estado del juego y obtenemos nuestros recursos de nuestro
		// personaje en creencias
		Mapa mapa = estadojuego.getMapa();
		Jugador yo = (Jugador) getBeliefbase().getBelief("myself").getFact();

		// Dependiendo de la estrategia construimos algo distinto
		IEstrategia estrategia = yo.getStrategy();
		Cartas cartas = yo.getCartas();
		Construccion construccion = estrategia.decidirConstruccion(mapa, cartas, yo.getNombre());
		// La estrategia nos devolvera el tipo de construccion que queremos colocar
		if (construccion != null) {
			if (construccion.getTipo().equals("Poblado")) {
				mapa.ColocarPoblado(construccion.getNodo().getPos_x(), construccion.getNodo().getPos_y(), yo);
				yo.ConstruirPoblado(construccion.getNodo());

				System.out.println("El jugador " + yo.getNombre() + " ha construido un poblado en "
						+ construccion.getNodo().getPos_x() + "," + construccion.getNodo().getPos_y());

			} else if (construccion.getTipo().equals("Ciudad")) {

				mapa.ColocarCiudad(construccion.getNodo().getPos_x(), construccion.getNodo().getPos_y(), yo);
				yo.ConstruirCiudad(construccion.getNodo());

				System.out.println("El jugador " + yo.getNombre() + " ha construido una ciudad en "
						+ construccion.getNodo().getPos_x() + "," + construccion.getNodo().getPos_y());

			} else {

				mapa.ColocarCarretera(construccion.getCamino(), yo);
				yo.ConstruirCarretera(construccion.getCamino());
				System.out.println("El jugador " + yo.getNombre() + " ha colocado una carretera que va del nodo "
						+ construccion.getCamino().getOrigen().getPos_x() + " a " + construccion.getCamino().getDestino().getPos_y());
			}

		} else {
			System.out.println("No quiero construir en este turno");
		}

		// Actualizamos creencias del jugador
		
		estadojuego = yo.setMyself(estadojuego);
		estadojuego.setMapa(mapa);
		getBeliefbase().getBelief("myself").setFact(yo);
		Construido construido= new Construido(estadojuego);
		// Mandamos respuesta al tablero
		IMessageEvent mensaje_enviar = (IMessageEvent) request.createReply("construir_recibir");
		mensaje_enviar.getParameter(SFipa.CONTENT).setValue(construido);
		sendMessage(mensaje_enviar);

	}

}
