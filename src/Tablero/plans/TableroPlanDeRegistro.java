package src.Tablero.plans;

import jadex.runtime.*;
import src.ontologia.*;
import src.ontologia.actions.Request_unirse_partida;
import jadex.util.SUtil;
import jadex.adapter.fipa.*;
import src.Jugador.Jugador;
import java.util.ArrayList;
import java.util.List;


public class TableroPlanDeRegistro extends Plan{

	public void body()
	{
		ServiceDescription sd = new ServiceDescription();
		sd.setType("jugador");
		sd.setName("jugador");
		AgentDescription adesc = new AgentDescription();
		adesc.addService(sd);
		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults(-1);
		IGoal busqueda = createGoal("df_search");
		busqueda.getParameter("description").setValue(adesc);
		busqueda.getParameter("constraints").setValue(sc);
		dispatchSubgoalAndWait(busqueda);
		
		AgentDescription[] result =(AgentDescription[])busqueda.getParameterSet("result").getValues();
		
		int numero_jugadores= result.length;
		System.out.println(numero_jugadores + " jugadores  encontrados");
		Jugador[] jugadores = new Jugador[numero_jugadores];
		
		for(int i=0;i< result.length;i++) {
			IMessageEvent	msg	= createMessageEvent("request_unirse_partida");
			msg.getParameterSet(SFipa.RECEIVERS).addValue(result[i].getName());
			Request_unirse_partida request = new Request_unirse_partida();
			msg.setContent(request);
			getLogger().info("sending join-message");
			// send the join-message and wait for a response
			System.out.println("envio peticion a: " + result[i].getName());
			IMessageEvent	reply	= sendMessageAndWait(msg);
			//Jugador jugador = new Jugador();
			//Request_unirse_partida rj = (Request_unirse_partida)reply.getContent();
			Jugador rj = (Jugador)reply.getContent();
			jugadores[i] = rj;
			System.out.println(rj.getNombre());		

		
		}

		getBeliefbase().getBeliefSet("jugador").addFacts(jugadores);
		getBeliefbase().getBelief("jugadores").setFact(numero_jugadores);
	}


}
