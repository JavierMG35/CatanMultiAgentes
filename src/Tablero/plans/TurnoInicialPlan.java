package src.Tablero.plans;

import jadex.adapter.fipa.AgentDescription;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.EstadoJuego.EstadoJuego;
import src.Jugador.Jugador;
import src.Mapa.Mapa;
import src.ontologia.Orden;

public class TurnoInicialPlan extends Plan{
	public void body()
	{
		////////////////////////Creamos el mapa y declaramos el estado del juego Inicial
		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("Creamos el mapa y declaramos el estado del juego Inicial");
		Mapa Mapa = new Mapa();
		System.out.println(Mapa.getArcilla());
		EstadoJuego EstadoJuego = new EstadoJuego(Mapa);
		//////////////////////////////////////
		Orden Orden = ((Orden)getBeliefbase().getBelief("orden").getFact());
		getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
		
		
		//AgentDescription[] result =(AgentDescription[])busqueda.getParameterSet("result").getValues();
		BasicAgentIdentifier AidSiguiente = Orden.getSiguiente_jugador().getAid();
		
		System.out.println("Aid siguiente : "+ AidSiguiente);
		
		
		EstadoJuego mapa	= (EstadoJuego)getBeliefbase().getBelief("EstadoJuego").getFact();
		System.out.println(mapa.getMapa().getArcilla());
		IMessageEvent mensaje_enviar = createMessageEvent("coloca_fichas_iniciales");
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
		mensaje_enviar.setContent(mapa);
		System.out.println("Mensaje enviado esperando respuesta");
		IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);
		System.out.println("Respuesta recividas");
		Jugador player = (Jugador)respuesta.getContent();
		
		
		
		
		
		
		
	}

}
