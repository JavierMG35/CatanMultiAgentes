package src.Jugador.Plan;

import jadex.runtime.Plan;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import src.Jugador.Jugador;
import src.ontologia.actions.ObtencionRecursos;
import src.ontologia.concepts.Recurso;
import src.ontologia.predicates.RecursosObtenidos;

public class RecibirRecursoPlan extends Plan{
	public void body() {
		IMessageEvent    request    = (IMessageEvent)getInitialEvent();
        AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
        System.out.println("Jugador añade el recurso");
        ObtencionRecursos  mensaje = (ObtencionRecursos)request.getParameter(SFipa.CONTENT).getValue();
        Recurso recurso = mensaje.getRecurso();
        Jugador yo = (Jugador) getBeliefbase().getBelief("myself").getFact();
        yo.getCartas().setRecurso(recurso);
        getBeliefbase().getBelief("myself").setFact(yo);
        
        //Mandamos respuesta al tablero
        RecursosObtenidos contenido_vuelta = new RecursosObtenidos();
        IMessageEvent mensaje_enviar = (IMessageEvent)request.createReply("recurso_recibido",contenido_vuelta);
        sendMessage(mensaje_enviar);
		
	}
	

}
