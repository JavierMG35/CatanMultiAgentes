package src.Jugador.Plan;

//import jadex.adapter.fipa.AgentIdentifier;
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
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Recurso;
import src.ontologia.predicates.NegociacionTerminada;
import src.ontologia.predicates.RespuestaOferta;

public class NegociarJugadoresPlan extends Plan  {
	public void body() {
		
		IMessageEvent	mensaje_recibido	= (IMessageEvent)getInitialEvent();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		System.out.println("Recibido mensaje para negociar de Tablero a " + yo.getNombre());
		OfrecerNegociacion  contenido = (OfrecerNegociacion)mensaje_recibido.getContent();
		EstadoJuego estadojuego = contenido.getEstadodejuego();
		List<Jugador> jugadores = estadojuego.getJugadores();
		
		/*
		 *    Según la estrategia, decidir oferta
		 */
		IEstrategia estrategia = yo.getStrategy();
		Recurso recurso_necesario = estrategia.propuestaNegociarJugadorRecibir(yo);
		Recurso recursos_ofrecer = estrategia.propuestaNegociarJugadorOfrecer(yo);
		
		if(recurso_necesario==null||recursos_ofrecer==null||recurso_necesario.getTipo().equals(recursos_ofrecer.getTipo())) {
			System.out.println("No quiero negociar");
			NegociacionTerminada contenido_respuesta = new NegociacionTerminada(estadojuego);
			IMessageEvent negociacion_terminada = mensaje_recibido.createReply("negociacion_terminada");
			negociacion_terminada.setContent(contenido_respuesta);
		    sendMessage(negociacion_terminada);
			System.out.println("Negociacion de " + yo.getNombre() + " terminada.");
			getBeliefbase().getBelief("myself").setFact(yo);
			
			
		}
		/*
		 * Si quiero negociar, negocio con todos los jugadores, hasta que uno de ellos acepte mi oferta
		 */
		else {
		RealizarOfertaJugador oferta = new RealizarOfertaJugador(recursos_ofrecer,recurso_necesario, yo,estadojuego);
		System.out.println("Oferta: " + recursos_ofrecer.getTipo() + " a cambio de " + recurso_necesario.getTipo());
		
		
		/*
		 * Selecciono el jugador con el que quiero negociar de manera aleatoria, y pruebo con todos
		 */
		System.out.println("/////////////Comienza negociacion/////////////");
		Random toca_jugador =  new Random();
		List<Jugador> jugadores_seleccionados = new ArrayList<>();
		for(int j=0;j<jugadores.size();j++) {
			jugadores_seleccionados.add(jugadores.get(j));
		}
		
		int indice = 0;
		int i=0;
		int ronda = jugadores_seleccionados.size();
		int tamaño_jugadores = jugadores_seleccionados.size();
		
		while(i<tamaño_jugadores-1) {
			indice = toca_jugador.nextInt(ronda);

			if(jugadores_seleccionados.get(indice).getNombre().equals(yo.getNombre())) {
			}
			else {
				/*
				 * Envio negociación a jugador seleccionado
				 */
			BasicAgentIdentifier AidSiguiente = jugadores_seleccionados.get(indice).getAid();
			IMessageEvent mensaje_enviar = createMessageEvent("oferta_negociacion_n");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.setContent(oferta);
			
			System.out.println("Oferta enviada a: " + jugadores_seleccionados.get(indice).getNombre()+ ". ");	
			
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);
			RespuestaOferta decision = (RespuestaOferta)respuesta.getContent();
			i++;
			ronda--;
			jugadores_seleccionados.remove(jugadores_seleccionados.get(indice));
			
			if(decision.isAcepto()) {
				System.out.println(yo.getNombre() + " gana 1 unidad de " + recurso_necesario.getTipo() + " a cambio de 1 unidad de " + recursos_ofrecer.getTipo());
				yo.getCartas().getRecurso(recurso_necesario).add(recurso_necesario);
				
				yo.getCartas().getRecurso(recursos_ofrecer).remove(recursos_ofrecer);
				estadojuego = decision.getEstadojuego();
				
				break;}
			
			
			}
		}
		
		/*
		 * Terminamos negociacion
		 */
		estadojuego = yo.setMyself(estadojuego);
		NegociacionTerminada contenido_respuesta = new NegociacionTerminada(estadojuego);
		IMessageEvent negociacion_terminada = mensaje_recibido.createReply("negociacion_terminada");
		negociacion_terminada.setContent(contenido_respuesta);
	    sendMessage(negociacion_terminada);
		System.out.println("Negociacion de " + yo.getNombre() + " terminada.");
		getBeliefbase().getBelief("myself").setFact(yo);
	}
	}

}
