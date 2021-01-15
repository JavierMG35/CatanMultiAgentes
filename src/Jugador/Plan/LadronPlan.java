package src.Jugador.Plan;

import java.util.List;
import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.Casilla;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.predicates.LadronDesplazado;

public class LadronPlan extends Plan {

		public void body() {
			//Recibo el mensaje de que debo cambiar el ladron de posicion al sacar un 7
			IMessageEvent	request	= (IMessageEvent)getInitialEvent();
			AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
			Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
			LadronDesplazado estado_ladron = (LadronDesplazado)request.getParameter(SFipa.CONTENT).getValue();
			EstadoJuego estado = (EstadoJuego)estado_ladron.getEstadoJuego();
			List<Casilla> casillas = estado.getMapa().getCasillas();
			
			//Para ello siempre se pondra en la casilla con mas poblados contrarios independientemente de la estrategia
			int poblados_contrarios = 0;
			//Comienza desde la casilla 5 por que es la primera que no es agua
			int casilla_ladron_nueva = 5;  
			//Buscamos la casilla con mas pueblos enemigos
			for(int i=5; i<32;i++) {
				int poblados = 0;
				//Contamos el numero de pueblos de jugadores contrarios en dicha casilla
				for(int j=0; j<6; j++) {
					if(!casillas.get(i).getAdyacentes().get(j).getDueño().getNombre().equals("") && !casillas.get(i).getAdyacentes().get(j).getDueño().getNombre().equals(yo.getNombre())) {
						poblados++;
					}
					//Comprobamos que cada nodo de la casilla no tiene un pueblo o ciudad del propio jugador
					if(casillas.get(i).getAdyacentes().get(j).getDueño()!=null && casillas.get(i).getAdyacentes().get(j).getDueño().getNombre().equals(yo.getNombre())) {
						poblados = poblados-6;
					}
					
					if(casillas.get(i).getAdyacentes().get(j).getDueño()!=null) {
					}
				}
				//Cuando encontramos una casilla con más poblados enemigos cambiamos la casilla en la que colocar el ladron
				if((poblados>=poblados_contrarios) && (casillas.get(i).getRecurso().getTipo() != "Agua")) {
					poblados_contrarios = poblados;
					casilla_ladron_nueva = i;
				}
				
				//Quitamos el ladron de su casilla actual
				if(estado.getMapa().getCasillas().get(i).isLadron()) {
					estado.getMapa().getCasillas().get(i).setLadron(false);
				}
				
			}
			
			estado=yo.setMyself(estado);
			estado.getMapa().getCasillas().get(casilla_ladron_nueva).setLadron(true);
			estado=yo.setMyself(estado);
			estado_ladron.setEstadoJuego(estado);
			getBeliefbase().getBelief("myself").setFact(yo);
			//Enviamos el mensaje que cvontiene el estado de juego con la nueva posicion del ladrón
			IMessageEvent mensaje_enviar = (IMessageEvent)request.createReply("ladron_recibir");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
			mensaje_enviar.getParameter(SFipa.CONTENT).setValue(estado_ladron);
		    sendMessage(mensaje_enviar);
		    
		}
}
