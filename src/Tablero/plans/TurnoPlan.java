package src.Tablero.plans;

//import jadex.adapter.fipa.AgentDescription;
//import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Tablero.Tablero;
import src.ontologia.actions.OfrecerNegociacion;
import src.ontologia.actions.TirarDados;
import src.ontologia.concepts.*;
import src.Mapa.Casilla;
import src.Mapa.Node;
import java.util.List;
import java.util.ArrayList;

public class TurnoPlan extends Plan{
	public void body()
	{
		////////////////////////Creamos el mapa y declaramos el estado del juego Inicial
		System.out.println("////////////////////////////TURNO////////////////////////////");
		
	
		
	
		Jugador[] jugadores = (Jugador[])getBeliefbase().getBeliefSet("jugador").getFacts();
		Orden Orden = ((Orden)getBeliefbase().getBelief("orden").getFact());
		for (int z=0; z<1;z++) {
			System.out.println("nuvo turno: "+z);
		for(int i=0;i< Orden.getJugadores().size();i++) {
			System.out.println("fasdklfñkls");
			System.out.println("La estrategia del juegdor : "+Orden.getJugadores().get(i).getStrategyname()+" "+Orden.getJugadores().get(i).getStrategy().getName()+" "+Orden.getJugadores().get(i).getStrategy());
			EstadoJuego EstadoJuego = (EstadoJuego)getBeliefbase().getBelief("EstadoJuego").getFact();
			BasicAgentIdentifier AidSiguiente = Orden.getJugadores().get(i).getAid();		
			
			
			System.out.println("Turno de siguiente : "+ AidSiguiente);
			
			TirarDados tirardados = new TirarDados();
			
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
			mensaje_enviar.setContent(tirardados);
			System.out.println("Petición dados enviado esperando respuesta");
			
			IMessageEvent	respuesta	= sendMessageAndWait(mensaje_enviar);
				
			System.out.println("Dados recibidos");
			
			Dados dados = (Dados)respuesta.getContent();
			
			System.out.println("Ha salido un " + dados.getDados());
			//EstadoJuego estado = (EstadoJuego)respuesta.getContent();
			//getBeliefbase().getBelief("EstadoJuego").setFact(estado);
			
			System.out.println("Antes del if");
			
			//Si la suma de los dos dados da 7 se juega al ladron
			if(dados.getDados() <= 7) {
				IMessageEvent ladron_mensaje = createMessageEvent("ladron_enviar");
                ladron_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
                MoverLadron MoverLadron = new MoverLadron();
                MoverLadron.setEstadoJuego(EstadoJuego);
                ladron_mensaje.setContent(MoverLadron);
                
                System.out.println("Se juega al ladron");
                IMessageEvent    respuesta_ladron    = sendMessageAndWait(ladron_mensaje);
                System.out.println("Se ha recibido la nueva posicion del ladron");

                MoverLadron LadronMapa = (MoverLadron)respuesta_ladron.getContent();
                EstadoJuego = (EstadoJuego)LadronMapa.getEstadoJuego();
			
			
			} else {
			
				
				Mapa mapa = EstadoJuego.getMapa();
				List<Casilla> casillas = mapa.getCasillas();
				System.out.println("Repartir recursos");
				
				List<Casilla> casilleo = mapa.getCasillas();
				for (int i2 = 0; i2 < mapa.getCasillas().size(); i2++) {
					
					Casilla casi = casilleo.get(i2);
					for (int j = 0; j < casi.getAdyacentes().size(); j++) {
						Node nodo = casi.getAdyacentes().get(j);
						//if ( nodo.isOcupado()) System.out.println("Nodo ocupado!!");
					}
				}
				//Recorremos las casillas
				for (int j = 0; j < casillas.size(); j++) {
					Casilla casilla = casillas.get(j);

					if(casilla.getValor() == dados.getDados()) {
						Recurso recurso_casilla = casilla.getRecurso();
						List<Node> nodos = casilla.getAdyacentes();

						for (int k = 0; k < nodos.size(); k++) {
							Node nodo = nodos.get(k);
							//System.out.println("llego");
							if(nodo.isOcupado()) {
                                Jugador player = nodo.getDueño();
                                String tipo = nodo.getTipo();
                                //Añadimos un recurso si es poblado o dos si es ciudad
                                Cartas cartas = player.getCartas();
                                if(tipo == "Poblado") {
                                    cartas.setRecurso(recurso_casilla);
                                } else {
                                    cartas.setRecurso(recurso_casilla);
                                    cartas.setRecurso(recurso_casilla);
                                }
                                System.out.println("Se reparte el recurso " +recurso_casilla.getTipo()+" al jugador "+player.getNombre());
                            }
						}
					}
				}
				
			}
				
				/*
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////Negociacion con jugadores////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("**************************Negociacion con jugadores*************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
	            IMessageEvent mensaje_comenzar_negociacion = createMessageEvent("comenzar_negociacion");
	            OfrecerNegociacion contenido = new OfrecerNegociacion(EstadoJuego);
	            mensaje_comenzar_negociacion.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
	            mensaje_comenzar_negociacion.setContent(contenido);
	            System.out.println("Mensaje para comenzar a negociar enviado");
	            IMessageEvent    respuesta_negociacion    = sendMessageAndWait(mensaje_comenzar_negociacion);
	            System.out.println("Negociación terminada");
	            EstadoJuego = (EstadoJuego)respuesta_negociacion.getContent();
	            getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
	            System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("***********************Fin Negociacion con jugadores***********************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
	            //////////////////////////////////////////////////////////////////////////////////////////////////
				*/
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////Comerciar con el banco////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("****************************Comercio con el banco**************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				IMessageEvent mensaje_comercio_banco = createMessageEvent("offer_comercio_banco");
				mensaje_comercio_banco.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				
				mensaje_comercio_banco.setContent(new OfertaComercio(EstadoJuego));
				System.out.println("Notifico de la fase de comercio a: "+Orden.getJugadores().get(i).getNombre());
				IMessageEvent	respuestacomerciarbanco	= sendMessageAndWait(mensaje_comercio_banco);
				///////oferta ="desado"+"ofrecido"				
								
				OfertaJugadorBanca oferta_jugador_banca = (OfertaJugadorBanca) respuestacomerciarbanco.getContent();
				String[] oferta=oferta_jugador_banca.getOferta();
				
				if (!oferta[0].equals("null")&&!oferta[1].equals("null")) {
					System.out.println("El jugador cambia" +oferta[0]+ " por "+oferta[1]);
					for (int j = 0; j < 3; j++) {EstadoJuego.getJugadores().get(i).getCartas().removeRecurso(new Recurso(oferta[1]));}
					EstadoJuego.getJugadores().get(i).getCartas().setRecurso(new Recurso(oferta[0]));
				}
				else {System.out.println("El jugador no decide comerciar con el banco");}
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("***************************Fin comrecio con el banco************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				////////////////////////////////////////////////////////////////////////////////////////////////////


				

				System.out.println("////////////////////////////////////////////////////////////////////////////");
                System.out.println("////////////////////////////Colocar una construccion//////////////////////////");
                System.out.println("////////////////////////////////////////////////////////////////////////////");
                IMessageEvent construir_mensaje = createMessageEvent("construir_enviar");
                construir_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
                ConstruirEstado construircontenido = new ConstruirEstado();
                construircontenido.setEstadoJuego(EstadoJuego);
                construir_mensaje.setContent(construircontenido);
                System.out.println("envio mensaeje");
                IMessageEvent    respuesta_construir    = sendMessageAndWait(construir_mensaje);
                System.out.println("recibo mensaeje");
                ConstruirEstado nuevomapa = (ConstruirEstado) respuesta_construir.getContent();
                EstadoJuego = nuevomapa.getEstadoJuego();

                System.out.println("////////////////////////////////////////////////////////////////////////////");
                System.out.println("*********************Acaba el momento de construccion***********************");
                System.out.println("////////////////////////////////////////////////////////////////////////////");
                
                
				/*
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////Comerciar carta de desarrollo/////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("************************Comerciar carta de desarrollo***********************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				AidSiguiente = Orden.getJugadores().get(i).getAid();		
				IMessageEvent mensaje_carta_desarrollo = createMessageEvent("offer_carta_desarrollo");
				mensaje_carta_desarrollo.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				
				mensaje_carta_desarrollo.setContent(new OfertaCartaDesarrollo(EstadoJuego));
				System.out.println("Notifico de la fase de compra de cartas de desarrollo: "+Orden.getJugadores().get(i).getAid());
				IMessageEvent	respuestacartadesarrollo	= sendMessageAndWait(mensaje_carta_desarrollo);
				///////oferta ="desado"+"ofrecido"				
				
				CartaDesarrollo carta_desarrollo = (CartaDesarrollo) respuestacartadesarrollo.getContent();
				if (carta_desarrollo!=null) {
					EstadoJuego.getJugadores().get(i).getCartas().addDesarrollo(carta_desarrollo);
					System.out.println("El jugaodr compro una carta de desarrollo");
				}
				else {System.out.println("El juegador no compro la carta de desarrollo");}
			
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("**********************Fin Comerciar carta de desarrollo**********************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				////////////////////////////////////////////////////////////////////////////////////////////////////
				*/

                
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

				
             //ACTUALIZAR EL ESTADO DE JUEGO
			 getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
			 System.out.println("final turno");
			 
			 
			 System.out.println("-----------------TIENE ESTE MAPA DESPUES DE LA ACCION----------------");
			 System.out.println("---------------------DEL JUGADOR " + Orden.getJugadores().get(i).getNombre() + "--------------------");
			 EstadoJuego.getMapa().printMapa();
			 System.out.println("---------------------------------------------------------------------");


			 ////////////////////////////////////////
			 System.out.println("ACTUALIZAMOS PUNTUACION JUGAOR");
			 //Actualizamos la puntiación del jugador
			 Jugador actual = Orden.getJugadores().get(i);
			 int puntuacion_actual = actual.getPuntuacion();
			 int contador = 0;

			 System.out.println("Soy el JUGADOR " + actual.getNombre());
			 System.out.println("El tamaño de construcciones es : " + actual.getNumeroConstrucciones().length);
	        
			 //Contamos las construcciones y las puntuamos
			 for(int j=0; j<actual.getConstrucciones().size()-1; j++) {
		         if(actual.getConstrucciones().get(j).getTipo().equals("Poblado")) {
	            	 contador++;
	             }else if(actual.getConstrucciones().get(j).getTipo().equals("Ciudad")) {
	            	 contador = contador + 2;
	             }
	         }
	            
			 //Contamos las cartas de puntos de victoria del jugador
			 for(int j=0; j<actual.getCartas().getCartas_desarrollo().size(); j++) {
	             if(actual.getCartas().getCartas_desarrollo().get(j).equals("Puntos de victoria")) {
	           		 contador++;
	             }
	         }
	            
			 //Asignamos la tarjeta especial de "Mayor Ejercito" si es el jugador con más cartas de Caballero
			 if(EstadoJuego.getMayor_Ejercito().getDueño()!=null) {
	             int cartascaballeroActual = 0;
	             int cartascaballeroDueño = 0;
	             for(int j=0; j<EstadoJuego.getMayor_Ejercito().getDueño().getCartas().getCartas_desarrollo().size(); j++) {
	            	 if(actual.getCartas().getCartas_desarrollo().get(j).equals("Caballero")){		//Contamos cartas de Caballero del jugador actual
	                   	 cartascaballeroActual++;
	            	 }
	            	 if(EstadoJuego.getMayor_Ejercito().getDueño().getCartas().getCartas_desarrollo().get(j).equals("Caballero")) {		//Contamos cartas de Caballero del jugador dueño de la tarjeta
	            		 cartascaballeroDueño++;
	            	 }
	            	
	            	 if(cartascaballeroActual>cartascaballeroDueño) {
	            		 EstadoJuego.getMayor_Ejercito().setDueño(actual);	//Si el jugador actual posee más cartas de Caballero se vuelve el dueño de la tarjeta "Mayor Ejercito"
	            		 contador = contador + 2;
	            	 }
	           	 }
			}else{
	             //En caso de no tener dueño se lo asignamos en caso de que el jugador tenga 3 o mas cartas de Caballero
	             int cartascaballeroActual = 0;
	             for(int j=0; j<actual.getCartas().getCartas_desarrollo().size(); j++) {
	            	 if(actual.getCartas().getCartas_desarrollo().get(j).equals("Caballero")) cartascaballeroActual++;
	             }
	             if(cartascaballeroActual>=3) {
	            	 EstadoJuego.getMayor_Ejercito().setDueño(actual);
	        		 contador = contador + 2;
	             }
			}
	            
	            
	            //Asignamos la tarjeta especial de "Ruta Comcercial" si es el jugador con más cartas de Caballero
			 if(EstadoJuego.getRuta_Comercial().getDueño()!=null) {
	            int carreterasActual = 0;
	            int carreterasDueño = 0;
	            for(int j=0; j<EstadoJuego.getMayor_Ejercito().getDueño().getConstrucciones().size(); j++) {
	            	if(actual.getConstrucciones().get(j).getTipo().equals("Carretera")){		//Contamos cartas de Caballero del jugador actual
	            		carreterasActual++;
	            	}
	            	if(EstadoJuego.getRuta_Comercial().getDueño().getConstrucciones().get(j).getTipo().equals("Carretera")) {		//Contamos cartas de Caballero del jugador dueño de la tarjeta
	            		carreterasDueño++;
	            	}

	            	if(carreterasActual>carreterasDueño) {
	            		EstadoJuego.getRuta_Comercial().setDueño(actual);	//Si el jugador actual posee más cartas de Caballero se vuelve el dueño de la tarjeta "Mayor Ejercito"
	            		contador = contador + 2;
	            	}
	            }
			}else{
	            //En caso de no tener dueño se lo asignamos en caso de que el jugador tenga 3 o mas cartas de Caballero
	            int carreterasActual = 0;
	            for(int j=0; j<actual.getConstrucciones().size(); j++) {
	            	if(actual.getConstrucciones().get(j).getTipo().equals("Carretera")) carreterasActual++;
	            }
	            if(carreterasActual>=3)EstadoJuego.getRuta_Comercial().setDueño(actual);
	    		contador = contador + 2;
	   		}
	            
	            
	        puntuacion_actual = contador;
	        actual.setPuntuacion(puntuacion_actual);

			//Checkeamos si el jugador ha obtenido los 10 puntos necesarios para ganar
	        if(puntuacion_actual==10) {
	            System.out.println("HAS GANADO EL JUEGO!");
	        }else{
	            System.out.println("¡¡¡ La puntuación actual del jugador " + actual.getNombre() + " es: " + puntuacion_actual + " !!!");
	        }
	            
		}
			
			 
	}
		
		
		
	}

}
