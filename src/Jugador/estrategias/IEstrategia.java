package src.Jugador.estrategias;

import java.util.List;

import src.Jugador.Jugador;
import src.ontologia.actions.RealizarOfertaJugador;
import src.ontologia.concepts.*;

public interface IEstrategia {
	
	
	
	public abstract Recurso propuestaNegociarJugadorOfrecer(Jugador jugador);
	
	public abstract Recurso propuestaNegociarJugadorRecibir(Jugador jugador);
	
	public abstract Recurso[] propuestaNegociarBanca(Cartas cartas, Mapa mapa , Jugador yo);
	
	public abstract boolean aceptarOferta(RealizarOfertaJugador oferta, Jugador yo);

	public abstract Construccion decidirConstruccion(Mapa mapa, Cartas cartas, String nombre);
	
	public abstract Boolean decidirCompra(Cartas cartas);
	
	public String getName();



}
