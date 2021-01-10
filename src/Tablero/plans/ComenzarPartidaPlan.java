package src.Tablero.plans;

import jadex.runtime.*;


import java.util.ArrayList;
//import src.Jugador.*;
//import jadex.util.SUtil;
import java.util.List;
import jadex.adapter.fipa.*;
import src.EstadoJuego.EstadoJuego;
import src.Jugador.Jugador;
import src.Mapa.Mapa;
import src.ontologia.*;
import java.util.Collections;
import java.util.Comparator;

public class ComenzarPartidaPlan extends Plan{
	
	public List<Integer> valores;
	public int max_valor = 0;
	public int turno = 0;
	protected long timeout;
	
	
	public void body() {
		
		Orden Lista = new Orden();
		int[] lista2;
		int minimo = 13;
		int index = 0;
		Mapa Mapa = new Mapa();
		EstadoJuego EstadoJuego = new EstadoJuego(Mapa);
		getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
		///////////////////////Buscamos los jugadores en el df
		
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
		
		/////////////////////////////////////////////////////////
		
		Jugador[]  jugadores	= new Jugador[result.length];
		List<Integer> valores = new ArrayList<>();
		TirarDados tirardados = new TirarDados();
		for(int i=0;i< result.length;i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(result[i].getName());
			mensaje_enviar.setContent(tirardados);
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);		
			Jugador player = (Jugador)respuesta.getContent();
			System.out.println("El jugador: "+ player.getNombre()+" saca un : "+player.getTirada() + " Aid : "+ player.getAid());
			System.out.println(respuesta);
			player.setAgentID((AgentIdentifier)respuesta.getParameter("sender").getValue());
			jugadores[i] = player;
			valores.add(player.getTirada());
		}
		

		//////////////////////////////////////////////////////////
				
		Jugador[]  jugadores2	= jugadores;		
		lista2 = new int[result.length];
		
		for( int j =0; j<jugadores.length;j++) {
			for (int i=0;i<jugadores.length;i++) {
				if(jugadores2[i].getTirada() <= minimo) { 
					minimo = jugadores2[i].getTirada();
					index = i; 
				}	
			}
			lista2[j] = index;
			jugadores2[index].setTirada(13);
			minimo=13;
		}
		
		for(int i = jugadores2.length-1; i>=0;i--) {Lista.addJugador(jugadores[lista2[i]]);	}

		Lista.setTurno(0);
		Lista.setSiguiente_jugador(Lista.getJugadores().get(0));
		getBeliefbase().getBelief("orden").setFact(Lista);
		
		
		/////////////////////////Test para la lista orden
		Orden check= (Orden)getBeliefbase().getBelief("orden").getFact();
		System.out.println("jugador: "+ check.getJugadores().get(1).getNombre());
		System.out.println( Lista.getJugadores().get(0).getNombre()+" Aid : "+ Lista.getJugadores().get(0).getAid());
		System.out.println( Lista.getJugadores().get(1).getNombre()+" Aid : "+ Lista.getJugadores().get(1).getAid());
		System.out.println( Lista.getJugadores().get(2).getNombre()+" Aid : "+ Lista.getJugadores().get(2).getAid());
		System.out.println( Lista.getJugadores().get(3).getNombre()+" Aid : "+ Lista.getJugadores().get(3).getAid());
		////////////////////////////		
		
		System.out.println("Protocolo Terminado");	
		
	}

}
