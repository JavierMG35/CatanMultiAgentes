package src.Tablero.plans;

//import jadex.adapter.fipa.AgentDescription;
//import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.EstadoJuego.EstadoJuego;
import src.Jugador.Jugador;
import src.Tablero.Tablero;
import src.ontologia.actions.TirarDados;
import src.ontologia.concepts.Orden;
import src.Mapa.Dados;
import src.Mapa.Mapa;

public class TurnoPlan extends Plan{
	public void body()
	{
		////////////////////////Creamos el mapa y declaramos el estado del juego Inicial
		System.out.println("////////////////////////////TURNO////////////////////////////");
		
	
		
	
		Jugador[] jugadores = (Jugador[])getBeliefbase().getBeliefSet("jugador").getFacts();
		Orden Orden = ((Orden)getBeliefbase().getBelief("orden").getFact());
		for(int i=0;i< Orden.getNumeroJugadores();i++) {
				
			EstadoJuego EstadoJuego = (EstadoJuego)getBeliefbase().getBelief("EstadoJuego").getFact();
			BasicAgentIdentifier AidSiguiente = Orden.getSiguiente_jugador().getAid();			
			System.out.println("Turno de siguiente : "+ AidSiguiente);
			
			TirarDados tirardados = new TirarDados();
			
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.setContent(tirardados);
			System.out.println("Petición dados enviado esperando respuesta");
			
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);
			
			System.out.println("Dados recibidos");
			
			Jugador dado = (Jugador)respuesta.getContent();
			
			System.out.println("Ha salido un" + dado.getTirada());
			//EstadoJuego estado = (EstadoJuego)respuesta.getContent();
			//getBeliefbase().getBelief("EstadoJuego").setFact(estado);
			
			if(dado.getTirada() == 7) {
				IMessageEvent ladron_mensaje = createMessageEvent("ladron_enviar");
				ladron_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				ladron_mensaje.setContent(EstadoJuego);
				IMessageEvent	respuesta_ladron	= sendMessageAndWait(ladron_mensaje);
			} else {
				//No es 7 - Repartir recursos
			}	
			
			
					//negociar
						//negociar juigadores
							//proponer
							//recibir respuesta
							//contraoferta
						//negociar tablero - realizar
					//o construir
						//se selecciona tipo (carretera, poblado, ciudad)
						//donde
						//pagar recursos
					//o comprar carta - realizar
				//fin turno

				
		}
		
		
		
		
	}

}
