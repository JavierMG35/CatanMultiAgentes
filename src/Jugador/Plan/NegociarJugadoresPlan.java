package src.Jugador.Plan;

import jadex.adapter.fipa.AgentIdentifier;
import java.util.Random;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.*;
import src.Jugador.estrategias.IEstrategia;

import java.util.ArrayList;
import java.util.List;
import src.ontologia.actions.*;
import src.ontologia.actions.*;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Recurso;

public class NegociarJugadoresPlan extends Plan  {
	public void body() {
		
		IMessageEvent	mensaje_recibido	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) mensaje_recibido.getParameter("sender").getValue();
		EstadoJuego  estadojuego = (EstadoJuego)mensaje_recibido.getContent();
		List<Jugador> jugadores = estadojuego.getJugadores();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		System.out.println("Recibido mensaje para negociar de Tablero a " + yo.getNombre());
		
		/////////negociar/////
		///Seleccionar qué quiero negociar
		IEstrategia estrategia = yo.getStrategy();
		Recurso recurso_necesario = estrategia.propuestaNegociarJugadorRecibir(yo);
		List<Recurso> recursos_ofrecer = estrategia.propuestaNegociarJugadorOfrecer(yo);
		RealizarOferta oferta = new RealizarOferta(recursos_ofrecer,recurso_necesario, yo);
		///negociar
		System.out.println("Comienza negociación");
		Random toca_jugador =  new Random();
		List<Jugador> jugadores_seleccionados =  new ArrayList<>();
		boolean encontrado = false;
		int indice = 0;
		for(int i=0;i<jugadores.size();i++) {
			encontrado = false;
			while(!encontrado) {
				indice = toca_jugador.nextInt(jugadores.size());
			if(!jugadores_seleccionados.contains(jugadores.get(indice))) {encontrado = true; jugadores_seleccionados.add(jugadores.get(indice)); }
			else {encontrado = false;}
			}
			BasicAgentIdentifier AidSiguiente = jugadores.get(indice).getAid();
			IMessageEvent mensaje_enviar = createMessageEvent("oferta_negociacion");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.setContent(oferta);
			System.out.println("Oferta enviada a: " + jugadores.get(i).getName() + ". Esperando respuesta");	
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);
			RespuestaOferta decision = (RespuestaOferta)respuesta.getContent();
			System.out.println("Respuesta de negociación recibida. La respuesta a aceptar la oferta es: " + decision.isAcepto());
			
			if(decision.isAcepto()) {
				System.out.println("Mis cartas antes de obtener mi recurso: " + yo.getCartas().getRecurso(recurso_necesario).get(0).getTipo()  + yo.getCartas().getRecurso(recurso_necesario).size()  );
				yo.getCartas().getRecurso(recurso_necesario).add(recurso_necesario);
				System.out.println("Mis cartas antes de obtener mi recurso: " + yo.getCartas().getRecurso(recurso_necesario).get(0).getTipo()  + yo.getCartas().getRecurso(recurso_necesario).size()  );

				System.out.println("Mis cartas antes de dar mi recurso: " + yo.getCartas().getRecurso(recurso_necesario).get(0).getTipo()  + yo.getCartas().getRecurso(recurso_necesario).size()  );
				yo.getCartas().getRecurso(recursos_ofrecer.get(0)).removeAll(recursos_ofrecer);
				break;}
			
			
		}
		
		
		System.out.println("Negociacion de " + yo.getNombre() + " terminada.");
		getBeliefbase().getBelief("myself").setFact(yo);
	}

}
