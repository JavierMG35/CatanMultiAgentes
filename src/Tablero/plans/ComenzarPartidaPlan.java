package src.Tablero.plans;

import jadex.runtime.*;

import java.util.ArrayList;
//import src.Jugador.*;
//import jadex.util.SUtil;
import java.util.List;
import jadex.adapter.fipa.*;
import src.Jugador.Jugador;
import src.ontologia.actions.EntregarDados;
import src.ontologia.concepts.Dados;
import src.ontologia.concepts.EstadoJuego;
import src.ontologia.concepts.Mapa;
import src.ontologia.concepts.Orden;
import src.ontologia.predicates.TiradaDados;

public class ComenzarPartidaPlan extends Plan {

	public List<Integer> valores;
	public int max_valor = 0;
	public int turno = 0;
	protected long timeout;

	public void body() {
		Orden Lista = new Orden();
		int[] lista2;
		int minimo = 13;
		int index = 0;
		Mapa Mapa = new Mapa();
		EstadoJuego EstadoJuego = new EstadoJuego(Mapa);
		getBeliefbase().getBelief("EstadoJuego").setFact(EstadoJuego);
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------------------COMIENZA LA PARTIDA----------------------");

		Jugador[] jugadores = (Jugador[]) getBeliefbase().getBeliefSet("jugador").getFacts();
		List<Integer> valores = new ArrayList<>();
		EntregarDados tirardados = new EntregarDados();
		// Enviamos los dados a los jugadores para estableces el orden inicial
		for (int i = 0; i < jugadores.length; i++) {
			IMessageEvent mensaje_enviar = createMessageEvent("offer_tirar_dados");
			mensaje_enviar.getParameterSet(SFipa.RECEIVERS).addValue(jugadores[i].getAid());
			mensaje_enviar.setContent(tirardados);
			IMessageEvent respuesta = sendMessageAndWait(mensaje_enviar);
			TiradaDados tirada = (TiradaDados) respuesta.getContent();
			Dados dados=tirada.getDados();
			jugadores[i].setTirada(dados.getDados());
			System.out.println("El jugador: " + jugadores[i].getNombre() + " ha sacado un : " + dados.getDados());
			jugadores[i].setAgentID((AgentIdentifier) respuesta.getParameter("sender").getValue());
			valores.add(dados.getDados());
		}

		// Ordenamos los jugadores segun la primera tirada realizada
		//////////////////////////////////////////////////////////

		Jugador[] jugadores2 = jugadores;
		lista2 = new int[jugadores.length];
		// Orena de mayora a menor los jugadores segun la tirada obtenida
		for (int j = 0; j < jugadores.length; j++) {
			for (int i = 0; i < jugadores.length; i++) {
				if (jugadores2[i].getTirada() <= minimo) {
					minimo = jugadores2[i].getTirada();
					index = i;
				}
			}
			lista2[j] = index;
			jugadores2[index].setTirada(13);
			minimo = 13;
		}

		for (int i = jugadores2.length - 1; i >= 0; i--) {
			Lista.getJugadores().add(jugadores2[lista2[i]]);
		}
		
		Lista.setSiguiente_jugador(Lista.getJugadores().get(0));
		getBeliefbase().getBelief("orden").setFact(Lista);
		EstadoJuego estadojuego = (EstadoJuego) getBeliefbase().getBelief("EstadoJuego").getFact();

		estadojuego.setJugadores(Lista.getJugadores());
		///////////////////////// Test para la lista orden
		System.out.println("EL ORDEN DE ESTA PARTIDA SERA");
		System.out.println("1º "+Lista.getJugadores().get(0).getNombre() );
		System.out.println("2º "+Lista.getJugadores().get(1).getNombre());
		System.out.println("3º "+Lista.getJugadores().get(2).getNombre());
		System.out.println("4º "+Lista.getJugadores().get(3).getNombre());
		////////////////////////////

		System.out.println("Protocolo Terminado");

	}

}
