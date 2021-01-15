package src.Jugador.Plan;

import java.util.List;
import java.util.Random;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.*;
import src.ontologia.actions.PosicionarTurnoInicial;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Mapa;
import src.ontologia.predicates.TurnoInicialPosicionado;


public class ColocaFichasInicialesPlan extends Plan{
	
	public void body()
	{
		
		//Comienza el juego y debemos colocar un poblado y carretera
		//Ontenmos el estado del juego actual y el mapa que nos envia el tablero
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		PosicionarTurnoInicial  posicionar = (PosicionarTurnoInicial)request.getParameter(SFipa.CONTENT).getValue();
		EstadoJuego  estadojuego= posicionar.getEstadojuego();
		Mapa mapa = estadojuego.getMapa();
		//Obtenemos nuestra informacion de la base de creencias
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		Node nodoPoblado= null;
		//Hasta que no construyamos un poblado el juego no puede continuar
		while(nodoPoblado == null) {
			Random rand1 =  new Random();
			int casilla = rand1.nextInt(37);
			System.out.println("Casilla en la que colocamos el poblado: " + (casilla+1));
			nodoPoblado = mapa.fichaInicial(casilla, yo);

		}	
		System.out.println("Poblado inicial colocado en el nodo : "+nodoPoblado.getPos_x()+","+nodoPoblado.getPos_y());
		
		//Añadimos un punto por construir un poblado
		yo.setPuntuacion(yo.getPuntuacion()+1);
		mapa.caminoInicial(nodoPoblado, yo);
		estadojuego=yo.setMyself(estadojuego);
		estadojuego.setMapa(mapa);
		getBeliefbase().getBelief("myself").setFact(yo);	
		//Mandamos el nuevo mapa al tablero
		TurnoInicialPosicionado turnoInicial=new TurnoInicialPosicionado(estadojuego);
		IMessageEvent mensaje_enviar = (IMessageEvent)request.createReply("ficha_colocada");
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
		mensaje_enviar.getParameter(SFipa.CONTENT).setValue(turnoInicial);
	    sendMessage(mensaje_enviar);
		
	}
	
	

}
