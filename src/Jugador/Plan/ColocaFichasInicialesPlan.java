package src.Jugador.Plan;

import java.util.Random;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.Mapa;
//import sun.jvm.hotspot.debugger.cdbg.ClosestSymbol;
import src.Mapa.*;
import src.EstadoJuego.*;


public class ColocaFichasInicialesPlan extends Plan{
	
	public void body()
	{
		
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		System.out.println("Recibido mensaje de Tablero a Jugador");
		EstadoJuego  estadojuego = (EstadoJuego)request.getContent();
		Mapa mapa = estadojuego.getMapa();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		//Jugador yoenestado = estadojuego.getMyself(yo);
		System.out.println(yo.getNombre());
		
		
		
		boolean posible = false;
		Node nodoPoblado= null;
		//Generamos una posición aleatoria de inicio de las fichas
		while(nodoPoblado == null) {
			Random rand1 =  new Random();
			int casilla = rand1.nextInt(37);
			System.out.println("Casilla: " + (casilla+1));
			nodoPoblado = mapa.fichaInicial(mapa, casilla, yo);
		}	
		
		System.out.println("Poblado colocado en : "+nodoPoblado.getPos_x()+","+nodoPoblado.getPos_y());
		posible = false;
		/////Generamos una posicion para un camino aleatorio al lado del poblado puesto=?=?=??
		posible = mapa.caminoInicial(mapa, nodoPoblado, yo);
	
		estadojuego.setMyself(yo);
		estadojuego.setMapa(mapa);
		getBeliefbase().getBelief("myself").setFact(yo);
		
		//Mandamos el nuevo mapa.
		//Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		IMessageEvent mensaje_enviar = request.createReply("ficha_colocada");
		//mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		mensaje_enviar.setContent(estadojuego);
	    sendMessage(mensaje_enviar);
	    System.out.println("Mandado posicion inicial");
		
	}
	
	

}
