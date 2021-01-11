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
import src.ontologia.concepts.Orden;
import src.Mapa.Mapa;

public class TurnoPlan extends Plan{
	public void body()
	{
		////////////////////////Creamos el mapa y declaramos el estado del juego Inicial
		System.out.println("////////////////////////////TURNO////////////////////////////");
		
	
		
	
		Jugador[] jugadores = (Jugador[])getBeliefbase().getBeliefSet("jugador").getFacts();
		Orden Orden = ((Orden)getBeliefbase().getBelief("orden").getFact());
		for(int i=0;i< Orden.getNumeroJugadores();i++) {
					////Plan dados    ///esto no está hecho, no hay nada aún
			EstadoJuego EstadoJuego = (EstadoJuego)getBeliefbase().getBelief("EstadoJuego").getFact();
			BasicAgentIdentifier AidSiguiente = Orden.getSiguiente_jugador().getAid();			
			System.out.println("Truno de siguiente : "+ AidSiguiente);
			
			
			IMessageEvent mensaje_enviar = createMessageEvent("tira_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.setContent(EstadoJuego);
			System.out.println("Petición dados enviado esperando respuesta");
			
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);
			
			System.out.println("Dados recibidos");
			
			EstadoJuego estado = (EstadoJuego)respuesta.getContent();
			getBeliefbase().getBelief("EstadoJuego").setFact(estado);
						///Evaluar dados
							///Si es 7 
									///Ladron (descarte y/o colocar(robas))
							//No es 7 - Repartir recursos
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
