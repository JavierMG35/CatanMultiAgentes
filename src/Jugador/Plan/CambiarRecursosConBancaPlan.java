package src.Jugador.Plan;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.Dados;
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
		////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////Miro si tengo 4 cartas de algun tipo/////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		//Recurso deseo=yo.getStrategy().propuestaNegociarBanca(yo.getCartas());
		Recurso deseo = new Recurso("Madera");
		Recurso recurso_enviado= new Recurso();
		recurso_enviado=yo.cuatroCartasIguales(recurso_enviado, deseo.getTipo());
		
		
		//////////////////////////////En el caso de que envie algo reduzco en 4 los recursos de esa lista
		if (recurso_enviado.getTipo()!=null) {
			for (int i = 0; i < 3; i++) {yo.getCartas().removeRecurso(recurso_enviado);}
			}
		
		String[] Oferta = {deseo.getTipo() , recurso_enviado.getTipo()};
		System.out.println("Soy "+yo.getNombre()+"voy a cambiar "+Oferta[0]+" por "+Oferta[1]);
		/////////////////////////////Envio la oferta al tablero
		IMessageEvent mensaje_enviar = request.createReply("oferta_comercio_banco",new OfertaJugadorBanca(Oferta));
		mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
	    sendMessage(mensaje_enviar);
	    getBeliefbase().getBelief("myself").setFact(yo);
		
	}

}
