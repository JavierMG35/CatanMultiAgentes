package src.Jugador.Plan;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.ontologia.concepts.CartaDesarrollo;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.OfertaCartaDesarrollo;

public class ComprarCartaDesarrolloPlan extends Plan{
	
	public void body()
	{
		////////////////////////////////Recibo el mensaje con el estado del juego actual
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		OfertaCartaDesarrollo oferta_carta_desarrollo = (OfertaCartaDesarrollo)request.getContent();
		EstadoJuego EstadoJuego = oferta_carta_desarrollo.getEstadoJuego();
		
		////////////////////////////////Busco a mi personaje para actualizar mi base de creencias
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact(); 
		int index=-1;
		for (int i = 0; i<EstadoJuego.getJugadores().size();i++) {
			if (EstadoJuego.getJugadores().get(i).getNombre().equals(yo.getNombre())) index=i;
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		CartaDesarrollo CartaDesarrollo=null;
		yo	= EstadoJuego.getJugadores().get(index);
		if (yo.getStrategy().decidirCompra() && yo.PermitirCartaDesarrollo()) {
			CartaDesarrollo = new CartaDesarrollo();
			yo.getCartas().addDesarrollo(CartaDesarrollo);
			System.out.println("Soy "+yo.getNombre()+"he obtenido esta carta de desarrollo: " +CartaDesarrollo.getTipo());
		}
		else {System.out.println("Soy "+yo.getNombre()+" no compro la carta de desarrollo");}
		/////////////////////////////Envio la oferta al tablero
		IMessageEvent mensaje_enviar = request.createReply("carta_desarrollo",CartaDesarrollo);
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
	    sendMessage(mensaje_enviar);
	    getBeliefbase().getBelief("myself").setFact(yo);
		
	}


}
