package src.Jugador.Plan;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import jadex.adapter.fipa.SFipa;
import src.Jugador.Jugador;
import src.ontologia.actions.OfrecerUnirsePartida;
import src.ontologia.predicates.UnirmePartida;

public class Unirse_partida_Plan extends Plan {
	public void body()
	{
	//Recibo una oferta para unirme a un partida de catan
	IMessageEvent	request	= (IMessageEvent)getInitialEvent();
	OfrecerUnirsePartida rj = (OfrecerUnirsePartida)request.getContent();
	//Obtengo mi Jugador de las creencias del agente
	
	Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
	yo.setAgentID(getAgentIdentifier());
	rj.setJugador(yo);
	UnirmePartida mensaje = new UnirmePartida(yo);
	//Envio un mensaje con mi jugador al tablero
	IMessageEvent mensaje_enviar = request.createReply("unirse_partida",mensaje);
    sendMessage(mensaje_enviar);	
	}
}
