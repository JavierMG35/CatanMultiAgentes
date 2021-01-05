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
		
		Jugador[]  jugadores	= new Jugador[result.length];
		List<Integer> valores = new ArrayList<>();
		TirarDados tirardados = new TirarDados();
		for(int i=0;i< result.length;i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(result[i].getName());
			mensaje_enviar.setContent(tirardados);
			System.out.println("Enviado de tablero a jugador");
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);		
			System.out.println("Recibido de jugador a tablero (o no)");
			Jugador player = (Jugador)respuesta.getContent();
			System.out.println(player.getTirada());
			System.out.println(respuesta);
			jugadores[i] = player;
			
			valores.add(player.getTirada());
			
			getLogger().info("Mensaje de TirarDados enviado a" + result[turno].getName());
		}
		Orden Lista = new Orden();
		int[] lista2 = new int[result.length];
		int minimo = 13;
		int index = 0;
		
		
		//////////////////////////////////////////////////////////
		
		
		Jugador[]  jugadores2	= jugadores;
		System.out.println( jugadores[0].getNombre());
		System.out.println( jugadores[0].getAid());
	
		for( int j =0; j<jugadores.length;j++) {

			System.out.println("Ronda" + j);
			for (int i=0;i<jugadores.length;i++) {
				if(jugadores2[i].getTirada() <= minimo) { 
					minimo = jugadores2[i].getTirada(); 
					index = i; }	
			}
			minimo=13;
			lista2[j] = index;
			jugadores2[index].setTirada(13);
		}
		
		for(int i = jugadores2.length-1; i>=0;i--) {
			Lista.addJugador(jugadores[lista2[i]]);
		}
		
		////////////////////////////
		
		System.out.println( Lista.getJugadores().get(0).getNombre());
		System.out.println( Lista.getJugadores().get(1).getNombre());
		System.out.println( Lista.getJugadores().get(2).getNombre());
		System.out.println( Lista.getJugadores().get(3).getNombre());
				
		System.out.println("Protocolo Terminado");
		
		
		
		
		
		
	
		
	}

}
