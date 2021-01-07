package src.Jugador.Plan;

import java.util.Random;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.EstadoJuego.EstadoJuego;
import src.Mapa.Mapa;


public class ColocaFichasInicialesPlan extends Plan{
	
	public void body()
	{
		System.out.println("Tu mapa Nuestor Mapa");
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		System.out.println("Recibido mensaje de Tablero a Jugador");
		EstadoJuego estado = (EstadoJuego)request.getContent();
		Mapa mapa = estado.getMapa();
		System.out.println(estado.getMapa().getArcilla());
		
		boolean done = false;
		while(!done) {
			Random rand1 =  new Random();
			int posicionInicial = rand1.nextInt(35)+1;
		}
		
		
		
		
		
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
	}
	
	

}
