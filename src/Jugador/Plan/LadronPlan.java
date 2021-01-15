package src.Jugador.Plan;

import java.util.List;
import java.util.Random;

import jadex.adapter.fipa.*;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Mapa.Casilla;
import src.ontologia.concepts.Cartas;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.predicates.LadronDesplazado;
import src.ontologia.concepts.Recurso;

public class LadronPlan extends Plan {

		public void body() {
			IMessageEvent	request	= (IMessageEvent)getInitialEvent();
			System.out.println("Se elige la nueva posicion del ladron");
			AgentIdentifier tablero = (AgentIdentifier) request.getParameter("sender").getValue();
			Jugador	yo	= (Jugador)getBeliefbase().getBelief("myself").getFact();
			
			LadronDesplazado estado_ladron = (LadronDesplazado)request.getParameter(SFipa.CONTENT).getValue();
			EstadoJuego estado = (EstadoJuego)estado_ladron.getEstadoJuego();
			List<Casilla> casillas = estado.getMapa().getCasillas();
			
		
			int poblados_contrarios = 0;
			int casilla_ladron_nueva = 5;  //El valor empieza en 5 porque es la primera casilla que no es agua

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
				
				//Cuando encontramos una casilla con mas poblados enemigos cambiamos la casilla en la que colocar el ladron
				if((poblados>=poblados_contrarios) && (casillas.get(i).getRecurso().getTipo() != "Agua")) {
					poblados_contrarios = poblados;
					casilla_ladron_nueva = i;
				}
				//Quitamos el ladron de su casilla actual
				if(estado.getMapa().getCasillas().get(i).isLadron()) {
					estado.getMapa().getCasillas().get(i).setLadron(false);
				}
				
			}
			
			System.out.println("Los jugadores con mas de 7 recursos deben descartarse de ellas hasta quedarse en 7");
			
			
			//Descartamos cartas a los jugadores con más de 7 recursos
            for(int j=0; j<estado.getJugadores().size();j++) {
            	
            	Cartas cartas = estado.getJugadores().get(j).getCartas();
        		Random rand =  new Random();
        		int recurso_descarte = 0;
        		
        		//Contamos el número de carats que tiene cada jugador
        		int total_recursos = cartas.getArcilla().size()+cartas.getPiedra().size()+cartas.getMadera().size()+cartas.getPaja().size()+cartas.getLana().size();
   
        		
        		//En caso de tener más de 7 cartas de recurso se van eliminando hast obtener 7
            	while(total_recursos>7) {
            		
            		recurso_descarte = rand.nextInt(5); 		//Creamos una variable aleatoria para desacrtar un recurso cualquiera
            		
            		int total = cartas.getArcilla().size()+cartas.getPiedra().size()+cartas.getMadera().size()+cartas.getPaja().size()+cartas.getLana().size();
            		           		
            		if(recurso_descarte==0 && cartas.getArcilla().size()>0) {
            			cartas.getArcilla().remove(cartas.getArcilla().size()-1);
            			total_recursos--;
            		}
            		if(recurso_descarte==1 && cartas.getPiedra().size()>0) {
            			cartas.getPiedra().remove(cartas.getPiedra().size()-1);
            			total_recursos--;
            		}
            		if(recurso_descarte==2 && cartas.getMadera().size()>0) {
            			cartas.getMadera().remove(cartas.getMadera().size()-1);
            			total_recursos--;
            		}
            		if(recurso_descarte==3 && cartas.getPaja().size()>0) {
            			cartas.getPaja().remove(cartas.getPaja().size()-1);
            			total_recursos--;
            		}
            		if(recurso_descarte==4 && cartas.getLana().size()>0) {
            			cartas.getLana().remove(cartas.getLana().size()-1);
            			total_recursos--;
            		}
            	}
            	//Actualizamos las cartas
            	estado.getJugadores().get(j).setCartas(cartas);
            }
            
            System.out.println("El jugador roba una carta a otro jugador");
            
            //Robamos una carta a un jugador de la casilla del ladron
    		Random rand =  new Random();
    		int recurso_descarte = 0;
        	boolean robado = false;					//Cuando pase a TRUE el bucle while dejara de cumplirse, luego no se robaran mas cartas
        	Recurso recurso_robado = new Recurso();
        	

        	for(int i=0; i<6;i++) {

        		Jugador jugador = new Jugador();
				if(estado.getMapa().getCasillas().get(casilla_ladron_nueva).getNodos_adyacentes().get(i).isOcupado()) {
                	Cartas cartas = estado.getMapa().getCasillas().get(casilla_ladron_nueva).getNodos_adyacentes().get(i).getDueño().getCartas();
    				int recurso_robar;
    				int total_recursos = cartas.getArcilla().size()+cartas.getPiedra().size()+cartas.getMadera().size()+cartas.getPaja().size()+cartas.getLana().size();
    				
                	while(!robado && total_recursos>0) {
                		
                		recurso_robar = rand.nextInt(5); 		//Creamos una variable aleatoria para robar una carta aleatoria al jugador contrincante
                		           		
                		if(recurso_robar==0 && cartas.getArcilla().size()>0) {
                			cartas.getArcilla().remove(cartas.getArcilla().size()-1);
                			recurso_robado.setTipo("Arcilla");
                			yo.getCartas().addArcilla(recurso_robado);
                			robado=true;
                		}
                		if(recurso_robar==1 && cartas.getPiedra().size()>0) {
                			cartas.getPiedra().remove(cartas.getPiedra().size()-1);
                			recurso_robado.setTipo("Piedra");
                			yo.getCartas().addPiedra(recurso_robado);
                			robado=true;
                		}
                		if(recurso_robar==2 && cartas.getMadera().size()>0) {
                			cartas.getMadera().remove(cartas.getMadera().size()-1);
                			recurso_robado.setTipo("Madera");
                			yo.getCartas().addMadera(recurso_robado);
                			robado=true;
                		}
                		if(recurso_robar==3 && cartas.getPaja().size()>0) {
                			cartas.getPaja().remove(cartas.getPaja().size()-1);
                			recurso_robado.setTipo("Paja");
                			yo.getCartas().addPaja(recurso_robado);
                			robado=true;
                		}
                		if(recurso_robar==4 && cartas.getLana().size()>0) {
                			cartas.getLana().remove(cartas.getLana().size()-1);
                			recurso_robado.setTipo("Lana");
                			yo.getCartas().addLana(recurso_robado);
                			robado=true;
                		}
                	}
    				//Actualizamos las cartas del jugador robado
                	estado.getMapa().getCasillas().get(casilla_ladron_nueva).getNodos_adyacentes().get(i).getDueño().setCartas(cartas);
				}
        	}
            
        	//Actualizamos el mapa con la nueva posicion del ladron
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
