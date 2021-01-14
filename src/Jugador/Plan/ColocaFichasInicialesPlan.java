package src.Jugador.Plan;

import java.util.List;
import java.util.Random;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.*;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Mapa;


public class ColocaFichasInicialesPlan extends Plan{
	
	public void body()
	{
		
		
		
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		System.out.println("Recibido mensaje de Tablero a Jugador");
		EstadoJuego  estadojuego = (EstadoJuego)request.getParameter(SFipa.CONTENT).getValue();
		Mapa mapa = estadojuego.getMapa();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		//Jugador yoenestado = estadojuego.getMyself(yo);
		System.out.println("Voy a colocar un poblado y calle soy:  "+ yo.getNombre());
		
		boolean posible = false;
		Node nodoPoblado= null;
		//Generamos una posición aleatoria de inicio de las fichas
		while(nodoPoblado == null) {
			Random rand1 =  new Random();
			int casilla = rand1.nextInt(37);
			System.out.println("Casilla en la que colocamos el poblado: " + (casilla+1));
			nodoPoblado = mapa.fichaInicial(casilla, yo);

		}	
		
		System.out.println("Poblado colocado en el nodo : "+nodoPoblado.getPos_x()+","+nodoPoblado.getPos_y());
		posible = false;
		/////Generamos una posicion para un camino aleatorio al lado del poblado puesto=?=?=??
		yo.setPuntuacion(yo.getPuntuacion()+1);
		posible = mapa.caminoInicial(nodoPoblado, yo);
	
		estadojuego=yo.setMyself(estadojuego);
		estadojuego.setMapa(mapa);

		//System.out.println("-----------------el estado del jugador tiene este mapa----------------");
		//estadojuego.getMapa().printMapa();
		//System.out.println("---------------------------------------------------------");
		
		getBeliefbase().getBelief("myself").setFact(yo);
		//BUCLE DE NODOS
		
		
		
		//Mandamos el nuevo mapa.
		//Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		IMessageEvent mensaje_enviar = (IMessageEvent)request.createReply("ficha_colocada");
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		mensaje_enviar.getParameter(SFipa.CONTENT).setValue(estadojuego);
		//mensaje_enviar.setContent(estadojuego);
		EstadoJuego mensaje=(EstadoJuego) mensaje_enviar.getParameter(SFipa.CONTENT).getValue();
		//System.out.println("-----------------el mensaje que envia el jugador tiene este mapa----------------");
		//mensaje.getMapa().printMapa();
	    sendMessage(mensaje_enviar);
	    System.out.println("Mandado posicion inicial al tablero");
		
	}
	
	

}
