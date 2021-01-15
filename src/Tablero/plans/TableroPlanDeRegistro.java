package src.Tablero.plans;

import jadex.runtime.*;
import src.ontologia.actions.OfrecerUnirsePartida;
import src.ontologia.predicates.UnirmePartida;
import jadex.util.SUtil;
import jadex.adapter.fipa.*;
import src.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;


public class TableroPlanDeRegistro extends Plan{

	public void body()
	{
		
	System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
	System.out.println("///////           ///////              ////              ////             /////      ////   ////");
	System.out.println("///     ////////      /    /////////    ///              //     ////////    ///   /   ///   ////");
	System.out.println("///     ///////////////    /////////    ////////    ///////     ////////    ///   /    //   ////");
	System.out.println("///     ///////////////    /////////    ////////    ///////     ////////    ///   //   //   ////");
	System.out.println("///     ///////////////                 ////////    ///////                 ///   //   //   ////");
	System.out.println("///     ///////////////                 ////////    ///////                 ///   ///   /   ////");
	System.out.println("///     ///////////////    /////////    ////////    ///////     ////////    ///   ///   /   ////");
	System.out.println("///     ///////////////    /////////    ////////    ///////     ////////    ///   ////      ////");
	System.out.println("///     ///////////////    /////////    ////////    ///////     ////////    ///   /////     ////");
	System.out.println("////    //////      ///    /////////    ////////    ///////     ////////    ///   //////    ////");
	System.out.println("////////         //////    /////////    ////////    ///////     ////////    ///   ///////   ////");
	System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
	
	
	System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
	System.out.println("////BIENVENIDOS AL CATAN, EL JUEGO MULTIAGENTES/////////////////////////////////////////////////");
	System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
	System.out.println("/////CREADO POR:////////////////////////////////////////////////////////////////////////////////");
	System.out.println("/////////////////////////////////////JOSELUIS VILLANUEVA URBINA/////////////////////////////////");
	System.out.println("/////////////////////////////////////VICTOR CEREIJO HERRANZ/////////////////////////////////////");
	System.out.println("/////////////////////////////////////PABLO GOMEZ GRACIA/////////////////////////////////////////");
	System.out.println("/////////////////////////////////////JAVIER MONCADA GUTIERREZ///////////////////////////////////");
	
	
	
		ServiceDescription sd = new ServiceDescription();
		sd.setType("jugador");
		sd.setName("jugador");
		AgentDescription adesc = new AgentDescription();
		adesc.addService(sd);
		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults(-1);
		//Buscamos en el df quien esta registrado
		IGoal busqueda = createGoal("df_search");
		busqueda.getParameter("description").setValue(adesc);
		busqueda.getParameter("constraints").setValue(sc);
		dispatchSubgoalAndWait(busqueda);
		AgentDescription[] result =(AgentDescription[])busqueda.getParameterSet("result").getValues();
		int numero_jugadores= result.length;
		System.out.println("Jugadores  registrados para la partida: "+numero_jugadores );
		Jugador[] jugadores = new Jugador[numero_jugadores];
	
		//Enviamos una solicitud al jugador para que se una a la partida, estos los encontramos en el df
		for(int i=0;i< result.length;i++) {
			IMessageEvent	msg	= createMessageEvent("request_unirse_partida");
			msg.getParameterSet(SFipa.RECEIVERS).addValue(result[i].getName());
			OfrecerUnirsePartida request = new OfrecerUnirsePartida();
			msg.setContent(request);
			IMessageEvent	reply	= sendMessageAndWait(msg);
			UnirmePartida rj = (UnirmePartida)reply.getContent();
			jugadores[i] = rj.getJugador();
			jugadores[i].setAgentID((AgentIdentifier)reply.getParameter("sender").getValue());
		}
		getBeliefbase().getBeliefSet("jugador").addFacts(jugadores);
		getBeliefbase().getBelief("jugadores").setFact(numero_jugadores);
	}


}
