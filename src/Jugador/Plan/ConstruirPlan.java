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
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.Construccion;
import src.ontologia.concepts.ConstruirEstado;

public class ConstruirPlan extends Plan {

	public void body() {

		/////////////////////////////////

		System.out.println("Jugador recibe mensaje para construir");

		IMessageEvent request = (IMessageEvent) getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		ConstruirEstado construircontenido = (ConstruirEstado) request.getParameter(SFipa.CONTENT).getValue();
		EstadoJuego estadojuego = construircontenido.getEstadoJuego();
		Mapa mapa = estadojuego.getMapa();
		Jugador yo = (Jugador) getBeliefbase().getBelief("myself").getFact();
		System.out.println(yo.getNombre() + " construye.");
		
		// Dependiendo de la estrategia construimos algo distinto
		IEstrategia estrategia = yo.getStrategy();
		Cartas cartas = yo.getCartas();
		
		
		Recurso arcilla = new Recurso();
		arcilla.setTipo("Arcilla");
		Recurso lana = new Recurso();
		lana.setTipo("Lana");
		Recurso madera = new Recurso();
		madera.setTipo("Madera");
		Recurso paja  = new Recurso();;
		paja.setTipo("Paja");
		Recurso piedra  = new Recurso();;
		piedra.setTipo("Piedra");
		
		
		cartas.setRecurso(arcilla);
		cartas.setRecurso(lana);
		cartas.setRecurso(madera);
		cartas.setRecurso(paja);
		cartas.setRecurso(paja);
		cartas.setRecurso(piedra);
		cartas.setRecurso(piedra);
		cartas.setRecurso(piedra);

		
		System.out.println("El jugador decide que va a construir");
		Construccion construccion = estrategia.decidirConstruccion(mapa, cartas, yo.getNombre());
		System.out.println("El jugador ha decidido");
		
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
		
		
		//Actualizamos creencias
		estadojuego=yo.setMyself(estadojuego);
		estadojuego.setMapa(mapa);
		getBeliefbase().getBelief("myself").setFact(yo);
		
		//Mandamos respuesta al tablero
		construircontenido.setEstadoJuego(estadojuego);
		IMessageEvent mensaje_enviar = (IMessageEvent)request.createReply("construir_recibir");
		mensaje_enviar.getParameter(SFipa.CONTENT).setValue(construircontenido);
	    sendMessage(mensaje_enviar);
	    System.out.println("Mandado construccion de jugador de vuelta al tablero");

	}

}
