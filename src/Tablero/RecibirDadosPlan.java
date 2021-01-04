package src.Tablero;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.ontologia.Orden;

public class RecibirDadosPlan extends Plan {

	public void body()
	{
		
		Orden Lista = ((Orden)getBeliefbase().getBelief("orden").getFact());
		
		System.out.println("Tengo los dados");
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		Dados numero =(Dados) request.getContent();
		System.out.println("Tengo los dados, le salio un : " + numero.getDado1() +" y " + numero.getDado2()+" = " +numero.getDados() );
		Lista.setTurno(Lista.getTurno()+1);
		System.out.println("Le toca a : "+ Lista.getTurno());
		getBeliefbase().getBelief("esperando_dados_jugador").setFact(new Boolean(false));
		
		//Añadido este codigo y el evento en tablero
		IMessageEvent returntb = createMessageEvent("vuelta_tablero");
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		returntb.setContent(numero);
		returntb.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		System.out.println("Volvemos al tablero");
	    sendMessage(returntb);
		
	}

		
	
}
