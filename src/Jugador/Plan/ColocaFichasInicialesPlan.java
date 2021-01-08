package src.Jugador.Plan;

import java.util.Random;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
//import src.EstadoJuego.EstadoJuego;
import src.Mapa.Mapa;


public class ColocaFichasInicialesPlan extends Plan{
	
	public void body()
	{
		
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		System.out.println("Recibido mensaje de Tablero a Jugador");
		Mapa  estado = (Mapa)request.getContent();
		boolean posible = false;
		
		//Generamos una posición aleatoria de inicio de las fichas
		while(!posible) {
			Random rand1 =  new Random();
			int posicion = rand1.nextInt(35)+1;
			System.out.println(posicion);
			posible = estado.fichaInicial(estado, posicion);
		}	
		
		//Mandamos el nuevo mapa.
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		IMessageEvent mensaje_enviar = request.createReply("ficha_colocada");
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		//mensaje_enviar.setContent(estado);
	    sendMessage(mensaje_enviar);
	    System.out.println("Mandado posicion inicial");
		
	}
	
	

}
