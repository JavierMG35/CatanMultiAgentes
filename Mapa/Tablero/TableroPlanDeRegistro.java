package Mapa.Tablero;

import jadex.runtime.*;
import jadex.util.SUtil;
import jadex.adapter.fipa.*;

public class TableroPlanDeRegistro {

	public void body()
	{
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Jugador");
		sd.setName("Jugador1");
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
		System.out.println(numero_jugadores+" jugadores  encontrados");
		getBeliefbase().getBelief("jugadores").setFact(numero_jugadores);
	}


}
