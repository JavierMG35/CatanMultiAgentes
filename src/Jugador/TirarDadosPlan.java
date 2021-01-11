package src.Jugador;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.ontologia.concepts.Dados;


public class TirarDadosPlan extends Plan {
	public void body() {
		
		
		///////////////////////////////// 
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		Dados dados = new Dados();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		yo.setTirada(dados.getDados());
		
		IMessageEvent mensaje_enviar = request.createReply("receive_tirar",yo);
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		getLogger().info("Tirada de dados de" + yo.nombre);
	    sendMessage(mensaje_enviar);
	    System.out.println("Tiro soy: "+ yo.getNombre()+" saca un : "+yo.getTirada() + " Aid : "+ yo.getAid());
		
		
	}

}
