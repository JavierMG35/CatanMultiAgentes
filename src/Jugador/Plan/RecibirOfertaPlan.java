package src.Jugador.Plan;


import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.*;
import src.Jugador.estrategias.AbstractEstrategias;
import src.Jugador.estrategias.IEstrategia;
import src.ontologia.actions.*;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.predicates.RespuestaOferta;


public class RecibirOfertaPlan extends Plan{
	public void body() {
		//Recibo de un jugador una oferta de intercambio
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		RealizarOfertaJugador  oferta = (RealizarOfertaJugador)request.getContent();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		EstadoJuego estadojuego = oferta.getEstadojuego();
		/*
		 * Según la estrategia, decidimos si queremos aceptar la oferta o no
		 */
		IEstrategia estrategia = yo.getStrategy();
		boolean acepto = estrategia.aceptarOferta(oferta,yo);
		
		/*
		 * Si acepta la oferta, actualizamos los recursos
		 */
		if(acepto) {
			System.out.println("Acepto la oferta, gano 1 unidad de " + oferta.getTe_doy().getTipo() + " a cambio de 1 unidad de " + oferta.getMe_das().getTipo());
			yo.getCartas().getRecurso(oferta.getMe_das()).remove(oferta.getMe_das());
			yo.getCartas().getRecurso(oferta.getTe_doy()).add(oferta.getTe_doy());
		}
		
		/*
		 * Mandamos la decision de la oferta al ofertante y actualizamos creencias
		 */
		estadojuego = yo.setMyself(estadojuego);
		getBeliefbase().getBelief("myself").setFact(yo);
		RespuestaOferta decision = new RespuestaOferta(acepto,estadojuego);	
		IMessageEvent respuesta_oferta = request.createReply("respuesta_oferta_n",decision);
	    sendMessage(respuesta_oferta);

	}

}
