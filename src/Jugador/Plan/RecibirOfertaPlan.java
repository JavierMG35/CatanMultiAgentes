package src.Jugador.Plan;


import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.*;
import src.Jugador.estrategias.AbstractEstrategias;
import src.Jugador.estrategias.IEstrategia;
import src.ontologia.actions.*;
import src.ontologia.predicates.RespuestaOferta;


public class RecibirOfertaPlan extends Plan{
	public void body() {
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier ofertante_aid = (AgentIdentifier) request.getParameter("sender").getValue();
		RealizarOferta  oferta = (RealizarOferta)request.getContent();
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		System.out.println("Recibido mensaje de " + oferta.getJugador().getNombre() + " a mi, " + yo.getNombre());
		
		//valorar oferta
		IEstrategia estrategia = yo.getStrategy();
		boolean acepto = estrategia.aceptarOferta(oferta,yo.getCartas());
		System.out.println("Oferta: " + oferta.getJugador().getNombre() + " da " + oferta.getTe_doy().get(0).getTipo() + " a cambio de que " + yo.getNombre() + " de " + oferta.getMe_das().getTipo());
		
		if(acepto) {
			System.out.println("Acepto la oferta");
			System.out.println("Mis cartas antes de dar mi recurso: " + yo.getCartas().getRecurso(oferta.getMe_das()).get(0).getTipo()  + yo.getCartas().getRecurso(oferta.getMe_das()).size()  );
			yo.getCartas().getRecurso(oferta.getMe_das()).remove(0);
			System.out.println("Mis cartas despues de darlo : " + yo.getCartas().getRecurso(oferta.getMe_das()).get(0).getTipo()  + yo.getCartas().getRecurso(oferta.getMe_das()).size()  );
				
			System.out.println("Mis cartas antes de recibir mi recurso: " + yo.getCartas().getRecurso(oferta.getMe_das()).get(0).getTipo()  + yo.getCartas().getRecurso(oferta.getMe_das()).size()  );
			yo.getCartas().getRecurso(oferta.getTe_doy().get(0)).addAll(oferta.getTe_doy());
			System.out.println("Mis cartas antes de recibir mi recurso: " + yo.getCartas().getRecurso(oferta.getMe_das()).get(0).getTipo()  + yo.getCartas().getRecurso(oferta.getMe_das()).size()  );
		}
		RespuestaOferta decision = new RespuestaOferta(acepto);
		
		getBeliefbase().getBelief("myself").setFact(yo);
		
		IMessageEvent respuesta_oferta = request.createReply("respuesta_oferta");
		respuesta_oferta.setContent(decision);
	    sendMessage(respuesta_oferta);
	    System.out.println("Decisión enviada. ¿Acepto? = " + decision.isAcepto());
	}

}
