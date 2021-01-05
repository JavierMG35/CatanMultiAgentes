package src.Tablero.plans;

import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Tablero.Dados;
import src.ontologia.Orden;

public class RecibirDadosPlan extends Plan {

	public void body()
	{
		
		Orden Lista = ((Orden)getBeliefbase().getBelief("orden").getFact());
		
		System.out.println("Tengo los dados");
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		Dados numero =(Dados) request.getContent();
		System.out.println("Tengo los dados, le salio un : " +numero.getDados() +" "+ numero.getDado1() +" y " + numero.getDado2());
		Lista.setTurno(Lista.getTurno()+1);
		System.out.println("Le toca a : "+ Lista.getTurno());
		getBeliefbase().getBelief("esperando_dados_jugador").setFact(new Boolean(false));
		
		
		
		
	}

		
	
}
