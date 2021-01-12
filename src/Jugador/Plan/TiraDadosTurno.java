package src.Jugador.Plan;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.ontologia.concepts.Dados;
import src.Jugador.Jugador;

public class TiraDadosTurno extends Plan{

	public void body() {

		System.out.println("holaaa");
		IMessageEvent request = (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		Dados dados = new Dados();
		Jugador yo = (Jugador) getBeliefbase().getBelief("myself").getFact();
		yo.setTirada(dados.getDados());

		
//		IMessageEvent mensaje_enviar = request.createReply("dados_jugador", yo);
//		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
//		getLogger().info("Tirada de dados de" + yo.getNombre());
//		sendMessage(mensaje_enviar);
//		System.out.println("Tiro soy: " + yo.getNombre() + " saca un : " + yo.getTirada() + " Aid : " + yo.getAid());

	}
}
