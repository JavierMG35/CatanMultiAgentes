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
		//// Plan dados
		/// Evaluar dados
		/// Si es 7
		/// Ladron (descarte y/o colocar(robas))
		// No es 7 - Repartir recursos
		// negociar
		// negociar juigadores
		// proponer
		// recibir respuesta
		// contraoferta
		// negociar tablero - realizar
		// o construir
		// se selecciona tipo (carretera, poblado, ciudad)
		// donde
		// pagar recursos
		// o comprar carta - realizar
//fin turno

		//////////////////////////////////////
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

			// EstadoJuego.getMapa().getCasillas().get(3).getAdyacentes().get(3).setOcupado(true);

			/*
			 * System.out.
			 * println("---------------------------Turno Inicial Plan antes de que se envie al jugador----------------------"
			 * ); //EstadoJuego.getMapa().printMapa(); //Mapa Mapa = EstadoJuego.getMapa();
			 * System.out.println("////////////////////////////////////////////////////////"
			 * );
			 */
			// AgentDescription[] result
			// =(AgentDescription[])busqueda.getParameterSet("result").getValues();
			BasicAgentIdentifier AidSiguiente = Orden.getJugadores().get(i).getAid();

			System.out.println("Aid siguiente : " + AidSiguiente);

			IMessageEvent mensaje_enviar = createMessageEvent("coloca_fichas_iniciales");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.getParameter(SFipa.CONTENT).setValue(EstadoJuego);
			System.out.println("Mensaje enviado esperando respuesta");

			IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);

			System.out.println("Tablero: Actualizo el tablero tras recibir una pieza inicial");
			EstadoJuego estado = (EstadoJuego) respuesta.getContent();
			getBeliefbase().getBelief("EstadoJuego").setFact(estado);

			/*
			 * System.out.
			 * println("-----------------el tablero tiene este mapa despues de que el jugador lo envie----------------"
			 * ); estado.getMapa().printMapa(); System.out.println(
			 * "---------------------------------------------------------");
			 */	
		}

		// Ahora en el orden inverso

		for (int i = Orden.getJugadores().size() - 1; i >= 0; i--) {

			EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
			// Mapa Mapa = EstadoJuego.getMapa();
			System.out.println("////////////////////////////////////////////////////////");
			// AgentDescription[] result
			// =(AgentDescription[])busqueda.getParameterSet("result").getValues();
			BasicAgentIdentifier AidAnterior = Orden.getJugadores().get(i).getAid();

			System.out.println("Aid anterior : " + AidAnterior);

			IMessageEvent mensaje_enviar = createMessageEvent("coloca_fichas_iniciales");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidAnterior);
			mensaje_enviar.setContent(EstadoJuego);
			System.out.println("Mensaje enviado esperando respuesta");

			IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);

			System.out.println("Respuesta recibidas");

			// EstadoJuego estado = new EstadoJuego((Mapa)respuesta.getContent());
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
