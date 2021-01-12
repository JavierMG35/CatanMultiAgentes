package src.Jugador.Plan;

import java.util.List;
import java.util.Random;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
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
		
		
		//BUCLE DE NODOS
		List<Casilla> casilleo = mapa.getCasillas();
		for (int i2 = 0; i2 < mapa.getCasillas().size(); i2++) {
			
			Casilla casi = casilleo.get(i2);
			for (int j = 0; j < casi.getAdyacentes().size(); j++) {
				Node nodo = casi.getAdyacentes().get(j);
				if ( nodo.isOcupado()) System.out.println("Nodo ocupado!!");
			}
		}
		getBeliefbase().getBelief("myself").setFact(yo);
		//BUCLE DE NODOS
		
		
		
		//Mandamos el nuevo mapa.
		//Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		IMessageEvent mensaje_enviar = request.createReply("ficha_colocada");
		//mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		mensaje_enviar.setContent(estadojuego);
	    sendMessage(mensaje_enviar);
	    System.out.println("Mandado posicion inicial");
		
	}
	
	

}
