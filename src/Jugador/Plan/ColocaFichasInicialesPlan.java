package src.Jugador.Plan;

import java.util.Random;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.Mapa;
import src.Mapa.*;


public class ColocaFichasInicialesPlan extends Plan{
	
	public void body()
	{
		
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		System.out.println("Recibido mensaje de Tablero a Jugador");
		Mapa  mapa = (Mapa)request.getContent();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
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
	
	
		
		//Mandamos el nuevo mapa.
		//Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		IMessageEvent mensaje_enviar = request.createReply("ficha_colocada");
		//mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		mensaje_enviar.setContent(mapa);
	    sendMessage(mensaje_enviar);
	    System.out.println("Mandado posicion inicial");
		
	}
	
	

}
