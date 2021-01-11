package src.Tablero.plans;

import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.Mapa;
//import src.Mapa.*;

public class TurnoPlan extends Plan{
	
	public void body() {
		
		
		System.out.println("////////////TURNO/////////////////");
		////obtener jugadores
		////para cada jugador: turno
						////Plan dados
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
