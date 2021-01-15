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
import src.ontologia.actions.EntregarDados;
import src.ontologia.concepts.*;
import src.ontologia.predicates.LadronDesplazado;
import src.ontologia.predicates.NegociacionTerminada;
import src.ontologia.predicates.OfertaJugadorBanca;
import src.Mapa.Casilla;
import src.Mapa.Node;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.util.ArrayList;

public class TurnoPlan extends Plan {
	public void body() {
		
		
		//Obtenemos el orden en el que los jugadores tiraran
		Orden Orden = ((Orden) getBeliefbase().getBelief("orden").getFact());
		
		for (int z = 0; z < 5; z++) {
			System.out.println("///////////////////////////////////////////////////////////////////////////");
			System.out.println("//////////////////////COMIENZA RONDA: " + z+"//////////////////////////////");
			System.out.println("//////////////////////////////////////////////////////////////////////////");
			for (int i = 0; i < Orden.getJugadores().size(); i++) {
				//Obtenemos el estado del juego de nuestra base de creencias y el jugador al que le coresponde el turno
				EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
				BasicAgentIdentifier AidSiguiente = Orden.getJugadores().get(i).getAid();
				System.out.println("Truno de: " + Orden.getJugadores().get(i).getNombre());
				
				
				////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////El tablero le entrega los dados al jugador///////////////
				///////////////////////////////////////////////////////////////////////////////////////
				//Creamos el mensaje con el estado del tablero y le entregamos los dados al jugador
				EntregarDados tirardados = new EntregarDados();
				IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
				mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				mensaje_enviar.setContent(tirardados);
				IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);
				//En la respuesta obtendremos los dados con el valor que el jugador ha obtenido
				Dados dados = (Dados) respuesta.getContent();
				System.out.println("El jugador "+Orden.getJugadores().get(i).getNombre()+ " obtine un "+ dados.getDados());

				//////////////////////////////////////////////////////////////////////////////
				///////////////////////////Resolver dados/////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////
				
				//Si sale un 7 nos encontramos en una situacion especial el jugador debe mover la ficha del ladron
				if (dados.getDados() == 7) {
					//Enviamos Un mensaje para que el jugador proceda a mover el ladron
					IMessageEvent ladron_mensaje = createMessageEvent("ladron_enviar");
					ladron_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
					LadronDesplazado MoverLadron = new LadronDesplazado();
					MoverLadron.setEstadoJuego(EstadoJuego);
					ladron_mensaje.setContent(MoverLadron);
					IMessageEvent respuesta_ladron = sendMessageAndWait(ladron_mensaje);
					LadronDesplazado LadronMapa = (LadronDesplazado) respuesta_ladron.getContent();
					System.out.println("El ladron es desplazado a la casilla: "+LadronMapa.get);
					EstadoJuego = (EstadoJuego) LadronMapa.getEstadoJuego();

				} else {

					Mapa mapa = EstadoJuego.getMapa();
					List<Casilla> casillas = mapa.getCasillas();
					System.out.println("Repartir recursos");

					List<Casilla> casilleo = mapa.getCasillas();
					for (int i2 = 0; i2 < mapa.getCasillas().size(); i2++) {

						Casilla casi = casilleo.get(i2);
						for (int j = 0; j < casi.getAdyacentes().size(); j++) {
							Node nodo = casi.getAdyacentes().get(j);
							// if ( nodo.isOcupado()) System.out.println("Nodo ocupado!!");
						}
					}
					// Recorremos las casillas
					for (int j = 0; j < casillas.size(); j++) {
						Casilla casilla = casillas.get(j);

						if (casilla.getValor() == dados.getDados() && !casilla.isLadron()) {

							Recurso recurso_casilla = casilla.getRecurso();
							List<Node> nodos = casilla.getAdyacentes();

							for (int k = 0; k < nodos.size(); k++) {
								Node nodo = nodos.get(k);
								// System.out.println("llego");
								if (nodo.isOcupado()) {
									Jugador player = nodo.getDueño();
									String tipo = nodo.getTipo();
									// Añadimos un recurso si es poblado o dos si es ciudad
									Cartas cartas = player.getCartas();
									if (tipo == "Poblado") {
										cartas.setRecurso(recurso_casilla);
									} else {
										cartas.setRecurso(recurso_casilla);
										cartas.setRecurso(recurso_casilla);
									}
									System.out.println("Se reparte el recurso " + recurso_casilla.getTipo()
											+ " al jugador " + player.getNombre());
								}
							}

						}
					}

				}
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////Negociacion con jugadores////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("**************************Negociacion con jugadores*************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				IMessageEvent mensaje_comenzar_negociacion = createMessageEvent("comenzar_negociacion");
				OfrecerNegociacion contenido = new OfrecerNegociacion(EstadoJuego);
				AidSiguiente = Orden.getJugadores().get(i).getAid();        
				mensaje_comenzar_negociacion.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				mensaje_comenzar_negociacion.setContent(contenido);
				System.out.println("Mensaje para comenzar a negociar enviado");
				IMessageEvent    respuesta_negociacion    = sendMessageAndWait(mensaje_comenzar_negociacion); 
				NegociacionTerminada contenido_respuesta= (NegociacionTerminada)respuesta_negociacion.getContent();
				EstadoJuego = contenido_respuesta.getEstadodejuego();
				getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("***********************Fin Negociacion con jugadores***********************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				//////////////////////////////////////////////////////////////////////////////////////////////////


				//////////////////////////////////////////////////////////////////////////////////////////////////
				//////////////////////////// Comerciar con el
				////////////////////////////////////////////////////////////////////////////////////////////////// banco////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("****************************Comercio con el banco**************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				IMessageEvent mensaje_comercio_banco = createMessageEvent("offer_comercio_banco");
				mensaje_comercio_banco.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);

				mensaje_comercio_banco.setContent(new OfrecerComerciarBanca(EstadoJuego));
				System.out.println("Notifico de la fase de comercio a: " + Orden.getJugadores().get(i).getNombre());
				IMessageEvent respuestacomerciarbanco = sendMessageAndWait(mensaje_comercio_banco);
				/////// oferta ="desado"+"ofrecido"

				OfertaJugadorBanca oferta_jugador_banca = (OfertaJugadorBanca) respuestacomerciarbanco.getContent();
				String[] oferta = oferta_jugador_banca.getOferta();

				if (!oferta[0].equals("null") && !oferta[1].equals("null")) {
					System.out.println("El jugador cambia" + oferta[0] + " por " + oferta[1]);
					for (int j = 0; j < 3; j++) {
						EstadoJuego.getJugadores().get(i).getCartas().removeRecurso(new Recurso(oferta[1]));
					}
					EstadoJuego.getJugadores().get(i).getCartas().setRecurso(new Recurso(oferta[0]));
				} else {
					System.out.println("El jugador no decide comerciar con el banco");
				}
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("***************************Fin comrecio con el banco************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				////////////////////////////////////////////////////////////////////////////////////////////////////

				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("////////////////////////////Colocar una construccion//////////////////////////");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				IMessageEvent construir_mensaje = createMessageEvent("construir_enviar");
				construir_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				OfrecerConstruir construircontenido = new OfrecerConstruir();
				construircontenido.setEstadoJuego(EstadoJuego);
				construir_mensaje.setContent(construircontenido);
				System.out.println("envio mensaje");
				IMessageEvent respuesta_construir = sendMessageAndWait(construir_mensaje);
				System.out.println("recibo mensaeje");
				OfrecerConstruir nuevomapa = (OfrecerConstruir) respuesta_construir.getContent();
				EstadoJuego = nuevomapa.getEstadoJuego();

				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("*********************Acaba el momento de construccion***********************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");

				/*
				 * /////////////////////////////////////////////////////////////////////////////
				 * ///////////////////// ////////////////////////////Comerciar carta de
				 * desarrollo/////////////////////////////////////////
				 * /////////////////////////////////////////////////////////////////////////////
				 * ///////////////////// System.out.println(
				 * "////////////////////////////////////////////////////////////////////////////"
				 * ); System.out.
				 * println("************************Comerciar carta de desarrollo***********************"
				 * ); System.out.println(
				 * "////////////////////////////////////////////////////////////////////////////"
				 * ); AidSiguiente = Orden.getJugadores().get(i).getAid(); IMessageEvent
				 * mensaje_carta_desarrollo = createMessageEvent("offer_carta_desarrollo");
				 * mensaje_carta_desarrollo.getParameterSet(SFipa.RECEIVERS).addValue(
				 * AidSiguiente);
				 * 
				 * mensaje_carta_desarrollo.setContent(new OfertaCartaDesarrollo(EstadoJuego));
				 * System.out.println("Notifico de la fase de compra de cartas de desarrollo: "
				 * +Orden.getJugadores().get(i).getAid()); IMessageEvent
				 * respuestacartadesarrollo = sendMessageAndWait(mensaje_carta_desarrollo);
				 * ///////oferta ="desado"+"ofrecido"
				 * 
				 * CartaDesarrollo carta_desarrollo = (CartaDesarrollo)
				 * respuestacartadesarrollo.getContent(); if (carta_desarrollo!=null) {
				 * EstadoJuego.getJugadores().get(i).getCartas().addDesarrollo(carta_desarrollo)
				 * ; System.out.println("El jugaodr compro una carta de desarrollo"); } else
				 * {System.out.println("El juegador no compro la carta de desarrollo");}
				 * 
				 * System.out.println(
				 * "////////////////////////////////////////////////////////////////////////////"
				 * ); System.out.
				 * println("**********************Fin Comerciar carta de desarrollo**********************"
				 * ); System.out.println(
				 * "////////////////////////////////////////////////////////////////////////////"
				 * );
				 * /////////////////////////////////////////////////////////////////////////////
				 * ///////////////////////
				 */

				// ACTUALIZAR EL ESTADO DE JUEGO
				getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
				System.out.println("final turno");

					////////////////////////////////////////
				System.out.println("ACTUALIZAMOS PUNTUACION JUGADOR");
				// Actualizamos la puntiación del jugador
				Jugador actual = Orden.getJugadores().get(i);
				int puntuacion_actual = actual.getPuntuacion();
				int contador = 0;

				System.out.println("Soy el JUGADOR " + actual.getNombre());
				System.out.println("El tamaño de construcciones es : " + actual.getNumeroConstrucciones().length);

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

				// Asignamos la tarjeta especial de "Mayor Ejercito" si es el jugador con más
				// cartas de Caballero
				if (EstadoJuego.getMayor_Ejercito().getDueño() != null) {
					int cartascaballeroActual = 0;
					int cartascaballeroDueño = 0;
					for (int j = 0; j < EstadoJuego.getMayor_Ejercito().getDueño().getCartas().getCartas_desarrollo()
							.size(); j++) {
						if (actual.getCartas().getCartas_desarrollo().get(j).equals("Caballero")) { // Contamos cartas
																									// de Caballero del
																									// jugador actual
							cartascaballeroActual++;
						}
						if (EstadoJuego.getMayor_Ejercito().getDueño().getCartas().getCartas_desarrollo().get(j)
								.equals("Caballero")) { // Contamos cartas de Caballero del jugador dueño de la tarjeta
							cartascaballeroDueño++;
						}

						if (cartascaballeroActual > cartascaballeroDueño) {
							EstadoJuego.getMayor_Ejercito().setDueño(actual); // Si el jugador actual posee más cartas
																				// de Caballero se vuelve el dueño de la
																				// tarjeta "Mayor Ejercito"
							contador = contador + 2;
						}
					}
				} else {
					// En caso de no tener dueño se lo asignamos en caso de que el jugador tenga 3 o
					// mas cartas de Caballero
					int cartascaballeroActual = 0;
					for (int j = 0; j < actual.getCartas().getCartas_desarrollo().size(); j++) {
						if (actual.getCartas().getCartas_desarrollo().get(j).equals("Caballero"))
							cartascaballeroActual++;
					}
					if (cartascaballeroActual >= 3) {
						EstadoJuego.getMayor_Ejercito().setDueño(actual);
						contador = contador + 2;
					}
				}

				// Asignamos la tarjeta especial de "Ruta Comcercial" si es el jugador con más
				// cartas de Caballero
				if (EstadoJuego.getRuta_Comercial().getDueño() != null) {
					int carreterasActual = 0;
					int carreterasDueño = 0;
					for (int j = 0; j < EstadoJuego.getMayor_Ejercito().getDueño().getConstrucciones().size(); j++) {
						if (actual.getConstrucciones().get(j).getTipo().equals("Carretera")) { // Contamos cartas de
																								// Caballero del jugador
																								// actual
							carreterasActual++;
						}
						if (EstadoJuego.getRuta_Comercial().getDueño().getConstrucciones().get(j).getTipo()
								.equals("Carretera")) { // Contamos cartas de Caballero del jugador dueño de la tarjeta
							carreterasDueño++;
						}

						if (carreterasActual > carreterasDueño) {
							EstadoJuego.getRuta_Comercial().setDueño(actual); // Si el jugador actual posee más cartas
																				// de Caballero se vuelve el dueño de la
																				// tarjeta "Mayor Ejercito"
							contador = contador + 2;
						}
					}
				} else {
					// En caso de no tener dueño se lo asignamos en caso de que el jugador tenga 3 o
					// mas cartas de Caballero
					int carreterasActual = 0;
					for (int j = 0; j < actual.getConstrucciones().size(); j++) {
						if (actual.getConstrucciones().get(j).getTipo().equals("Carretera"))
							carreterasActual++;
					}
					if (carreterasActual >= 3)
						EstadoJuego.getRuta_Comercial().setDueño(actual);
					contador = contador + 2;
				}

				puntuacion_actual = contador;
				actual.setPuntuacion(puntuacion_actual);

				// Checkeamos si el jugador ha obtenido los 10 puntos necesarios para ganar
				if (puntuacion_actual == 10) {
					System.out.println("HAS GANADO EL JUEGO!");
				} else {
					System.out.println("¡¡¡ La puntuación actual del jugador " + actual.getNombre() + " es: "
							+ puntuacion_actual + " !!!");
				}

			}

		}
		EstadoJuego EstadoJuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();
		List <Jugador> Jugadores=EstadoJuego.getJugadores();
		
		System.out.println("///////////////////////////////////////////////////////////////////////////");
		System.out.println("///////////////////////////Fin de la partida///////////////////////////////");
		System.out.println("///////////////////////////////////////////////////////////////////////////");
		System.out.println("Nombre ----> Puntuacion");
		int max=-1;
		int pos=-1;
		int podium=1;
		while (Jugadores.size()>0) {
			for(int i = 0 ; i < Jugadores.size();i++)
			{
				if (Jugadores.get(i).getPuntuacion()>max) {max=Jugadores.get(i).getPuntuacion(); pos=i;}
				
			}
			System.out.println(podium+": "+Jugadores.get(pos).getNombre() +" ----> "+Jugadores.get(pos).getPuntuacion());
			Jugadores.remove(Jugadores.get(pos));
			max=-1;
			pos=-1;
			podium++;
			
		}
		
		System.out.println("Fin del Juego.");

	}

}
