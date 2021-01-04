package src.Tablero;

import jadex.runtime.*;


import java.util.ArrayList;
//import src.Jugador.*;
//import jadex.util.SUtil;
import java.util.List;
import jadex.adapter.fipa.*;
import src.Jugador.Jugador;
import src.ontologia.*;
import java.util.Collections;
import java.util.Comparator;

public class ComenzarPartidaPlan extends Plan{
	
	public List<Integer> valores;
	public int max_valor = 0;
	public int turno = 0;
	protected long timeout;
	
	
	public void body() {
		
		
		
		
		
		this.timeout = ((Number)getBeliefbase().getBelief("playerwaitmillis").getFact()).longValue();
		System.out.println("Comienza el juego");	
		
		
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
		
		/*
		int i2 = ((int)getBeliefbase().getBelief("inicializado").getFact());
		if (i2==0) {
			System.out.println("Iniciamos la lista");
			for (int i = 0 ; i> result.length;i++) { Lista.getJugadores().add(result[i]); }
			getBeliefbase().getBelief("orden").setFact(Lista);
			Lista.setTurno(0);
			Lista.setInicializada(1);
			i2++;
			getBeliefbase().getBelief("inicializado").setFact(i2);
		}
		else {
			turno = Lista.getTurno();
		}*/
		
				
		List<Integer> valores = new ArrayList<>();
		TirarDados tirardados = new TirarDados();
		for(int i=0;i< result.length;i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(result[i].getName());
			mensaje_enviar.setContent(tirardados);
			System.out.println("Enviado de tablero a jugador");
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);		
			System.out.println("Recibido de jugador a tablero (o no)");
			Dados dados = (Dados)respuesta.getContent();
			System.out.println(dados.getDados());
			System.out.println(respuesta);
			
			valores.add(dados.getDados());
			
			getLogger().info("Mensaje de TirarDados enviado a" + result[turno].getName());
		}
		Orden Lista = new Orden();
		Jugador[] jugadores	= (Jugador[])getBeliefbase().getBeliefSet("jugador").getFacts();
		for(int i=0;i< result.length;i++) {
			Lista.addJugador(jugadores[i]);
		}
	
		
		Collections.sort(Lista.getJugadores(), new Comparator<Jugador>() {
	            @Override
	            public int compare(Jugador p1, Jugador p2) {
	                return p1.getTirada() - p2.getTirada();
	            }
	        });
	 
		
		System.out.println( Lista.getJugadores());
				
		System.out.println("Protocolo Terminado");
		
		
		
		
		
		
	
		
	}

}
