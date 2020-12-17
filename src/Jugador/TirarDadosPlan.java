package src.Jugador;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Tablero.Dados;

public class TirarDadosPlan extends Plan {
	public void body() {
		System.out.println("Recibido mensaje de Tablero a Jugador");
		Dados dados = new Dados();
		dados.tirarDados();
		System.out.println("Valor dados" + dados.getDados());
			
		
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		AgentIdentifier	tablero	= (AgentIdentifier)getBeliefbase().getBelief("tablero").getFact();
		
		IMessageEvent mensaje_enviar = createMessageEvent("offer_tirada_dados");
		mensaje_enviar.setContent(dados);
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		mensaje_enviar.getParameterSet(SFipa.SENDER).addValue(yo);
	    sendMessage(mensaje_enviar);		
		getLogger().info("Tirada de dados de" + yo.nombre);
	}

}
