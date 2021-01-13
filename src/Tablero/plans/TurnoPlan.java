package src.Tablero.plans;

//import jadex.adapter.fipa.AgentDescription;
//import jadex.adapter.fipa.AgentIdentifier;
import jadex.adapter.fipa.SFipa;
import jadex.runtime.BasicAgentIdentifier;
import jadex.runtime.IMessageEvent;
import jadex.runtime.Plan;
import src.Jugador.Jugador;
import src.Tablero.Tablero;
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
		for(int i=0;i< Orden.getJugadores().size();i++) {
				
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
			
			if(dados.getDados() == 13) {
				IMessageEvent ladron_mensaje = createMessageEvent("ladron_enviar");
				ladron_mensaje.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				ladron_mensaje.setContent(EstadoJuego);
				IMessageEvent	respuesta_ladron	= sendMessageAndWait(ladron_mensaje);
			
			
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
							System.out.println("llego");
							if(nodo.isOcupado()) {
								Jugador player = nodo.getDueño();
								//Añadimos un recurso
								Cartas cartas = player.getCartas();
								cartas.setRecurso(recurso_casilla);
								System.out.println("Se reparte el recurso " +recurso_casilla.getTipo()+" al jugador "+player.getNombre());
							}
						}
					}
				}
				
				
				//////////////////////////////////////////////////////////////////////////////////////////////////
				////////////////////////////Comerciar con el banco////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("****************************Comercio con el banco**************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				AidSiguiente = Orden.getJugadores().get(i).getAid();;		
				IMessageEvent mensaje_comercio_banco = createMessageEvent("offer_comercio_banco");
				mensaje_comercio_banco.getParameterSet(SFipa.RECEIVERS).addValue(AidSiguiente);
				
				mensaje_comercio_banco.setContent(new OfertaComercio(EstadoJuego));
				System.out.println("Notifico de la fase de comercio a: "+Orden.getSiguiente_jugador().getNombre());
				IMessageEvent	respuestacomerciarbanco	= sendMessageAndWait(mensaje_comercio_banco);
				///////oferta ="desado"+"ofrecido"				
								
				OfertaJugadorBanca oferta_jugador_banca = (OfertaJugadorBanca) respuestacomerciarbanco.getContent();
				String[] oferta=oferta_jugador_banca.getOferta();
				System.out.println("El jugador cambia" +oferta[1]+ " por "+oferta[0]);
				if (oferta[0]!=null && oferta[1]!=null) {
					for (int j = 0; j < 3; j++) {EstadoJuego.getJugadores().get(i).getCartas().removeRecurso(new Recurso(oferta[1]));}
					EstadoJuego.getJugadores().get(i).getCartas().setRecurso(new Recurso(oferta[0]));
				}
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				System.out.println("***************************Fin comrecio con el banco************************");
				System.out.println("////////////////////////////////////////////////////////////////////////////");
				////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				
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

				
		//ACTUALIZAR EL ESTADO DE JUEGO
		}
		
		
		
	}
	

}
