package src.Jugador.Plan;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.ontologia.actions.EntregarDados;
import src.ontologia.actions.ObtencionRecursos;
import src.ontologia.concepts.Dados;
import src.ontologia.predicates.TiradaDados;


public class TirarDadosPlan extends Plan {
	public void body() {
		//Recibo del tablero la informacion de que se me han entregado los dados y debo tirarlos
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		EntregarDados  mensaje = (EntregarDados)request.getParameter(SFipa.CONTENT).getValue();
		Dados dados = new Dados();		
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		//Actualizo la tirada que he obtenido
		yo.setTirada(dados.getDados());
		TiradaDados tirada= new TiradaDados(dados);
		// Envio los dados que he obtenido con su puntuacion
		IMessageEvent mensaje_enviar = request.createReply("receive_tirar",tirada);
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
	    sendMessage(mensaje_enviar);	
		
	}

}
