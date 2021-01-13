package src.Jugador.Plan;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.ontologia.concepts.Dados;


public class TirarDadosPlan extends Plan {
	public void body() {
		
		
		///////////////////////////////// 
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		Dados dados = new Dados();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		yo.setTirada(dados.getDados());
		System.out.println(dados.getId()+"este es el ide de los dados al comenzar la partida2");
		yo.printID();
		IMessageEvent mensaje_enviar = request.createReply("receive_tirar",dados);
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
	
	    sendMessage(mensaje_enviar);
	    System.out.println("Tiro soy: "+ yo.getNombre()+" saca un : "+yo.getTirada() + " Aid : "+ yo.getAid());
		
		
	}

}
