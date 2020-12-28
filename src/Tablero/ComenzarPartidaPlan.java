package src.Tablero;

import jadex.runtime.*;
//import src.Jugador.*;
//import jadex.util.SUtil;
import java.util.List;
import jadex.adapter.fipa.*;
import src.ontologia.*;

public class ComenzarPartidaPlan extends Plan{
	
	public List<Integer> valores;
	public int max_valor = 0;
	protected long timeout;
	
	public void body() {
		this.timeout = ((Number)getBeliefbase().getBelief("playerwaitmillis").getFact()).longValue();
		System.out.println("Comienza el juego");
		Tablero	yo	= (Tablero)getBeliefbase().getBelief("myself").getFact();
		
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
		
		IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dadosMsg");
		AgentIdentifier receptor = result[0].getName();
		TirarDados tirardados = new TirarDados();
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(receptor);
		mensaje_enviar.setContent(tirardados);
		
		//mensaje_enviar.getParameterSet(SFipa.SENDER).addValue(yo);
		System.out.println("Enviado de tablero a jugador");
		IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar, timeout);		
		System.out.println("Recibido de jugador a tablero (o no)");
		System.out.println(respuesta);
		Dados dados = (Dados)respuesta.getContent();
		valores.add(dados.getDados());
		System.out.println(dados.getDados());
		getLogger().info("Mensaje de TirarDados enviado a" + result[0].getName());
		/*for(int i=0;i<4;i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dadosMsg");
			AgentIdentifier receptor = result[i].getName();
			TirarDados tirardados = new TirarDados();
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(receptor);
			mensaje_enviar.setContent(tirardados);
			
			//mensaje_enviar.getParameterSet(SFipa.SENDER).addValue(yo);
			System.out.println("Enviado de tablero a jugador");
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar, 15000);		
			System.out.println("Recibido de jugador a tablero (o no)");
			System.out.println(respuesta);
			Dados dados = (Dados)respuesta.getContent();
			valores.add(dados.getDados());
			getLogger().info("Mensaje de TirarDados enviado a" + i);
		}
		*/
		System.out.println("Protocolo Terminado");
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Jugador[]	jugadores	= (Jugador[])getBeliefbase().getBeliefSet("jugador").getFacts();
		

		//System.out.println("A");
		//System.out.println(jugadores);
		/*
		for(int i=0;i<4;i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(jugadores[i].getAgentID());
			mensaje_enviar.getParameterSet(SFipa.SENDER).addValue(yo);
			System.out.println("Enviado de tablero a jugador");
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar, 15000);		
			System.out.println("Recibido de jugador a tablero (o no)");
			System.out.println(respuesta);
			Dados dados = (Dados)respuesta.getContent();
			valores.add(dados.getDados());
			getLogger().info("Mensaje de TirarDados enviado a" + i);
		}
		for(int i=0;i<valores.size();i++) {
			if(valores.get(i)>max_valor) {
				max_valor = valores.get(i);
			}
		}
		
		System.out.println("we made it ");*/
		
	}

}
