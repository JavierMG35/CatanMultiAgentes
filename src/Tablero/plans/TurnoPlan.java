package src.Tablero.plans;

//import jadex.adapter.fipa.AgentDescription;
//import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Tablero.Tablero;
import src.ontologia.actions.OfrecerConstruir;
import src.ontologia.actions.OfrecerComerciarBanca;
import src.ontologia.actions.OfrecerNegociacion;
import src.ontologia.actions.ProponerNegociacion;
import src.ontologia.actions.DesplazarLadron;
import src.ontologia.actions.EntregarDados;
import src.ontologia.actions.ObtencionRecursos;
import src.ontologia.actions.ProponerCartaDesarrollo;
import src.ontologia.concepts.*;
import src.ontologia.predicates.Construido;
import src.ontologia.predicates.LadronDesplazado;
import src.ontologia.predicates.NegociacionTerminada;
import src.ontologia.predicates.RecursosObtenidos;
import src.ontologia.predicates.TiradaDados;
import src.ontologia.predicates.ComerciadoConBanca;
import src.ontologia.predicates.CompradaCarta;
import src.Mapa.Casilla;
import src.Mapa.Node;
import java.util.List;


import java.util.ArrayList;

public class TurnoPlan extends Plan {
	public void body() {

		// Obtenemos el orden en el que los jugadores tiraran
		Orden Orden = ((Orden) getBeliefbase().getBelief("orden").getFact());
		
		for (int z = 0; z < 7; z++) {
			System.out.println("///////////////////////////////////////////////////////////////////////////");
			System.out.println("//////////////////////COMIENZA RONDA: " + z + "//////////////////////////////");
			System.out.println("//////////////////////////////////////////////////////////////////////////");
			
			
			for (int i = 0; i < Orden.getJugadores().size(); i++) {
				// Obtenemos el estado del juego de nuestra base de creencias y el jugador al
				// que le coresponde el turno
				EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
				BasicAgentIdentifier AidSiguiente = Orden.getJugadores().get(i).getAid();
				System.out.println("Truno de: " + Orden.getJugadores().get(i).getNombre());

				////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////// El tablero le entrega los dados al
				//////////////////////////////////////////////////////////////////////////////////////// jugador///////////////
				///////////////////////////////////////////////////////////////////////////////////////
				// Creamos el mensaje con el estado del tablero y le entregamos los dados al
				//////////////////////////////////////////////////////////////////////////////////////// jugador
				EntregarDados tirardados = new EntregarDados();
				IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
				mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				mensaje_enviar.setContent(tirardados);
				IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);
				// En la respuesta obtendremos los dados con el valor que el jugador ha obtenido
				TiradaDados tirada = (TiradaDados) respuesta.getContent();
				Dados dados= tirada.getDados();
				System.out.println(
						"El jugador " + Orden.getJugadores().get(i).getNombre() + " obtine un " + dados.getDados());

				//////////////////////////////////////////////////////////////////////////////
				/////////////////////////// Resolver dados/////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////

				// Si sale un 7 nos encontramos en una situacion especial el jugador debe mover
				// la ficha del ladron
				if (dados.getDados() == 7) {
					// Enviamos Un mensaje para que el jugador proceda a mover el ladron
					IMessageEvent ladron_mensaje = createMessageEvent("ladron_enviar");
					ladron_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
					DesplazarLadron MoverLadron = new DesplazarLadron(EstadoJuego);
					ladron_mensaje.setContent(MoverLadron);
					IMessageEvent respuesta_ladron = sendMessageAndWait(ladron_mensaje);
					LadronDesplazado LadronMapa = (LadronDesplazado) respuesta_ladron.getContent();
					System.out.println("El ladron es desplazado a la casilla: "
							+ LadronMapa.getEstadoJuego().getMapa().getPosicionLadron());
					// Acutualizamos el estado del juego para segui utilizandolo a lo largo de este
					// plan
					EstadoJuego = (EstadoJuego) LadronMapa.getEstadoJuego();

				}
				// En el caso de que salga otro numero se repartiran los recusos, los jugadores
				// los obtendran si tienen construcciones en esa casilla
				else {

					Mapa mapa = EstadoJuego.getMapa();
					List<Casilla> casillas = mapa.getCasillas();
					// Recorremos las casillas buscando construcciones de cada jugador
					for (int j = 0; j < casillas.size(); j++) {
						Casilla casilla = casillas.get(j);
						// si la casilla tiene el mismo valor de los dados y la figuara del ladron no
						// esta en esta casilla los recursos se reparten
						if (casilla.getValor() == dados.getDados() && !casilla.isLadron()) {
							Recurso recurso_casilla = casilla.getRecurso();
							List<Node> nodos = casilla.getAdyacentes();
							for (int k = 0; k < nodos.size(); k++) {
								Node nodo = nodos.get(k);
								// Miramos si el nodo tiene una construccion es decir esta ocupado
								if (nodo.isOcupado()) {
									Jugador player = nodo.getDueño();
									String tipo = nodo.getTipo();
									// Añadiremos un recurso si el jugador tiene un poblado o 2 si tiene una ciudad
									Cartas cartas = player.getCartas();
									if (tipo.equals("Poblado")) {
										cartas.setRecurso(recurso_casilla);
										IMessageEvent mensaje_con_recursos = createMessageEvent("repartir_recurso");
                                        ObtencionRecursos contenido_recurso = new ObtencionRecursos(recurso_casilla);
                                        BasicAgentIdentifier AidJugadorRecurso = player.getAid();
                                        mensaje_con_recursos.getParameterSet(SFipa.RECEIVERS).addValue(AidJugadorRecurso);
                                        mensaje_con_recursos.setContent(contenido_recurso);
                                        System.out.println("Mensaje para comenzar a negociar enviado");
                                        IMessageEvent    respuesta_negociacion    = sendMessageAndWait(mensaje_con_recursos); 
                                        RecursosObtenidos recurso_recibido = (RecursosObtenidos)respuesta_negociacion.getContent();
				
										
									} else {
										
										cartas.setRecurso(recurso_casilla);
										cartas.setRecurso(recurso_casilla);
										for(int y =0;y<2;y++) {
											IMessageEvent mensaje_con_recursos = createMessageEvent("repartir_recurso");
										    ObtencionRecursos contenido_recurso = new ObtencionRecursos(recurso_casilla);
										    BasicAgentIdentifier AidJugadorRecurso = player.getAid();
										    mensaje_con_recursos.getParameterSet(SFipa.RECEIVERS).addValue(AidJugadorRecurso);
										    mensaje_con_recursos.setContent(contenido_recurso);
										    System.out.println("Mensaje para comenzar a negociar enviado");
										    IMessageEvent    respuesta_negociacion    = sendMessageAndWait(mensaje_con_recursos); 
										    RecursosObtenidos recurso_recibido = (RecursosObtenidos)respuesta_negociacion.getContent();
										}
										}
									System.out.println("Se entrega el recurso " + recurso_casilla.getTipo()
											+ " al jugador " + player.getNombre());
								}
							}

						}
					}

				}

				//////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////// Negociacion con jugadores////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////// 
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("**************************Negociacion con jugadores*************************");
				// Avisamos al jugador que comienza la fase en la que puede negociar con otros
				// jugadores.
				// Le enviamos un mensaje con el estado del juego
				IMessageEvent mensaje_comenzar_negociacion = createMessageEvent("comenzar_negociacion");
				ProponerNegociacion contenido = new ProponerNegociacion(EstadoJuego);
				AidSiguiente = Orden.getJugadores().get(i).getAid();
				mensaje_comenzar_negociacion.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				mensaje_comenzar_negociacion.setContent(contenido);
				IMessageEvent respuesta_negociacion = sendMessageAndWait(mensaje_comenzar_negociacion);
				NegociacionTerminada contenido_respuesta = (NegociacionTerminada) respuesta_negociacion.getContent();
				// Como resupesta el tablero obtiene un mensaje del jugador que le notifica que
				// no quiere realizar mas negociaciones
				EstadoJuego = contenido_respuesta.getEstadodejuego();
				getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
				System.out.println("***********************Fin Negociacion con jugadores***********************");
				//////////////////////////////////////////////////////////////////////////////////////////////////

				//////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////// Comerciar con el banco///////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////////////////////////////////// 
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("****************************Comercio con el banco**************************");
				// El comercio con el banco comienza el jugador puede cambiar 4 recursos iguales
				// por uno de su eleecion con la banca
				// Enviamos un mensaje que avisa que la fase de comercion con el banco a
				// comenzado.
				IMessageEvent mensaje_comercio_banco = createMessageEvent("offer_comercio_banco");
				mensaje_comercio_banco.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				mensaje_comercio_banco.setContent(new OfrecerComerciarBanca(EstadoJuego));
				IMessageEvent respuestacomerciarbanco = sendMessageAndWait(mensaje_comercio_banco);
				// En la respuesta obtenemos el recurso que desea y el recurso por el cual desea
				// cambiarlo este agente
				ComerciadoConBanca oferta_jugador_banca = (ComerciadoConBanca) respuestacomerciarbanco.getContent();
				String[] oferta = oferta_jugador_banca.getOferta();
				// Si la oferta se acepta el objeto tendra el nombre del objeto que deasea
				// cambiar y por cual lo cambia
				if (!oferta[0].equals("null") && !oferta[1].equals("null")) {
					System.out.println("El jugador cambia" + oferta[0] + " por 4 recursos de " + oferta[1]);
					for (int j = 0; j < 3; j++) {
						EstadoJuego.getJugadores().get(i).getCartas().removeRecurso(new Recurso(oferta[1]));
					}
					EstadoJuego.getJugadores().get(i).getCartas().setRecurso(new Recurso(oferta[0]));
				}
				// Si el objeto esta vacio el jugador no realiza ningun comercio
				else {
					System.out.println("El jugador no decide comerciar con el banco");
				}
				System.out.println("***************************Fin comrecio con el banco************************");
				////////////////////////////////////////////////////////////////////////////////////////////////////

				//////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////// Colocar una
				////////////////////////////////////////////////////////////////////////////////////////////////// construccion/////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////Colocar una construccion//////////////////////////");
				// Comienza la fase del constructor y el tablero se lo comunica al jugador
				IMessageEvent construir_mensaje = createMessageEvent("construir_enviar");
				construir_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				OfrecerConstruir construircontenido = new OfrecerConstruir();
				construircontenido.setEstadoJuego(EstadoJuego);
				construir_mensaje.setContent(construircontenido);
				IMessageEvent respuesta_construir = sendMessageAndWait(construir_mensaje);
				// Recibo el estado del juego con un mapa actualizado con la nueva contruccion
				Construido construido = (Construido) respuesta_construir.getContent();
				EstadoJuego = construido.getEstadodejuego();
				System.out.println("*********************Acaba el momento de construccion***********************");

				/////////////////////////////////////////////////////////////////////////////
				//////////////////////////// Comerciar carta de desarrollo////////////////////
				/////////////////////////////////////////////////////////////////////////////
				System.out.println("************************Comerciar carta de desarrollo***********************");
				// El tablero notifica al jugador de que comienza la fase de compra de cartas de
				// desarrollo y le envia el estado del juego actual
				AidSiguiente = Orden.getJugadores().get(i).getAid();
				IMessageEvent mensaje_carta_desarrollo = createMessageEvent("offer_carta_desarrollo");
				mensaje_carta_desarrollo.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				mensaje_carta_desarrollo.setContent(new ProponerCartaDesarrollo(EstadoJuego));
				IMessageEvent respuestacartadesarrollo = sendMessageAndWait(mensaje_carta_desarrollo);
				CompradaCarta compradacarta = (CompradaCarta) respuestacartadesarrollo.getContent();
				CartaDesarrollo carta_desarrollo= compradacarta.getCarta();
				// Obtenemos la carta de desarrollo que ha obtenido el jugador
			
				// Si el objeto de desarrollo no esta vaico ha realizado la compra
				if (carta_desarrollo != null) {
					EstadoJuego.getJugadores().get(i).getCartas().addDesarrollo(carta_desarrollo);
					System.out.println("El jugador compro una carta de desarrollo");
				}
			

				System.out.println("**********************Fin Comerciar carta de desarrollo**********************");
				/////////////////////////////////////////////////////////////////////////////

				/////////////////////////////////////////////////////////////////////////////
				///////////////// ACTUALIZAR EL ESTADO DE JUEGO//////////////////////////////
				/////////////////////////////////////////////////////////////////////////////

				System.out.println("******************************Final de turno*********************************");
				//Actualizamso la base de nuestras creencias
				getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
				System.out.println("*********************ACTUALIZAMOS PUNTUACION JUGADOR*************************");
				//Obtenemos la puntucacion del jugador en este turno y la actualizamos en nuestras creencias				
				Jugador actual = Orden.getJugadores().get(i);
				int puntuacion_actual = actual.getPuntuacion();
				int contador = 0;
				// Contamos las construcciones y las puntuamos
				for (int j = 0; j < actual.getConstrucciones().size() - 1; j++) {
					if (actual.getConstrucciones().get(j).getTipo().equals("Poblado")) {
						contador++;
					} else if (actual.getConstrucciones().get(j).getTipo().equals("Ciudad")) {
						contador = contador + 2;
					}
				}

				// Contamos las cartas de puntos de victoria del jugador
				for (int j = 0; j < actual.getCartas().getCartas_desarrollo().size(); j++) {
					if (actual.getCartas().getCartas_desarrollo().get(j).equals("Puntos de victoria")) {
						contador++;
					}
				}

				///////////////////////////////////////////////////////////////////////////////////////////
				// Otrogamos la tarjeta de "Mayor Ejercito" si es el jugador con más cartas de Caballero
				///////////////////////////////////////////////////////////////////////////////////////////
				
				/*
				 * Si la carta se ha otrogado buscamos quien la tiene y si posee mas o menos cartas de 
				 * caballero que el jugador acutal 
				 */
				if (EstadoJuego.getMayor_Ejercito().getDueño() != null) {
					int cartascaballeroActual = 0;
					int cartascaballeroDueño = 0;
					for (int j = 0; j < EstadoJuego.getMayor_Ejercito().getDueño().getCartas().getCartas_desarrollo().size(); j++) {
						//Si el jugador tiene cartas de caballero las contamos
						if (actual.getCartas().getCartas_desarrollo().get(j).equals("Caballero")) {
							cartascaballeroActual++;
						}
						// Contamos cartas de Caballero del jugador dueño de la tarjeta
						if (EstadoJuego.getMayor_Ejercito().getDueño().getCartas().getCartas_desarrollo().get(j)
								.equals("Caballero")) { 
							cartascaballeroDueño++;
						}
						//Si el actual jugador tiene mas cartas de caballero se le otorga la carta de caballero
						if (cartascaballeroActual > cartascaballeroDueño) {
							System.out.println(actual.getNombre()+" obtien la tarjeta de MAYOR EJERCITO");
							EstadoJuego.getMayor_Ejercito().setDueño(actual); 
							contador = contador + 2;
						}
					}
					
				}
				// En caso de no tener dueño se lo asignamos en caso de que el jugador tenga 3 o mas cartas de Caballero
				else {
					
					int cartascaballeroActual = 0;
					for (int j = 0; j < actual.getCartas().getCartas_desarrollo().size(); j++) {
						if (actual.getCartas().getCartas_desarrollo().get(j).equals("Caballero"))
							cartascaballeroActual++;
					}
					if (cartascaballeroActual >= 3) {
						System.out.println("Se entrega a "+actual.getNombre()+" la tarjeta de MAYOR EJERCITO");
						EstadoJuego.getMayor_Ejercito().setDueño(actual);
						contador = contador + 2;
					}
				}

				/* 
				 *Asignamos la tarjeta especial de "Ruta Comcercial" si es el jugador con más caminos contruidos
				 */ 
				//Si la tarjeta ha sido asiganda comprobamos los caminos del dueño de la tarjeta y del actual
				if (EstadoJuego.getRuta_Comercial().getDueño() != null) {
					int carreterasActual = 0;
					int carreterasDueño = 0;
					for (int j = 0; j < EstadoJuego.getMayor_Ejercito().getDueño().getConstrucciones().size(); j++) {
						// Contamos cartas de Caballero del jugador actual
						if (actual.getConstrucciones().get(j).getTipo().equals("Carretera")) { 
							carreterasActual++;
						}
						// Contamos cartas de Caballero del jugador dueño de la tarjeta
						if (EstadoJuego.getRuta_Comercial().getDueño().getConstrucciones().get(j).getTipo().equals("Carretera")) { 
							carreterasDueño++;
						}
						//Si el jugador actual posee más cartas de Caballero se vuelve el dueño de la tarjeta "Mayor Ejercito" 
						if (carreterasActual > carreterasDueño) {
							EstadoJuego.getRuta_Comercial().setDueño(actual); 
							System.out.println(actual.getNombre()+" obtien la tarjeta de RUTA COMERCIAL");
							contador = contador + 2;
						}
					}
				} 
				// En caso de no tener dueño se lo asignamos en caso de que el jugador tenga 3 o mas cartas de Caballero
				
				else {
					int carreterasActual = 0;
					for (int j = 0; j < actual.getConstrucciones().size(); j++) {
						if (actual.getConstrucciones().get(j).getTipo().equals("Carretera"))
							carreterasActual++;
					}
					if (carreterasActual >= 3)
						EstadoJuego.getRuta_Comercial().setDueño(actual);
					System.out.println("Se entrega a "+actual.getNombre()+" la tarjeta de RUTA COMERCIAL");
					contador = contador + 2;
				}

				puntuacion_actual = contador;
				System.out.println("La puntacion de "+actual.getNombre()+" es: "+puntuacion_actual);
				actual.setPuntuacion(puntuacion_actual);
				// Checkeamos si el jugador ha obtenido los 10 puntos necesarios para ganar
				if (puntuacion_actual==10) {break;}
			}
			

		}
		EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
		List<Jugador> Jugadores = EstadoJuego.getJugadores();

		System.out.println("///////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////Fin de la partida///////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////////////////////");
		System.out.println("Nombre ----> Puntuacion");
	
		int max = -1;
		int pos = -1;
		int podium = 1;
		while (Jugadores.size() > 0) {
			for (int i = 0; i < Jugadores.size(); i++) {
				if (Jugadores.get(i).getPuntuacion() > max) {
					max = Jugadores.get(i).getPuntuacion();
					pos = i;
				}

			}
			System.out.println(
					podium + ": " + Jugadores.get(pos).getNombre() + " ----> " + Jugadores.get(pos).getPuntuacion());
			Jugadores.remove(Jugadores.get(pos));
			max = -1;
			pos = -1;
			podium++;

		}

		System.out.println("Fin del Juego.");

	}

}
