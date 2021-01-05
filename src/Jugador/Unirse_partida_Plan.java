package src.Jugador;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import jadex.adapter.fipa.SFipa;
import src.Tablero.Dados;
import src.ontologia.*;

public class Unirse_partida_Plan extends Plan {
	public void body()
	{
	IMessageEvent	request	= (IMessageEvent)getInitialEvent();
	AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
	System.out.println("Recibido mensaje de Tablero a Jugador");
	Request_unirse_partida rj = (Request_unirse_partida)request.getContent();
	Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
	rj.setJugador(yo);
	IMessageEvent mensaje_enviar = request.createReply("unirse_partida");
	mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
	mensaje_enviar.setContent(rj);
	getLogger().info(yo.nombre + "envía petición de unida a la partida");
	System.out.println(yo.nombre + "envía petición de unida a la partida");
	System.out.println("Envio el mensaje");
    sendMessage(mensaje_enviar);	
    System.out.println("Ya Envio el mensaje");
	}
}
