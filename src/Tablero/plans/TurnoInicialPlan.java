package src.Tablero.plans;

import java.util.List;

//import jadex.adapter.fipa.AgentDescription;
//import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Tablero.Tablero;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Mapa;
import src.ontologia.concepts.Orden;
import src.Mapa.Casilla;
import src.Mapa.Node;

public class TurnoInicialPlan extends Plan {
	public void body() {
		//////////////////////// Creamos el mapa y declaramos el estado del juego
		//////////////////////// Inicial
		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("---------Tablero Inicia el primer turno del juego------");
		System.out.println("////////////////////////////////////////////////////////");
		
		//////////////////////////////////////Obtenemos el orden
		Orden Orden = ((Orden) getBeliefbase().getBelief("orden").getFact());
		/////////////////////////////////
		// cada jugador coloca un poblado y una carretera por el orden de los turnos
		for (int i = 0; i < Orden.getJugadores().size(); i++) {
			EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BasicAgentIdentifier AidSiguiente = Orden.getJugadores().get(i).getAid();
			IMessageEvent mensaje_enviar = createMessageEvent("coloca_fichas_iniciales");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.getParameter(SFipa.CONTENT).setValue(EstadoJuego);
			IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);
			EstadoJuego estado = (EstadoJuego) respuesta.getContent();
			getBeliefbase().getBelief("EstadoJuego").setFact(estado);

			
		}

		// Ahora en el orden inverso

		for (int i = Orden.getJugadores().size() - 1; i >= 0; i--) {

			EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
			System.out.println("////////////////////////////////////////////////////////");
			BasicAgentIdentifier AidAnterior = Orden.getJugadores().get(i).getAid();
			IMessageEvent mensaje_enviar = createMessageEvent("coloca_fichas_iniciales");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidAnterior);
			mensaje_enviar.setContent(EstadoJuego);
			IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);
			EstadoJuego estado = (EstadoJuego) respuesta.getContent();
			getBeliefbase().getBelief("EstadoJuego").setFact(estado);

		}
		Jugador[] jugadores = (Jugador[]) getBeliefbase().getBeliefSet("jugador").getFacts();
		for (int i = 0; i < Orden.getJugadores().size(); i++) {
			jugadores[i].setState(Jugador.STATE_PLAYING);

		}
		Tablero yo = (Tablero) getBeliefbase().getBelief("myself").getFact();
		int ini = 4;
		getBeliefbase().getBelief("inicializado").setFact(ini);
		/*
		 * System.out.println(yo.getState()); yo.setState(Tablero.STATE_PLAYING);
		 * System.out.println(yo.getState());
		 */

		getBeliefbase().getBelief("myself").setFact(yo);
		getBeliefbase().getBelief("orden").setFact(Orden);
		System.out.println("---------Fin del turno inicial del tablero-----------");
		System.out.println("////////////////////////////////////////////////////////");

	}

}
