
package src.Jugador.Plan;

import java.util.List;
import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.OfertaComercio;
import src.ontologia.concepts.OfertaJugadorBanca;
import src.ontologia.concepts.Recurso;

public class CambiarRecursosConBancaPlan extends Plan{
	
	
	
	public void body()
	{
		////////////////////////////////Recibo el mensaje con el estado del juego actual
		IMessageEvent	request	= (IMessageEvent)getInitialEvent();
		AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
		OfertaComercio oferta_comercio = (OfertaComercio)request.getContent();
		EstadoJuego EstadoJuego = oferta_comercio.getEstadoJuego();
		////////////////////////////////Busco a mi personaje para actualizar mi base de creencias
		Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
		
		int index=-1;
		for (int i = 0; i<EstadoJuego.getJugadores().size();i++) {
			if (EstadoJuego.getJugadores().get(i).getNombre().equals(yo.getNombre())) index=i;
		}
		/////////////////////////////////////////////////////////////////////////////////////////
		yo=EstadoJuego.getJugadores().get(index);
		yo.getCartas().printCartas();
		////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////Miro si tengo 4 cartas de algun tipo/////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		Recurso[] oferta=yo.getStrategy().propuestaNegociarBanca(yo.getCartas(), EstadoJuego.getMapa(),yo);
		String[] Oferta = {"null" , "null"};
		//////////////////////////////En el caso de que envie algo reduzco en 4 los recursos de esa lista
		if (oferta!=null) {
			
			for (int i = 0; i < 3; i++) {yo.getCartas().removeRecurso(oferta[1]);	System.out.println(yo.getCartas().getArcilla().size());}
			Oferta[0]=oferta[0].getTipo();
			Oferta[1]=oferta[1].getTipo();
			System.out.println("Cambio: "+Oferta[0] +" por "+Oferta[1]);
		}
		/////////////////////////////Envio la oferta al tablero
		IMessageEvent mensaje_enviar = request.createReply("oferta_comercio_banco",new OfertaJugadorBanca(Oferta));
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
	    sendMessage(mensaje_enviar);
	    getBeliefbase().getBelief("myself").setFact(yo);
		
	}

}
