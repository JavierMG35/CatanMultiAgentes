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
			IMessageEvent	request	= (IMessageEvent)getInitialEvent();
			System.out.println("Se elige la nueva posicion del ladron");
			AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
			Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
			
			LadronDesplazado estado_ladron = (LadronDesplazado)request.getParameter(SFipa.CONTENT).getValue();
			EstadoJuego estado = (EstadoJuego)estado_ladron.getEstadoJuego();
			List<Casilla> casillas = estado.getMapa().getCasillas();
			
			//System.out.println("<<<------------------el mapa que recibe el juagdor para usar el LADRON------------------>>>");
			//System.out.println(" EL JUGADOR ES: " + yo.getNombre());
			//MoverLadron mensaje=(MoverLadron) request.getParameter(SFipa.CONTENT).getValue();
			//mensaje.getEstadoJuego().getMapa().printMapa();
		
			int poblados_contrarios = 0;
			int casilla_ladron_nueva = 5;  //5 porque es la primera casilla que no es agua

			for(int i=5; i<32;i++) {
				int poblados = 0;
				
				//Contamos el numero de pueblos de jugadores contrarios en dicha casilla
				for(int j=0; j<6; j++) {
					if(!casillas.get(i).getAdyacentes().get(j).getDueño().getNombre().equals("") && !casillas.get(i).getAdyacentes().get(j).getDueño().getNombre().equals(yo.getNombre())) {
						poblados++;
						//System.out.println("OOOOOOOOOOOOOLKEEEEEEEEEEEEEEEEEEEEE: "+ poblados);
						//System.out.println("En la casilla numero : "+ (i+1));
					}
					//Comprobamos que cada nodo de la casilla no tiene un pueblo o ciudad del propio jugador
					if(casillas.get(i).getAdyacentes().get(j).getDueño()!=null && casillas.get(i).getAdyacentes().get(j).getDueño().getNombre().equals(yo.getNombre())) {
						poblados = poblados-6;
						//System.out.println("PUUUUUUUUUUUUUUUUUTTTTTAAAAAAAAAAAAAAA");
						//System.out.println("En la casilla numero : "+ (i+1));
					}
					
					if(casillas.get(i).getAdyacentes().get(j).getDueño()!=null) {
						//System.out.println("Tenemos por un lado " + casillas.get(i).getAdyacentes().get(j).getDueño().getNombre());
						//System.out.println("Tenemos por el otro lado " + yo.getNombre());
					}
				}

				
				
				//Cuando encontramos una casilla con más poblados enemigos cambiamos la casilla en la que colocar el ladron
				if((poblados>=poblados_contrarios) && (casillas.get(i).getRecurso().getTipo() != "Agua")) {
					//System.out.println("JODEEEEEERRRRRRRRRRRRRRRRRRRRRRRRRR : " + poblados);
					//System.out.println("En la casilla numero : "+ (i+1));
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
			
			IMessageEvent mensaje_enviar = (IMessageEvent)request.createReply("ladron_recibir");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(tablero);
			mensaje_enviar.getParameter(SFipa.CONTENT).setValue(estado_ladron);
			//getLogger().info("Tirada de dados de" + yo.nombre);
		    sendMessage(mensaje_enviar);
		    
		}
}
